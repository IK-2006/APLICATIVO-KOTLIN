package br.upf.ads.mercadoestoque

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appaula.R

class LoginActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa os componentes da UI
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        btnLogin = findViewById(R.id.btnLogin)

        // Evento de clique do botão Login
        btnLogin.setOnClickListener {
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            // Simulação de autenticação (usuário fixo)
            if (email == "admin@mercado.com" && senha == "123456") {
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                // Navega para a tela principal
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Fecha a tela de login
            } else {
                Toast.makeText(this, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}