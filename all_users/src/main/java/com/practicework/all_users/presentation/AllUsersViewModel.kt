package com.practicework.all_users.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.all_users.domain.AllUsersRepository
import com.practicework.all_users.domain.models.AllUser
import com.practicework.all_users.domain.usecases.GetUsersFromApiUseCase
import com.practicework.all_users.domain.usecases.GetUsersFromDbUseCase
import com.practicework.all_users.domain.usecases.InsertUsersToDbUseCase
import com.practicework.all_users.domain.usecases.UpdateUsersInDbUseCase
import com.practicework.core.retrofit.call_handler.NO_INTERNET_ACCESS
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val allUsersRepository: AllUsersRepository
) : ViewModel() {

    private val getUsersFromApi = GetUsersFromApiUseCase(allUsersRepository)
    private val getUsersFromDb = GetUsersFromDbUseCase(allUsersRepository)
    private val insertUsersToDb = InsertUsersToDbUseCase(allUsersRepository)
    private val updateUsersInDb = UpdateUsersInDbUseCase(allUsersRepository)

    private val _uiState = MutableStateFlow(AllUsersState())
    val uiState: StateFlow<AllUsersState>
        get() = _uiState.asStateFlow()
    private var page = 1
    private var endReached = false

    init {
        update()
    }

    fun send(event: AllUsersEvent) {
        when (event) {
            AllUsersEvent.GetMore -> {
                if (!endReached) {
                    getMoreUsers()
                }
            }

            AllUsersEvent.Update -> {
                update()
            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            val since = (page - 1) * PER_PAGE_VALUE
            getUsersFromApi(PER_PAGE_VALUE, since)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            handleSuccess(resource.model)
                        }

                        is Resource.Error -> {
                            handleError(resource.exception)
                        }

                        else -> {}
                    }
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }
    }

    private fun handleError(exc: Exception) {
        if (exc.message == NO_INTERNET_ACCESS) {
            updateUsersFromDb()
        }

        _uiState.update { current ->
            current.copy(isLoading = false, isUpdating = false)
        }
    }

    private fun updateUsersFromDb() {
        val offset = (page - 1) * PER_PAGE_VALUE
        getUsersFromDb(offset, PER_PAGE_VALUE)
            .onEach { dbResource ->
                when (dbResource) {
                    DbResource.Error -> {
                        endReached = true
                    }
                    is DbResource.Success -> {
                        if (dbResource.model.isNotEmpty()) {
                            _uiState.update { current ->
                                val newList = current.userList + dbResource.model
                                current.copy(userList = newList)
                            }
                        } else {
                            endReached = true
                        }
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
        page++
    }

    private fun handleSuccess(appendList: List<AllUser>) {
        if (appendList.isEmpty()) {
            endReached = true
            _uiState.update { current ->
                current.copy(isLoading = false, isUpdating = false)
            }
        } else {
            _uiState.update { current ->
                val newList = current.userList + appendList
                current.copy(userList = newList, isLoading = false, isUpdating = false)
            }
            if (page == 1) {
                viewModelScope.launch {
                    updateUsersInDb(appendList)
                }
            } else {
                viewModelScope.launch {
                    insertUsersToDb(appendList)
                }
            }
            page++
        }
    }

    private fun update() {
        _uiState.update { current ->
            current.copy(userList = listOf(), isUpdating = true)
        }
        page = 1
        endReached = false
        getUsers()
    }

    private fun getMoreUsers() {
        _uiState.update { current ->
            current.copy(isLoading = true)
        }
        getUsers()
    }

    companion object {
        private const val PER_PAGE_VALUE = 15
    }
}