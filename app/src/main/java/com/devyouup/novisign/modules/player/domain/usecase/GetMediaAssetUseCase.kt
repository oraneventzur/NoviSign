package com.devyouup.novisign.modules.player.domain.usecase

import com.devyouup.novisign.modules.core.data.remote.BaseResponse
import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetRequest
import com.devyouup.novisign.modules.player.data.remote.GetMediaAssetResponse
import com.devyouup.novisign.modules.player.domain.repository.PlayerRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMediaAssetUseCase @Inject constructor(
    private val repository: PlayerRepository
) {
    operator fun invoke(
        request: GetMediaAssetRequest
    ): Flow<BaseResponse<GetMediaAssetResponse>> = flow {
        try {
//            emit(BaseResponse.Loading)
            val assetResponse = repository.getMediaAsset(request)
            emit(BaseResponse.Success(assetResponse))
        }catch (e: HttpException){
            emit(
                BaseResponse.Failure(
                code = e.code().toString(),
                errorCode = null,
                networkCode = null,
                message = e.message ?: "An unexpected error"
            ))
        }catch (e: Exception){
            emit(
                BaseResponse.Failure(
                    code = "500",
                    errorCode = null,
                    networkCode = null,
                    message = e.message ?: "An unexpected error"
                )
            )
        }
    }
}