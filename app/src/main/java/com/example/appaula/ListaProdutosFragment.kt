package br.upf.ads.mercadoestoque

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import br.upf.ads.mercadoestoque.domain.Produto
import com.example.appaula.R
import com.example.appaula.dao.AppDatabase
import com.example.appaula.util.Util

// Fragmento responsável por exibir a listagem de produtos cadastrados
class ListaProdutosFragment : Fragment(R.layout.fragment_lista_produtos) {

    private lateinit var listView: ListView
    private var listaProdutos: MutableList<Produto> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>
    private var db: AppDatabase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa o banco de dados local (Room)
        db = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java,
            "mercado_estoque_db"
        ).allowMainThreadQueries().build()

        // Referencia o componente de lista do layout
        listView = view.findViewById(R.id.listViewProdutos)

        // Configura o adaptador básico para mostrar os textos na lista
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        listView.adapter = adapter

        // Busca os dados iniciais
        carregarProdutos()

        // Define o que acontece ao clicar em um item da lista
        listView.setOnItemClickListener { _, _, position, _ ->
            val produto = listaProdutos[position]
            mostrarOpcoesProduto(produto)
        }
    }

    // Sempre que o fragmento voltar a ficar visível, atualiza a lista
    override fun onResume() {
        super.onResume()
        carregarProdutos()
    }

    // Busca todos os produtos salvos no banco de dados
    private fun carregarProdutos() {
        listaProdutos = db?.produtoDao()?.getAll()?.toMutableList() ?: mutableListOf()
        atualizarLista()
    }

    // Formata os dados para exibição e atualiza o componente visual
    private fun atualizarLista() {
        val nomes = listaProdutos.map {
            "${it.nome} - Qtd: ${it.quantidade} - R$ ${it.preco}"
        }

        adapter.clear()
        adapter.addAll(nomes)
        adapter.notifyDataSetChanged()
    }

    // Abre um diálogo perguntando se o usuário quer editar ou excluir o item
    private fun mostrarOpcoesProduto(produto: Produto) {
        val opcoes = arrayOf("Editar", "Excluir")

        AlertDialog.Builder(requireContext())
            .setTitle("Selecione uma ação")
            .setItems(opcoes) { _, which ->
                when (which) {
                    0 -> abrirEdicao(produto)
                    1 -> confirmarExclusao(produto)
                }
            }
            .show()
    }

    // Leva o usuário para a tela de cadastro passando os dados do produto para edição
    private fun abrirEdicao(produto: Produto) {
        val intent = Intent(requireContext(), CadastroProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }

    // Mostra um alerta de confirmação antes de apagar o registro
    private fun confirmarExclusao(produto: Produto) {
        Util.dialogoConfirma(
            "Deseja excluir o produto ${produto.nome}?",
            requireContext()
        ) {
            excluirProduto(produto)
        }
    }

    // Remove o produto do banco e atualiza a interface
    private fun excluirProduto(produto: Produto) {
        db?.produtoDao()?.delete(produto)
        Toast.makeText(
            requireContext(),
            "${produto.nome} excluído com sucesso!",
            Toast.LENGTH_SHORT
        ).show()
        carregarProdutos()
    }
}