package com.practicework.core.room.call_handler

import com.practicework.core.retrofit.call_handler.Resource
import io.reactivex.rxjava3.core.Flowable

inline fun <DBMODEL : Any, MODEL> safeRxDbCall(
    crossinline mapper: (DBMODEL) -> MODEL,
    crossinline body: () -> Flowable<DBMODEL>
): Flowable<Resource<MODEL>> {
    return try {
        body()
            .map { dbModel ->
                Resource.Success(mapper(dbModel))
            }
    } catch (t: Throwable) {
        Flowable.just(
            Resource.Error(
                Exception(DbErrors.CAN_NOT_ACCESS_TO_DB),
                null
            )
        )
    }
}