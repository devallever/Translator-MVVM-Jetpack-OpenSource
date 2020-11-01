package org.xm.app.translator.opensource.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.allever.lib.common.app.BaseFragment
import com.allever.lib.common.util.log
import kotlinx.android.synthetic.main.fragment_translate.*
import kotlinx.android.synthetic.main.include_select_language_bar.*
import kotlinx.coroutines.*
import org.xm.app.translator.opensource.R
import org.xm.app.translator.opensource.app.Global
import org.xm.app.translator.opensource.function.TranslationHelper
import org.xm.app.translator.opensource.function.language.Lang
import org.xm.app.translator.opensource.mvvm.model.TranslationResponse
import org.xm.app.translator.opensource.mvvm.repository.Repository
import org.xm.app.translator.opensource.mvvm.repository.network.NetworkManager
import org.xm.app.translator.opensource.mvvm.repository.network.ServiceCreator
import org.xm.app.translator.opensource.mvvm.repository.network.TranslateApiService
import org.xm.app.translator.opensource.mvvm.viewmodel.TranslateViewModel

class TranslateFragment : BaseFragment() {

    private val mMainCoroutine = CoroutineScope(Dispatchers.Main)

    private val mViewModel by lazy {
        ViewModelProvider(this).get(TranslateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_translate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val translateApiService = ServiceCreator.create(TranslateApiService::class.java)
        mMainCoroutine.launch {
            //直接使用Retrofit请求
//            val translateResult = translateApiService.translate("你好", sl = Lang.CHINESE.CODE, tl = Lang.ENGLISH.CODE)
//            if (translateResult.isSuccessful) {
//                refreshUI(translateResult.body())
//            }


            //使用 NetworkManager
//            val translateResult = NetworkManager.translate("你好", sl = Lang.CHINESE.CODE, tl = Lang.ENGLISH.CODE)
//            if (translateResult.isSuccessful) {
//                refreshUI(translateResult.body())
//            }


            //使用ViewModel + LiveData
        }



        mViewModel.translateLiveData.observe(viewLifecycleOwner, Observer { result ->
            val translateResponse = result.getOrNull()
            refreshUI(translateResponse)
        })

        mViewModel.translate("你好", sl = Lang.CHINESE.CODE, tl = Lang.ENGLISH.CODE)

        return

        etInputContent.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                val editable = etInputContent.text
                if (editable != null) {
                    hideKeyboard()
                    translate()
                    return@OnEditorActionListener true
                }
            }

            false
        })

        etInputContent.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 0) {
                    setVisibility(ivInputClose, false)
                    setVisibility(llResultContainer, false)
                } else {
                    setVisibility(ivInputClose, true)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}


        })

    }

    private fun translate() {
        val content = etInputContent.text?.toString()?:""
        if (content.isEmpty()) {
            setVisibility(llResultContainer, false)
            llResultContainer.visibility = View.GONE
            return
        } else {
            setVisibility(llResultContainer, true)
        }

        val sl = Global.langKeyCodeMap[tvSrcLanguage.text] ?: mViewModel.defaultLang.CODE
        val tl = Global.langKeyCodeMap[tvTranslateLanguage.text] ?: mViewModel.defaultLang.CODE
        mViewModel.translate(content, sl, tl)
    }

    private fun refreshUI(bean: TranslationResponse?) {
        log(bean?.toString() ?: "null")
        bean?.let {
            setVisibility(llResultContainer, true)

            //发音行显示文本语言
            tvSoundTranslateLanguage.text = tvTranslateLanguage.text
            tvSoundSrcLanguage.text = TranslationHelper.getSrcLang(bean)
            tvSrcText.text = TranslationHelper.getSrcText(bean)
            tvSrcSymbol.text = TranslationHelper.getSrcSymbol(bean)
            tvTranslateText.text = TranslationHelper.getTranslateText(bean)
            tvTranslateSymbol.text = TranslationHelper.getTranslateSymbol(bean)
            tvResult.text = TranslationHelper.getDictText(bean)
        }

    }

    private fun hideKeyboard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive) {
            inputMethodManager.hideSoftInputFromWindow(
                activity?.currentFocus?.windowToken, 0
            )
        }
    }

    companion object {
        private const val EXTRA_SRC_TEXT = "EXTRA_SRC_TEXT"
        fun newInstance(srcText: String = ""): TranslateFragment {
            val fragment = TranslateFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_SRC_TEXT, srcText)
            fragment.arguments = bundle
            return fragment
        }
    }
}