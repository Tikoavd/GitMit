package com.practicework.core.room.call_handler

import com.practicework.core.retrofit.call_handler.Resource
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

inline fun <DBMODEL : Any, MODEL> safeRxDbCall(
    crossinline mapper: (DBMODEL) -> MODEL,
    crossinline body: () -> Flowable<DBMODEL>
): Flowable<Resource<MODEL>> {
    return Flowable.create({ subscriber ->
        body.invoke()
            .subscribeOn(Schedulers.io())
            .subscribe ({ dbModel ->
                subscriber.onNext(Resource.Success(mapper(dbModel)))
            },
            {
                subscriber.onNext(Resource.Error(Exception(DbErrors.CAN_NOT_ACCESS_TO_DB), null))
            })
    }, BackpressureStrategy.LATEST)
}