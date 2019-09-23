package com.foo.assignment.ui.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.foo.assignment.di.AppViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Ramesh on 2019-06-29.
 */
abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
    }

    abstract fun initViewModel(viewModel: VM)

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    protected fun transparentStatusBar() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
