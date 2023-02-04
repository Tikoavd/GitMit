package com.practicework.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.details.domain.DetailsRepository
import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User
import com.practicework.details.domain.usecases.GetReposUseCase
import com.practicework.details.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailsRepository: DetailsRepository
) : ViewModel() {
    private val login = checkNotNull(savedStateHandle.get<String>(USER_LOGIN))

    private val getUserUseCase = GetUserUseCase(detailsRepository)
    private val getReposUseCase = GetReposUseCase(detailsRepository)

    private val _uiState: MutableStateFlow<DetailsState> = MutableStateFlow(DetailsState())
    val uiState: StateFlow<DetailsState>
        get() = _uiState.asStateFlow()

    private var page = 1
    private var endReached = false

    init {
        update()
    }

    fun send(event: DetailsEvent) {
        when (event) {
            DetailsEvent.GetMoreRepos -> {
                getMoreRepos()
            }

            DetailsEvent.Update -> {
                update()
            }
        }
    }

    private fun update() {
        getUser()
        updateRepos()
    }

    private fun getUser() {
        viewModelScope.launch {
            getUserUseCase(login)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            handleError()
                        }

                        is Resource.Success -> {
                            handleUserSuccess(resource.model)
                        }
                    }
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }
    }

    private fun getRepos() {
        viewModelScope.launch {
            getReposUseCase(login = login, page = page, perPage = PER_PAGE_VALUE)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            handleRepoSuccess(resource.model)
                        }

                        is Resource.Error -> {
                            handleError()
                        }

                        else -> {}
                    }
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }
    }

    private fun updateRepos() {
        _uiState.update { current ->
            current.copy(repos = listOf(), isUpdating = true)
        }
        page = 1
        endReached = false
        getRepos()
    }

    private fun getMoreRepos() {
        _uiState.update { current ->
            current.copy(isLoading = true)
        }
        getRepos()
    }

    private fun handleUserSuccess(user: User) {
        _uiState.update { current ->
            current.copy(user = user)
        }
    }

    private fun handleRepoSuccess(appendList: List<Repo>) {
        if (appendList.isEmpty()) {
            endReached = true
            _uiState.update { current ->
                current.copy(isLoading = false, isUpdating = false)
            }
        }
        else {
            _uiState.update { current ->
                val newList = current.repos + appendList
                current.copy(repos = newList, isLoading = false, isUpdating = false)
            }
            page++
        }
    }

    private fun handleError() {
        _uiState.update { current ->
            current.copy(isLoading = false, isUpdating = false, isError = true)
        }
    }

    companion object {
        private const val PER_PAGE_VALUE = 10
        const val USER_LOGIN = "login"
    }
}