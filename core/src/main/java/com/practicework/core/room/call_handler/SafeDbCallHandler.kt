package com.practicework.core.room.call_handler

import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.*

inline fun <DBMODEL, MODEL> safeDbCall(
    crossinline mapper: (DBMODEL) -> MODEL,
    crossinline body: () -> Flow<DBMODEL?>
): Flow<Resource<MODEL>> {
    return body()
        .map { dbModel ->
            dbModel?.let {
                Resource.Success(mapper(dbModel))
            } ?: Resource.Error(Exception(DbErrors.UNKNOWN_DATA), null)
        }
        .catch {
            Resource.Error(Exception(DbErrors.CAN_NOT_ACCESS_TO_DB), null)
        }
        .onStart {
            emit(Resource.Loading)
        }
}
