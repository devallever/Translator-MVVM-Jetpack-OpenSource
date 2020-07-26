package org.xm.app.translator.opensource.ui

import android.os.Bundle
import com.allever.lib.common.app.BaseActivity
import org.xm.app.translator.opensource.R

class MainDrawerActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drawer)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, TranslateFragment.newInstance())
        transaction.commit()
    }
}