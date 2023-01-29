package com.practicework.core.retrofit.call_handler

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.UnknownHostException

inline fun <RESPONSE : Any, RESULT> safeRxApiCall(
    crossinline mapper: (RESPONSE) -> RESULT,
    crossinline body: () -> Single<RESPONSE>
): Flowable<Resource<RESULT>> {
    return Flowable.create({ subscriber ->
        subscriber.onNext(Resource.Loading)

        body()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response ->
                    subscriber.onNext(Resource.Success(mapper(response)))
                },
                { t ->
                    val exception = when (t) {
                        is UnknownHostException -> {
                            Exception(NO_INTERNET_ACCESS)
                        }
                        else -> {
                            Exception("Could not fetch from network")
                        }
                    }
                    subscriber.onNext(Resource.Error(exception = exception, null))
                }
            )
    }, BackpressureStrategy.LATEST)
}