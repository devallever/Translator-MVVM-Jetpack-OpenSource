package org.xm.app.translator.opensource.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.xm.app.translator.opensource.bean.TranslateParams
import org.xm.app.translator.opensource.function.language.Lang
import org.xm.app.translator.opensource.mvvm.repository.Repository

class TranslateViewModel : ViewModel() {

    private val translateParams = TranslateParams()
    private val translateParamsLiveData = MutableLiveData<TranslateParams>()

    val translateLiveData = Transformations.switchMap(translateParamsLiveData) {
        val result = Repository.translate(it.q, it.sl, it.tl)
        result
    }

    val defaultLang = Lang.CHINESE

    fun translate(q: String?, sl: String, tl: String) {
        if (q?.isEmpty() == true) {
            return
        }
        val same = translateParamsLiveData.value?.equals(q ?: "", sl, tl)
        if (same == true) {
            return
        }
        translateParams.q = q ?: ""
        translateParams.sl = sl
        translateParams.tl = tl
        translateParamsLiveData.value = translateParams
//        translateParamsLiveData.value = TranslateParams(q!!, sl, tl)
    }
}