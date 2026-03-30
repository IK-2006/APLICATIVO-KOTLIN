package com.example.appaula

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var editEmail: EditText? = null;
    var editSenha: EditText? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editEmail = findViewById<EditText>(R.id.editEmail)
        editSenha = findViewById<EditText>(R.id.editSenha)
    }

    fun fazerLogin(view: View?) {
        val email = editEmail?.text.toString()
        val senha = editSenha?.text.toString()
        if (email.equals("jaqson@upf.br") && senha.equals("123456")){
            Toast.makeText(getApplicationContext(), "Autenticado com sucesso", Toast.LENGTH_LONG).show();
            // SE autenticou com sucesso chamar a segunda tela
            val intent = Intent(this, UsuarioApiActivity::class.java)
            startActivity(intent)
            finish() // tira a main da pilha

        } else {
            Toast.makeText(getApplicationContext(), "autenticação falhou", Toast.LENGTH_LONG).show();
        }
    }

}