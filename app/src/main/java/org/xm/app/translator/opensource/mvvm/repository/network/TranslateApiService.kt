package org.xm.app.translator.opensource.mvvm.repository.network

import okhttp3.ResponseBody
import org.xm.app.translator.opensource.mvvm.model.TranslationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateApiService {
//    @GET("translate_a/single?client=gtx&dt=t&dt=bd&dt=rm&dj=1&ie=UTF-8&oe=UTF-8&sl=auto&tl=zh-CN&hl=zh-CN&tk=&q=")
//    fun translate(@Query("q") content: String): Observable<TranslationBean>

    @GET("translate_a/single")
    suspend fun translate(
        @Query("q") q: String,
        @Query("client") content: String = "gtx",
        @Query("dt") dt: String = "t",
        @Query("dt") dt1: String = "bd",
        @Query("dt") dt2: String = "rm",
        @Query("dj") dj: String = "1",
        @Query("ie") ie: String = "UTF-8",
        @Query("oe") oe: String = "UTF-8",
        @Query("sl") sl: String = "auto",
        @Query("tl") tl: String = "en",
        @Query("hl") hl: String = "zh-CN",
        @Query("tk") tk: String = ""
    ): Response<TranslationResponse>

    //https://translate.google.cn/translate_tts?client=gtx&ie=UTF-8&tl=zh-CN&total=1&idx=0&textlen=2&tk=&q=设置
    @GET("translate_tts")
    suspend fun requestTTS(
        @Query("q") q: String,
        @Query("client") content: String = "gtx",
        @Query("ie") ie: String = "UTF-8",
        @Query("tl") tl: String = "en",
        @Query("hl") hl: String = "zh-CN",
        @Query("total") total: String = "1",
        @Query("idx") idx: String = "0",
        @Query("textlen") textlen: String = "0",
        @Query("tk") tk: String = ""
    ): Response<ResponseBody>

}