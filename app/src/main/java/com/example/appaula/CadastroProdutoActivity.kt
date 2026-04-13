package br.upf.ads.mercadoestoque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.upf.ads.mercadoestoque.domain.Produto
import com.example.appaula.R
import com.example.appaula.dao.AppDatabase

// Tela responsável tanto por cadastrar novos produtos quanto por editar os já existentes
class CadastroProdutoActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editQuantidade: EditText
    private lateinit var editPreco: EditText
    private lateinit var btnSalvar: Button

    private var produtoEmEdicao: Produto? = null
    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        // Configura a barra de topo com botão de voltar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarCadastroProduto)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cadastrar Produto"

        // Faz a ligação com os campos de texto e o botão do layout
        editNome = findViewById(R.id.editNome)
        editQuantidade = findViewById(R.id.editQuantidade)
        editPreco = findViewById(R.id.editPreco)
        btnSalvar = findViewById(R.id.btnSalvar)

        // Inicializa o banco de dados
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mercado_estoque_db"
        ).allowMainThreadQueries().build()

        // Verifica se recebemos um produto para edição (vindo da lista)
        produtoEmEdicao = intent.getSerializableExtra("produto") as? Produto
        produtoEmEdicao?.let { produto ->
            // Se houver um produto, preenche os campos com os dados atuais dele
            editNome.setText(produto.nome)
            editQuantidade.setText(produto.quantidade.toString())
            editPreco.setText(produto.preco.toString())
            supportActionBar?.title = "Editar Produto"
        }

        // Define a ação do botão de salvar
        btnSalvar.setOnClickListener {
            salvarProduto()
        }
    }

    // Função que valida os campos e salva os dados no banco
    private fun salvarProduto() {
        val nome = editNome.text.toString().trim()
        val quantidadeStr = editQuantidade.text.toString().trim()
        val precoStr = editPreco.text.toString().trim()

        // Validação simples para não deixar campos vazios
        if (nome.isBlank() || quantidadeStr.isBlank() || precoStr.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        // Tenta converter os valores para números
        val quantidade = quantidadeStr.toIntOrNull()
        val preco = precoStr.toDoubleOrNull()

        // Valida se os números são válidos
        if (quantidade == null || quantidade < 0) {
            Toast.makeText(this, "Informe uma quantidade válida!", Toast.LENGTH_SHORT).show()
            return
        }

        if (preco == null || preco < 0) {
            Toast.makeText(this, "Informe um preço válido!", Toast.LENGTH_SHORT).show()
            return
        }

        // Se não temos um 'produtoEmEdicao', criamos um novo registro
        if (produtoEmEdicao == null) {
            val novoProduto = Produto(
                nome = nome,
                quantidade = quantidade,
                preco = preco
            )
            db?.produtoDao()?.insertOne(novoProduto)
            Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
        } else {
            // Se já existia, apenas atualizamos os dados do objeto e salvamos no banco
            produtoEmEdicao?.nome = nome
            produtoEmEdicao?.quantidade = quantidade
            produtoEmEdicao?.preco = preco
            db?.produtoDao()?.updateOne(produtoEmEdicao!!)
            Toast.makeText(this, "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        // Fecha a tela e volta para a anterior
        finish()
    }

    // Faz o botão de voltar da Toolbar funcionar corretamente
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}