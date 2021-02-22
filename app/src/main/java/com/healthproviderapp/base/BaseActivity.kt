package com.healthproviderapp.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.healthproviderapp.R

open class BaseActivity : AppCompatActivity() {

    lateinit var dialog: Dialog


    fun showLoading() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)

        val lWindowParams = WindowManager.LayoutParams()
        lWindowParams.copyFrom(dialog.window?.attributes)
        lWindowParams.width =
                WindowManager.LayoutParams.WRAP_CONTENT // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 0)
        dialog.window!!.setBackgroundDrawable(inset)

        dialog.show()
    }

    fun hideLoading() {
        if (dialog.isShowing) {
            dialog.dismiss()
        } else {
            dialog.dismiss()
        }
    }

}
