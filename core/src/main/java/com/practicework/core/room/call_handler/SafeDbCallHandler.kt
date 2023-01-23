package com.practicework.core.room.call_handler

import kotlinx.coroutines.flow.*

inline fun <DBMODEL, MODEL> safeDbCall(
    crossinline mapper: (DBMODEL) -> MODEL,
    crossinline body: () -> Flow<DBMODEL?>
) : Flow<DbResource<MODEL>> {
    return flow {
        body.invoke()
            .onEach { dbModel ->
                dbModel?.let {
                    emit(DbResource.Success(mapper(dbModel)))
                } ?: emit(DbResource.Error)
            }
            .catch {
                emit(DbResource.Error)
            }
            .collect()
    }
}