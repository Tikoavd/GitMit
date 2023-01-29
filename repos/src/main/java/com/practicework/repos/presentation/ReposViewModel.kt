package com.practicework.repos.presentation

import androidx.lifecycle.ViewModel
import com.practicework.core.retrofit.call_handler.NO_INTERNET_ACCESS
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import com.practicework.repos.domain.ReposRepository
import com.practicework.repos.domain.models.Repo
import com.practicework.repos.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val reposRepository: ReposRepository
) : ViewModel() {

    private val getReposFromApi = GetReposFromApiUseCase(reposRepository)
    private val getReposFromDb = GetReposFromDbUseCase(reposRepository)
    private val clearReposFromDb = ClearReposFromDbUseCase(reposRepository)
    private val insertReposToDb = InsertReposToDbUseCase(reposRepository)
    private val updateReposInDb = UpdateReposInDbUseCase(reposRepository)

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
        getReposFromApi(PER_PAGE_VALUE, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
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
    }

    private fun handleError(exc: Exception) {
        if (exc.message == NO_INTERNET_ACCESS) {
            updateReposFromDb()
        }

        _uiState.update { current ->
            current.copy(isLoading = false, isUpdating = false)
        }
    }

    private fun updateReposFromDb() {
        val offset = (page - 1) * PER_PAGE_VALUE
        getReposFromDb(offset, PER_PAGE_VALUE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dbResource ->
                when (dbResource) {
                    DbResource.Error -> {
                        endReached = true
                    }
                    is DbResource.Success -> {
                        if (dbResource.model.isNotEmpty()) {
                            _uiState.update { current ->
                                val newList = current.repos + dbResource.model
                                current.copy(repos = newList)
                            }
                        }
                        else {
                            endReached = true
                        }
                    }
                }
            }
        page++
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
            if (page == 1) {
                updateReposInDb(appendList)
            }
            else {
                insertReposToDb(appendList)
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