package org.xm.app.translator.opensource.app

import com.allever.lib.common.app.App

class MyApp : App() {
    override fun onCreate() {
        super.onCreate()

        Global.initLanguage()
    }
}