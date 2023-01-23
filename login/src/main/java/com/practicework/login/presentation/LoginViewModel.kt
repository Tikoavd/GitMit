package com.practicework.login.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.core.di.SHARED_PREF_NAME
import com.practicework.core.di.TOKEN
import com.practicework.core.retrofit.call_handler.NO_INTERNET_ACCESS
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import com.practicework.login.domain.LoginRepository
import com.practicework.login.domain.models.User
import com.practicework.login.domain.usecases.DeleteUserFromDbUseCase
import com.practicework.login.domain.usecases.GetUserFromApiUseCase
import com.practicework.login.domain.usecases.GetUserFromDbUseCase
import com.practicework.login.domain.usecases.InsertUserToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    loginRepository: LoginRepository
) : ViewModel() {

    private val getUserFromApiUseCase = GetUserFromApiUseCase(loginRepository)
    private val getUserFromDbUseCase = GetUserFromDbUseCase(loginRepository)
    private val insertUserToDbUseCase = InsertUserToDbUseCase(loginRepository)
    private val deleteUserFromDbUseCase = DeleteUserFromDbUseCase(loginRepository)

    var userInput by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState>
        get() = _uiState.asStateFlow()

    private var firstCheck = true

    init {
        getUser()
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
                saveToken()
                getUser()
            }

            LoginEvent.CloseError -> {
                _uiState.update { current ->
                    current.copy(isError = false, errorMessage = "")
                }
            }
        }
    }

    private fun getToken() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(GET_TOKEN_URL)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    private fun saveToken() {
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit()
            .putString(TOKEN, userInput)
            .apply()
    }

    private fun inputChange(input: String) {
        userInput = input
    }

    private fun getUser() {
        viewModelScope.launch {
            getUserFromApiUseCase()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            handleError(resource.exception)
                        }
                        Resource.Loading -> {
                            loadingOn()
                        }
                        is Resource.Success -> {
                            handleSuccess(resource.model)
                        }
                    }
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }
    }

    private fun handleError(exception: Exception) {
        if (exception.message == NO_INTERNET_ACCESS && firstCheck) {
            getUserFromDbUseCase()
                .onEach { resource ->
                    if (resource is DbResource.Success) {
                        signedInSuccess()
                    }
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }
        else {
            if (!firstCheck) {
                _uiState.update { current ->
                    current.copy(isError = true, errorMessage = exception.message.toString())
                }
            }
            viewModelScope.launch {
                deleteUserFromDbUseCase()
            }
        }
        firstCheck = false
        loadingOff()
    }

    private fun handleSuccess(user: User) {
        viewModelScope.launch {
            insertUserToDbUseCase(user)
        }
        signedInSuccess()
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

    companion object {
        private const val GET_TOKEN_URL = "https://github.com/settings/tokens"
    }
}