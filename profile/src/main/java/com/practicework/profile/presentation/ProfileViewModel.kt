package com.practicework.profile.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.core.datastore.*
import com.practicework.core.di.EMPTY_TOKEN
import com.practicework.core.di.SHARED_PREF_NAME
import com.practicework.core.di.TOKEN
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.profile.domain.ProfileRepository
import com.practicework.profile.domain.models.User
import com.practicework.profile.domain.usecases.ClearAllDataUseCase
import com.practicework.profile.domain.usecases.GetUserFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    profileRepository: ProfileRepository,
) : ViewModel() {

    private val getUserFromDb = GetUserFromDbUseCase(profileRepository)
    private val clearAllData = ClearAllDataUseCase(profileRepository)

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState>
        get() = _uiState.asStateFlow()

    init {
        getUser()
        getImage()
        getTheme()
    }

    fun send(event: ProfileEvent) {
        when (event) {
            ProfileEvent.SignOut -> {
                signOut()
            }

            is ProfileEvent.SaveUri -> {
                saveImage(event.uri)
            }

            is ProfileEvent.SaveNightMode -> {
                saveTheme(event.isNightMode)
            }
        }
    }

    private fun getTheme() {
        context.getNightMode()
            .onEach { isNightMode ->
                _uiState.update { current ->
                    current.copy(isNightMode = isNightMode)
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun saveTheme(isNightMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            context.saveNightMode(isNightMode)
        }
    }

    private fun getImage() {
        context.getImage()
            .onEach { imageSaved ->
                if (imageSaved) {
                    getImageBitmap()
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun getImageBitmap() {
        viewModelScope.launch(Dispatchers.IO) {
            val fin = context.openFileInput(IMAGE_FILE_NAME)
            val bitmap = BitmapFactory.decodeStream(fin)
            fin.close()

            _uiState.update { current ->
                current.copy(bitmap = bitmap)
            }
        }
    }

    private fun saveImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }

            val fos = context.openFileOutput(IMAGE_FILE_NAME, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()

            context.saveImage(true)
            getImageBitmap()
        }
    }

    private fun clearToken() {
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit()
            .putString(TOKEN, EMPTY_TOKEN)
            .apply()
    }

    private fun clearData() {
        viewModelScope.launch {
            clearAllData()
        }
    }

    private fun signOut() {
        clearToken()
        clearData()
        viewModelScope.launch(Dispatchers.IO) {
            context.clearPrefs()
        }
    }

    private fun getUser() {
        getUserFromDb()
            .onEach { dbResource ->
                if (dbResource is Resource.Success) {
                    updateUser(dbResource.model)
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun updateUser(user: User) {
        _uiState.update { current ->
            current.copy(user = user)
        }
    }
}