package com.practicework.login.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.core.coroutines.CommonDispatchers
import com.practicework.core.retrofit.call_handler.ErrorTypes
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.login.BuildConfig
import com.practicework.login.domain.LoginRepository
import com.practicework.login.domain.usecases.TryToSignInFromDbUseCase
import com.practicework.login.domain.usecases.TryToSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    loginRepository: LoginRepository,
    private val dispatchers: CommonDispatchers
) : ViewModel() {

    private val tryToSignIn by lazy { TryToSignInUseCase(loginRepository) }
    private val tryToSignInFromDb by lazy { TryToSignInFromDbUseCase(loginRepository) }

    var userInput by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState>
        get() = _uiState.asStateFlow()

    init {
        tryToSignInOnLaunch()
    }

    fun send(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.InputChange -> {
                inputChange(loginEvent.input)
            }

            LoginEvent.GetToken -> {
                getToken()
            }

            LoginEvent.SignIn -> {
                signIn()
            }

            LoginEvent.CloseError -> {
                _uiState.update { current ->
                    current.copy(isError = false, errorMessage = "")
                }
            }

            LoginEvent.NavigateSignedIn -> {
                _uiState.update { current ->
                    current.copy(isSignedIn = false)
                }
            }
        }
    }

    private fun tryToSignInOnLaunch() {
        tryToSignInFromDb()
            .onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        loadingOff()
                    }
                    Resource.Loading -> {
                        loadingOn()
                    }
                    is Resource.Success -> {
                        signedInSuccess()
                    }
                }
            }
            .flowOn(dispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

    private fun signIn() {
        tryToSignIn(userInput)
            .onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        loadingOff()
                        handleError(resource.exception)
                    }
                    Resource.Loading -> {
                        loadingOn()
                    }
                    is Resource.Success -> {
                        signedInSuccess()
                    }
                }
            }
            .flowOn(dispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

    private fun getToken() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(BuildConfig.GET_TOKEN_URL)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    private fun inputChange(input: String) {
        userInput = input
    }

    private fun handleError(exception: Exception) {
        val message = when (exception.message) {
            ErrorTypes.EMPTY_RESPONSE.message -> {
                ErrorMessages.UNKNOWN_ACCOUNT
            }
            ErrorTypes.NOT_SUCCESSFULLY_REQUEST.message -> {
                ErrorMessages.INVALID_TOKEN
            }
            ErrorTypes.NO_INTERNET_ACCESS.message -> {
                ErrorTypes.NO_INTERNET_ACCESS.message
            }
            else -> {
                ErrorTypes.COULD_NOT_FETCH.message
            }
        }

        _uiState.update { current ->
            current.copy(isError = true, errorMessage = message)
        }
        loadingOff()
    }

    private fun loadingOn() {
        _uiState.update { current ->
            current.copy(isLoading = true)
        }
    }

    private fun loadingOff() {
        _uiState.update { current ->
            current.copy(isLoading = false)
        }
    }

    private fun signedInSuccess() {
        _uiState.update { current ->
            current.copy(isSignedIn = true)
        }
    }
}