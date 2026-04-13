package com.example.appaula.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

// Classe utilitária com funções prontas para mostrar janelas de aviso e confirmação
class Util {
    companion object {
        
        // Mostra um alerta simples com um botão de "Ok"
        fun dialogoAlerta(msg: String, context: Context) {
            var dialogo: AlertDialog.Builder = AlertDialog.Builder(context)
            dialogo.setTitle("Alerta")
            dialogo.setMessage(msg)
            dialogo.setNeutralButton("Ok", null)
            dialogo.show()
        }

        // Mostra uma pergunta com botões de "Ok" e "Cancelar"
        // Executa uma função específica apenas se o usuário clicar em "Ok"
        fun dialogoConfirma(msg:String, context: Context, funcaoConfirmar: () -> Unit) {
            var dialogo:AlertDialog.Builder = AlertDialog.Builder(context);
            dialogo.setTitle("Confirmar");
            dialogo.setMessage(msg);
            dialogo.setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    funcaoConfirmar() // Executa a ação desejada (ex: excluir um item)
                })
            dialogo.setNegativeButton(android.R.string.cancel, null)
            dialogo.show();
        }

    }

}