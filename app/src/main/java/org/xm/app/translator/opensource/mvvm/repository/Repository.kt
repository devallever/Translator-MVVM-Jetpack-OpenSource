package org.xm.app.translator.opensource.mvvm.repository

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import org.xm.app.translator.opensource.mvvm.repository.network.NetworkManager
import kotlin.coroutines.CoroutineContext

object Repository {

    fun translate(q: String, sl: String, tl: String) = fire(Dispatchers.IO) {
        val response = NetworkManager.translate(q, sl, tl)
        if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.failure(RuntimeException(response.errorBody()?.string()))
        }
    }


    fun tts(q: String, tl: String) = fire(Dispatchers.IO) {
        val response = NetworkManager.tts(q, tl)
        if (response.isSuccessful) {
            Result.success(response)
        } else {
            Result.failure(RuntimeException(response.errorBody()?.string()))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure<T>(e)
            }
            //emit()类似liveData的setValue()通知数据变化
            emit(result)
        }


}