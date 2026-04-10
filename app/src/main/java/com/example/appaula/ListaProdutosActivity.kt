package br.upf.ads.mercadoestoque

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.upf.ads.mercadoestoque.api.RetrofitClient
import br.upf.ads.mercadoestoque.domain.Produto
import com.example.appaula.R
import kotlinx.coroutines.launch

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var listaProdutos: MutableList<Produto> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Produtos em Estoque"

        listView = findViewById(R.id.listViewProdutos)

        // Configura o adapter para exibir os nomes dos produtos
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        // Carrega os produtos da API
        carregarProdutos()

        // Clique curto: editar produto
        listView.setOnItemClickListener { _, _, position, _ ->
            val produto = listaProdutos[position]
            val intent = Intent(this, CadastroProdutoActivity::class.java)
            intent.putExtra("produto", produto)
            startActivity(intent)
        }

        // Clique longo: excluir produto
        listView.setOnItemLongClickListener { _, _, position, _ ->
            val produto = listaProdutos[position]
            AlertDialog.Builder(this)
                .setTitle("Confirmar exclusão")
                .setMessage("Deseja excluir o produto ${produto.nome}?")
                .setPositiveButton("Sim") { _, _ ->
                    excluirProduto(produto)
                }
                .setNegativeButton("Não", null)
                .show()
            true
        }
    }

    private fun carregarProdutos() {
        lifecycleScope.launch {
            try {
                listaProdutos = RetrofitClient.instance.listarProdutos().toMutableList()
                atualizarLista()
            } catch (e: Exception) {
                Toast.makeText(this@ListaProdutosActivity,
                    "Erro ao carregar produtos: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun excluirProduto(produto: Produto) {
        lifecycleScope.launch {
            try {
                produto.id?.let { id ->
                    val response = RetrofitClient.instance.deletarProduto(id)
                    if (response.isSuccessful) {
                        listaProdutos.remove(produto)
                        atualizarLista()
                        Toast.makeText(this@ListaProdutosActivity,
                            "${produto.nome} excluído!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ListaProdutosActivity,
                            "Erro ao excluir produto", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this@ListaProdutosActivity,
                    "Erro: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun atualizarLista() {
        val nomes = listaProdutos.map { "${it.nome} - Qtd: ${it.quantidade} - R$ ${it.preco}" }
        adapter.clear()
        adapter.addAll(nomes)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // Recarrega a lista ao retornar da edição
    override fun onResume() {
        super.onResume()
        carregarProdutos()
    }
}