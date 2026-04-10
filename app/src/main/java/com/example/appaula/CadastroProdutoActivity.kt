package br.upf.ads.mercadoestoque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.upf.ads.mercadoestoque.api.RetrofitClient
import br.upf.ads.mercadoestoque.domain.Produto
import com.example.appaula.R
import kotlinx.coroutines.launch

class CadastroProdutoActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editQuantidade: EditText
    private lateinit var editPreco: EditText
    private lateinit var btnSalvar: Button

    // Para edição, pode receber um produto via Intent
    private var produtoEmEdicao: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        // Ativa o botão voltar na Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inicializa componentes da UI
        editNome = findViewById(R.id.editNome)
        editQuantidade = findViewById(R.id.editQuantidade)
        editPreco = findViewById(R.id.editPreco)
        btnSalvar = findViewById(R.id.btnSalvar)

        // Verifica se recebeu um produto para edição
        produtoEmEdicao = intent.getSerializableExtra("produto") as? Produto
        produtoEmEdicao?.let { produto ->
            editNome.setText(produto.nome)
            editQuantidade.setText(produto.quantidade.toString())
            editPreco.setText(produto.preco.toString())
            supportActionBar?.title = "Editar Produto"
        }

        // Evento do botão Salvar
        btnSalvar.setOnClickListener {
            val nome = editNome.text.toString()
            val quantidadeStr = editQuantidade.text.toString()
            val precoStr = editPreco.text.toString()

            if (nome.isBlank() || quantidadeStr.isBlank() || precoStr.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantidade = quantidadeStr.toIntOrNull() ?: 0
            val preco = precoStr.toDoubleOrNull() ?: 0.0

            lifecycleScope.launch {
                try {
                    if (produtoEmEdicao == null) {
                        // Criar novo produto
                        val novoProduto = Produto(
                            id = null,
                            nome = nome,
                            quantidade = quantidade,
                            preco = preco
                        )
                        RetrofitClient.instance.criarProduto(novoProduto)
                        Toast.makeText(this@CadastroProdutoActivity,
                            "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    } else {
                        // Atualizar produto existente
                        produtoEmEdicao?.id?.let { id ->
                            val produtoAtualizado = Produto(
                                id = id,
                                nome = nome,
                                quantidade = quantidade,
                                preco = preco
                            )
                            RetrofitClient.instance.atualizarProduto(id, produtoAtualizado)
                            Toast.makeText(this@CadastroProdutoActivity,
                                "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    finish() // Volta para a tela anterior
                } catch (e: Exception) {
                    Toast.makeText(this@CadastroProdutoActivity,
                        "Erro ao salvar: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // Trata o clique no botão voltar da Toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}