package com.practicework.repos.presentation

import androidx.lifecycle.ViewModel
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.rx.CommonRxThreads
import com.practicework.repos.domain.ReposRepository
import com.practicework.repos.domain.models.Repo
import com.practicework.repos.domain.usecases.GetReposFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    reposRepository: ReposRepository,
    private val rxThreads: CommonRxThreads
) : ViewModel() {

    private val getRepos = GetReposFromApiUseCase(reposRepository)

    private val _uiState = MutableStateFlow(ReposState())
    val uiState: StateFlow<ReposState>
        get() = _uiState.asStateFlow()

    private var page = 1
    private var endReached = false

    init {
        update()
    }

    fun send(event: ReposEvent) {
        when (event) {
            ReposEvent.GetMoreRepos -> {
                if (!endReached) {
                    getMoreRepos()
                }
            }

            ReposEvent.UpdateRepos -> {
                update()
            }
        }
    }

    private fun getRepos() {
        getRepos(PER_PAGE_VALUE, page)
            .subscribeOn(rxThreads.ioThread)
            .observeOn(rxThreads.mainThread)
            .subscribe { resource ->
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
    }

    private fun handleError() {
        endReached = true

        _uiState.update { current ->
            current.copy(isLoading = false, isUpdating = false)
        }
    }

    private fun handleSuccess(appendList: List<Repo>) {
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

    private fun update() {
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

    companion object {
        private const val PER_PAGE_VALUE = 10
    }
}