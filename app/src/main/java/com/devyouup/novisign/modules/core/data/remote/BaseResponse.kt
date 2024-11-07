package com.devyouup.novisign.modules.core.data.remote

import com.google.gson.annotations.SerializedName

sealed class BaseResponse<out T> {
    data class Success<out T>(val value: T): BaseResponse<T>()
    data class Failure(
        @SerializedName("code") var code: String? = null,
        @SerializedName("errorCode") var errorCode: String? = null,
        @SerializedName("networkCode") var networkCode: String? = null,
        @SerializedName("message") var message: String? = null
    ) : BaseResponse<Nothing>()
    data object NetworkError: BaseResponse<Nothing>()
    data object Loading: BaseResponse<Nothing>()
}