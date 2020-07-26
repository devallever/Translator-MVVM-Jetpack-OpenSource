package org.xm.app.translator.opensource.mvvm.repository.network

import kotlinx.coroutines.*

fun main() {
    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Default+ job)
    runBlocking {
        val response = NetworkManager.translate("string", "auto", "zh")
        println(response?.toString())
    }
}