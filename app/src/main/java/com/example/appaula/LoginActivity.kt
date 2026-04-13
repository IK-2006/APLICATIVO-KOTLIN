package br.upf.ads.mercadoestoque

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.appaula.R
import com.example.appaula.dao.AppDatabase
import com.example.appaula.dao.User

class LoginActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtIrCadastro: TextView

    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        btnLogin = findViewById(R.id.btnLogin)
        txtIrCadastro = findViewById(R.id.txtIrCadastro)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mercado_estoque_db"
        ).allowMainThreadQueries().build()

        criarUsuarioPadrao()

        btnLogin.setOnClickListener {
            fazerLogin()
        }

        txtIrCadastro.setOnClickListener {
            val intent = Intent(this, CadastroUsuarioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun criarUsuarioPadrao() {
        val usuario = db?.userDao()?.getByEmail("admin@mercado.com")
        if (usuario == null) {
            db?.userDao()?.insertOne(
                User(
                    nome = "Administrador",
                    email = "admin@mercado.com",
                    senha = "123456"
                )
            )
        }
    }

    private fun fazerLogin() {
        val email = editEmail.text.toString().trim()
        val senha = editSenha.text.toString()

        if (email.isBlank() || senha.isBlank()) {
            Toast.makeText(this, "Informe email e senha!", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = db?.userDao()?.getUser(email, senha)

        if (usuario != null) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
        }
    }
}