package org.xm.app.translator.opensource.function

import com.allever.lib.common.util.ShareHelper
import com.allever.lib.common.util.log
import org.xm.app.translator.opensource.app.Global
import org.xm.app.translator.opensource.function.language.Lang
import org.xm.app.translator.opensource.mvvm.model.TranslationResponse
import java.io.File
import java.lang.StringBuilder

object TranslationHelper {

    fun getSrcLang(bean: TranslationResponse): String {
        var srcLang: String = Lang.CHINESE.KEY
        //根据原文本类型去设置
        val srcLangList = bean.ldResult?.extendedSrcLang
        if (srcLangList?.isNotEmpty() == true) {
            srcLang = Global.langCodeKeyMap[srcLangList[0]] ?: Lang.CHINESE.KEY
        }
        log("原语言 = $srcLang")
        return srcLang
    }

    fun getSrcText(bean: TranslationResponse): String {
        var translation = ""
        if (bean.sentences?.size ?: 0 > 0) {
            translation = bean.sentences?.get(0)?.orig ?: ""
        }
        log("原内容 = $translation")
        return translation
    }

    fun getTranslateText(bean: TranslationResponse): String {
        var translation = ""
        if (bean.sentences?.size ?: 0 > 0) {
            translation = bean.sentences?.get(0)?.trans ?: ""
        }
        log("翻译内容 = $translation")
        return translation
    }

    fun getSrcSymbol(bean: TranslationResponse): String {
        var srcSymbol = ""
        if (bean.sentences?.size ?: 0 > 1) {
            val symbols = bean.sentences?.get(1)
            srcSymbol = symbols?.srcTransLit ?: ""
        }
        return srcSymbol
    }

    fun getTranslateSymbol(bean: TranslationResponse): String {
        var translateSymbol = ""
        if (bean.sentences?.size ?: 0 > 1) {
            val symbols = bean.sentences?.get(1)
            translateSymbol = symbols?.transLit ?: ""
        }

        return translateSymbol
    }

    fun getDictText(bean: TranslationResponse): String {
        //解析词典
        val resultBuilder = StringBuilder()
        val dictionaryBeanList = bean.dict
        dictionaryBeanList?.map {
            resultBuilder.append("${it.pos}\n")
            val entity = it.entry
            entity?.map {
                resultBuilder.append("\t${it.word}\n")
                val list = it.reverseTranslation
                list?.mapIndexed { index, s ->
                    val last = if (index != list.size - 1) {
                        ", "
                    } else {
                        "\n\n"
                    }
                    resultBuilder.append("\t$s$last")
                }
            }
        }
        return resultBuilder.toString()
    }

//    fun playTTS(content: String, tl: String) {
//        val ttsPath = DBHelper.getTTSPath(content, tl)
//        if (ttsPath.isNotEmpty()) {
//            MediaHelper.playFile(ttsPath)
//            log("播放本地缓存文件")
//            return
//        }
//
//        requestTTS(content, tl, Runnable {
//            val path = DBHelper.getTTSPath(content, tl)
//            MediaHelper.playFile(path)
//        })
//    }
//
//    fun isPlaying(): Boolean {
//        return MediaHelper.isPlaying()
//    }
//
//    fun liked(history: History): History? {
//        val result = DBHelper.like(history)
//        var newHistory: History? = history
//        if (result) {
//            newHistory = DBHelper.getHistory(history.srcText, history.sl, history.tl)
//            if (newHistory != null) {
//                EventBus.getDefault().post(LikeUpdateEvent(newHistory))
//            }
//        } else {
//        }
//
//        return newHistory
//    }
//
//    fun shareAudio(obj: Any, content: String, tl: String) {
//        val path = DBHelper.getTTSPath(content, tl)
//        val file = File(path)
//        if (file.exists()) {
//            ShareHelper.shareAudio(obj, path)
//            return
//        }
//
//        requestTTS(content, tl, Runnable {
//            ShareHelper.shareAudio(obj, DBHelper.getTTSPath(content, tl))
//        })
//    }
//
//    fun requestTTS(content: String, tl: String, runnable: Runnable?) {
//        NetworkHelper.requestTTS(content, tl, object : TTSRequestCallback {
//            override fun onSuccess(ttsPath: String) {
//                DBHelper.saveTTS(content, tl, ttsPath)
//                runnable?.run()
//            }
//
//            override fun onFail(msg: String) {
//                log(msg)
//            }
//        })
//    }
}