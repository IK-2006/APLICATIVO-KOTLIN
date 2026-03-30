package com.example.appaula.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class Util {
    companion object {
        fun dialogoAlerta(msg: String, context: Context) {
            var dialogo: AlertDialog.Builder = AlertDialog.Builder(context)
            dialogo.setTitle("Alerta")
            dialogo.setMessage(msg)
            dialogo.setNeutralButton("Ok", null)
            dialogo.show()
        }

        fun dialogoConfirma(msg:String, context: Context, funcaoConfirmar: () -> Unit) {
            var dialogo:AlertDialog.Builder = AlertDialog.Builder(context);
            dialogo.setTitle("Confirmar");
            dialogo.setMessage(msg);
            dialogo.setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    funcaoConfirmar()
                })
            dialogo.setNegativeButton(android.R.string.cancel, null)
            dialogo.show();
        }

    }

}