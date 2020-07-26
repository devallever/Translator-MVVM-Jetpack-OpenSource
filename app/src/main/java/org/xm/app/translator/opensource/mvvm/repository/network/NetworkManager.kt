package org.xm.app.translator.opensource.mvvm.repository.network

import okhttp3.ResponseBody
import org.xm.app.translator.opensource.mvvm.model.TranslationResponse
import retrofit2.Response

object NetworkManager {
    private val mTranslateApiService = ServiceCreator.create(TranslateApiService::class.java)

    suspend fun translate(q: String, sl: String, tl: String): Response<TranslationResponse> =
        mTranslateApiService.translate(q = q, sl = sl, tl = tl)

    suspend fun tts(q: String, tl: String): Response<ResponseBody> =
        mTranslateApiService.requestTTS(q = q, tl = tl)

}