package br.upf.ads.mercadoestoque

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.room.Room
import com.example.appaula.R
import com.example.appaula.dao.AppDatabase
import com.example.appaula.dao.User

// Tela de entrada onde o usuário faz login para acessar o sistema
class LoginActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtIrCadastro: TextView

    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Liga as variáveis do código com os elementos visuais do XML
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        btnLogin = findViewById(R.id.btnLogin)
        txtIrCadastro = findViewById(R.id.txtIrCadastro)

        // Prepara a conexão com o banco de dados Room
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mercado_estoque_db"
        ).allowMainThreadQueries().build()

        // Garante que exista ao menos um usuário para teste inicial
        criarUsuarioPadrao()

        // Configura o clique do botão de login
        btnLogin.setOnClickListener {
            fazerLogin()
        }

        // Configura o texto clicável para quem ainda não tem conta
        txtIrCadastro.setOnClickListener {
            val intent = Intent(this, CadastroUsuarioActivity::class.java)
            startActivity(intent)
        }
    }

    // Cria um usuário administrador caso o banco esteja vazio
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

    // Valida as credenciais e decide se permite a entrada
    private fun fazerLogin() {
        val email = editEmail.text.toString().trim()
        val senha = editSenha.text.toString()

        // Verifica se os campos foram preenchidos
        if (email.isBlank() || senha.isBlank()) {
            Toast.makeText(this, "Informe email e senha!", Toast.LENGTH_SHORT).show()
            return
        }

        // Tenta buscar o usuário no banco com as credenciais informadas
        val usuario = db?.userDao()?.getUser(email, senha)

        if (usuario != null) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
            // Se der certo, vai para a tela principal e fecha a de login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Se errar, mostra um aviso
            Toast.makeText(this, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
        }
    }
}