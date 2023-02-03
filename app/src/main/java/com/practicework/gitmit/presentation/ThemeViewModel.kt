package com.practicework.gitmit.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.core.datastore.getNightMode
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _nightMode : MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val nightMode : StateFlow<Boolean?>
        get() = _nightMode.asStateFlow()

    init {
        getAppTheme()
    }

    private fun getAppTheme() {
        context.getNightMode()
            .onEach {
                _nightMode.value = it
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}