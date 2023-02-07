package com.practicework.core.room.call_handler

import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.*

inline fun <DBMODEL, MODEL> safeDbCall(
    crossinline mapper: (DBMODEL) -> MODEL,
    crossinline body: () -> Flow<DBMODEL?>
) : Flow<Resource<MODEL>> {
    return flow {
        emit(Resource.Loading)
        body.invoke()
            .onEach { dbModel ->
                dbModel?.let {
                    emit(Resource.Success(mapper(dbModel)))
                } ?: emit(Resource.Error(Exception(DbErrors.UNKNOWN_DATA), null))
            }
            .catch {
                emit(Resource.Error(Exception(DbErrors.CAN_NOT_ACCESS_TO_DB), null))
            }
            .collect()
    }
}