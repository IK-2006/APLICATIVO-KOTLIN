package br.upf.ads.mercadoestoque

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.upf.ads.mercadoestoque.domain.Produto
import com.example.appaula.R
import com.example.appaula.dao.AppDatabase
import com.example.appaula.util.Util

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var listaProdutos: MutableList<Produto> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>
    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarListaProdutos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Produtos em Estoque"

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mercado_estoque_db"
        ).allowMainThreadQueries().build()

        listView = findViewById(R.id.listViewProdutos)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        carregarProdutos()

        listView.setOnItemClickListener { _, _, position, _ ->
            val produto = listaProdutos[position]
            val intent = Intent(this, CadastroProdutoActivity::class.java)
            intent.putExtra("produto", produto)
            startActivity(intent)
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val produto = listaProdutos[position]
            Util.dialogoConfirma("Deseja excluir o produto ${produto.nome}?", this) {
                excluirProduto(produto)
            }
            true
        }
    }

    private fun carregarProdutos() {
        listaProdutos = db?.produtoDao()?.getAll()?.toMutableList() ?: mutableListOf()
        atualizarLista()
    }

    private fun excluirProduto(produto: Produto) {
        db?.produtoDao()?.delete(produto)
        Toast.makeText(this, "${produto.nome} excluído!", Toast.LENGTH_SHORT).show()
        carregarProdutos()
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

    override fun onResume() {
        super.onResume()
        carregarProdutos()
    }
}