package br.upf.ads.mercadoestoque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.appaula.R
import com.example.appaula.dao.AppDatabase
import com.example.appaula.dao.User

// Tela para o cadastro de novos usuários no sistema
class CadastroUsuarioActivity : AppCompatActivity() {

    private lateinit var editNomeCadastro: EditText
    private lateinit var editEmailCadastro: EditText
    private lateinit var editSenhaCadastro: EditText
    private lateinit var editConfirmarSenha: EditText
    private lateinit var btnCadastrarUsuario: Button

    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)

        // Configura a barra superior e ativa o botão de "voltar"
        val toolbar = findViewById<Toolbar>(R.id.toolbarCadastroUsuario)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Cadastro de Usuário"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mapeia os componentes da interface
        editNomeCadastro = findViewById(R.id.editNomeCadastro)
        editEmailCadastro = findViewById(R.id.editEmailCadastro)
        editSenhaCadastro = findViewById(R.id.editSenhaCadastro)
        editConfirmarSenha = findViewById(R.id.editConfirmarSenha)
        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario)

        // Inicializa o acesso ao banco de dados local
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mercado_estoque_db"
        ).allowMainThreadQueries().build()

        // Define a ação ao clicar no botão de cadastro
        btnCadastrarUsuario.setOnClickListener {
            cadastrarUsuario()
        }
    }

    // Lógica para validar os dados e salvar o novo usuário
    private fun cadastrarUsuario() {
        val nome = editNomeCadastro.text.toString().trim()
        val email = editEmailCadastro.text.toString().trim()
        val senha = editSenhaCadastro.text.toString()
        val confirmarSenha = editConfirmarSenha.text.toString()

        // Validação básica: nenhum campo pode estar vazio
        if (nome.isBlank() || email.isBlank() || senha.isBlank() || confirmarSenha.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        // Verifica se o formato do email é válido
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Informe um email válido!", Toast.LENGTH_SHORT).show()
            return
        }

        // Exige uma senha com tamanho mínimo de segurança
        if (senha.length < 6) {
            Toast.makeText(this, "A senha deve ter pelo menos 6 caracteres!", Toast.LENGTH_SHORT).show()
            return
        }

        // Garante que o usuário digitou a mesma senha nos dois campos
        if (senha != confirmarSenha) {
            Toast.makeText(this, "As senhas não conferem!", Toast.LENGTH_SHORT).show()
            return
        }

        // Verifica se o email já está cadastrado para evitar duplicidade
        val existente = db?.userDao()?.getByEmail(email)
        if (existente != null) {
            Toast.makeText(this, "Já existe usuário com esse email!", Toast.LENGTH_SHORT).show()
            return
        }

        // Cria o objeto do usuário e salva no banco
        val novoUsuario = User(
            nome = nome,
            email = email,
            senha = senha
        )

        db?.userDao()?.insertOne(novoUsuario)

        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
        
        // Finaliza a tela e volta para o login
        finish()
    }

    // Controla o clique no botão de voltar da barra de ferramentas
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}