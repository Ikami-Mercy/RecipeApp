package com.recipeMenu.task2.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.recipeMenu.task2.R


class ProgressDialog {
    private lateinit var dialog: Dialog
    fun show(context: Context): Dialog? {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.progress_dialog, null)
        dialog = Dialog(context, R.style.LoadingDialog)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.show()

        return dialog
    }

    fun dismiss() {
        try {
            dialog.dismiss()
        } catch (e: UninitializedPropertyAccessException) {
            e.printStackTrace()
        }

    }

}