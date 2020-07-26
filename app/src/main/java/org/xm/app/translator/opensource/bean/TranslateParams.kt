package org.xm.app.translator.opensource.bean


class TranslateParams(var q: String, var sl: String, var tl: String) {
    fun equals(q: String, sl: String, tl: String?): Boolean {
        return q == this.q && sl == this.sl && tl == this.tl
    }
}