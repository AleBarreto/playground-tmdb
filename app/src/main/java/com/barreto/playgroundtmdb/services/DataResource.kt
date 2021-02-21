package com.barreto.playgroundtmdb.services

/**
 * Font guide https://developer.android.com/jetpack/guide#addendum
 */
sealed class DataResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : DataResource<T>(data)
    class Loading<T>(data: T? = null) : DataResource<T>(data)
    class Error<T>(message: String, data: T? = null) : DataResource<T>(data, message)
}