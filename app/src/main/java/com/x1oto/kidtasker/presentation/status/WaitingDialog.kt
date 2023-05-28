package com.x1oto.kidtasker.presentation.status

import android.app.ProgressDialog
import android.content.Context

class WaitingDialog(private val context: Context) {
    private var progressDialog: ProgressDialog? = null

    init {
        createProgressDialog()
    }

    private fun createProgressDialog() {
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Please, wait...")
        progressDialog?.setCancelable(false)
    }

    fun show() {
        progressDialog?.show()
    }

    fun dismiss() {
        progressDialog?.dismiss()
    }
}
