package com.catchpig.mvp.base.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.catchpig.mvp.R
import com.catchpig.mvp.apt.KotlinMvpCompiler
import com.catchpig.mvp.base.BaseContract
import com.catchpig.mvp.controller.LoadingViewController
import kotlinx.android.synthetic.main.view_root.*
import luyao.util.ktx.ext.longToast

/**
 * 创建时间:2019/4/4 00:09<br></br>
 * 创建人: 李涛<br></br>
 * 修改人: 李涛<br></br>
 * 修改时间: 2019/4/4 00:09<br></br>
 * 描述:
 * --------------状态栏----------------
 * 请使用注解[com.catchpig.annotation.StatusBar]
 * 想让注解不可用,请设置[com.catchpig.annotation.StatusBar.enabled]为true
 * --------------状态栏----------------
 *
 * --------------标题栏----------------
 * 请使用注解[com.catchpig.annotation.Title]
 * --------------标题栏----------------
 *
 * --------------标题栏右边按钮点击事件---------------
 * 第一个文字按钮点击事件,请方法上实现以下注解
 * @[com.catchpig.annotation.OnClickFirstText]
 *
 * 第一个图标按钮的点击事件,请方法上实现以下注解
 * @[com.catchpig.annotation.OnClickFirstDrawable]
 *
 * 第二个文字按钮的点击事件,请方法上实现以下注解
 * @[com.catchpig.annotation.OnClickSecondText]
 *
 * 第二个图标按钮的点击事件,请方法上实现以下注解
 * @[com.catchpig.annotation.OnClickSecondDrawable]
 * --------------标题栏右边按钮点击事件---------------
 *
 */
abstract class BaseActivity : AppCompatActivity(), BaseContract.View {
    private var loadingViewController: LoadingViewController? = null
    override fun baseActivity(): BaseActivity? {
        return this
    }

    override fun activity(): Activity {
        return this
    }

    override fun applicationContext(): Context {
        return applicationContext
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_root)
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        KotlinMvpCompiler.inject(this)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
    }

    @CallSuper
    override fun onRestart() {
        super.onRestart()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun setContentView(view: View?) {
        layout_body?.let {
            it.addView(view, 0, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            loadingViewController = LoadingViewController(this, it)
        }
    }

    override fun setContentView(layoutResID: Int) {
        setContentView(View.inflate(this, layoutResID, null))
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    override fun loadingView(isDialog: Boolean) {
        loadingViewController?.let {
            if (isDialog) {
                it.loadingDialog()
            } else {
                it.loadingView()
            }
        }
    }

    override fun hideLoadingView() {
        loadingViewController?.let {
            it.hideLoading()
        }
    }

    override fun closeActivity() {
        finish()
    }

    override fun toast(text: String, isLong: Boolean) {
        if (isLong) {
            longToast(text)
        } else {
            toast(text)
        }
    }
}
