package com.catchpig.kotlin_mvp.di

import com.catchpig.kotlin_mvp.mvp.main.MainContract
import com.catchpig.kotlin_mvp.mvp.main.model.MainModel
import com.catchpig.kotlin_mvp.mvp.main.presenter.MainPresenter
import com.catchpig.kotlin_mvp.mvp.main.view.MainActivity
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * 创建时间:2019/8/18 0018<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/18 0018<br/>
 * 描述:
 */
val mainModule = module() {
    scope<MainActivity> {
        scoped { (view:MainContract.View)->
            MainPresenter(view,get())
        } bind MainContract.Presenter::class

        scoped {
            MainModel(get(),get())
        } bind MainContract.Model::class
    }
}