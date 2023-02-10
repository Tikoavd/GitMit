package com.practicework.core.coroutines

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CommonDispatchers @Inject constructor() {
    val ioDispatcher = Dispatchers.IO
    val mainDispatcher = Dispatchers.Main
}