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

    // Usando 'lateinit' para inicializar depois no onCreate (mais comum em Kotlin)
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuração visual da barra de status
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializando os campos de texto
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
    }

    fun fazerLogin(view: View?) {
        val email = editEmail.text.toString()
        val senha = editSenha.text.toString()

        // Verificando as credenciais
        if (email == "jaqson@upf.br" && senha == "123456") {
            Toast.makeText(applicationContext, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()

            // --- AQUI ESTÁ A CORREÇÃO ---
            // Agora ele aponta para a sua nova tela de listagem de estoque
            val intent = Intent(this, EstoqueListarActivity::class.java)
            startActivity(intent)

            // Finaliza a MainActivity para o usuário não voltar ao login apertando "voltar"
            finish()

        } else {
            Toast.makeText(applicationContext, "E-mail ou senha incorretos", Toast.LENGTH_LONG).show()
        }
    }
}