package com.pisey.cleanarchitecturewithgraphql.data.commons

import com.apollographql.apollo3.api.Error

sealed class DataSourceException(
    val messageError: Any?
) : RuntimeException() {
    class Unexpected(message: String) : DataSourceException(message)
    class Server(error: Error?) : DataSourceException(error)
}