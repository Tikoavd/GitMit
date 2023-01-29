package com.practicework.core.room.call_handler

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

inline fun <DBMODEL, MODEL> safeRxDbCall(
    crossinline mapper: (DBMODEL) -> MODEL,
    crossinline body: () -> Flowable<DBMODEL>
): Flowable<DbResource<MODEL>> {
    return Flowable.create({ subscriber ->
        body.invoke()
            .subscribeOn(Schedulers.io())
            .subscribe ({ dbModel ->
                subscriber.onNext(DbResource.Success(mapper(dbModel)))
            },
            {
                subscriber.onNext(DbResource.Error)
            })
    }, BackpressureStrategy.LATEST)
}