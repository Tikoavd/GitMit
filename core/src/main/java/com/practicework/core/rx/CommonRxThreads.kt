package com.practicework.core.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CommonRxThreads @Inject constructor() {
    val ioThread = Schedulers.io()
    val mainThread = AndroidSchedulers.mainThread()
}