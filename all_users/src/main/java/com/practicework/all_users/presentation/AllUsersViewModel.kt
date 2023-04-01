package com.practicework.all_users.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.all_users.domain.AllUsersRepository
import com.practicework.all_users.domain.models.AllUser
import com.practicework.all_users.domain.usecases.GetUsersUseCase
import com.practicework.core.coroutines.CommonDispatchers
import com.practicework.core.retrofit.call_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    allUsersRepository: AllUsersRepository,
    private val commonDispatchers: CommonDispatchers
) : ViewModel() {

    private val getUsersUseCase by lazy { GetUsersUseCase(allUsersRepository) }

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
        getUsersUseCase(PER_PAGE_VALUE, page)
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        handleSuccess(resource.model)
                    }

                    is Resource.Error -> {
                        handleError()
                    }
                    else -> Unit
                }
            }
            .flowOn(commonDispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

    private fun handleError() {
        endReached = true
        _uiState.update { current ->
            current.copy(isLoading = false, isUpdating = false)
        }
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