package br.upf.ads.mercadoestoque

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appaula.R

// Atividade que serve como "casca" para exibir a lista de produtos através de um fragmento
class ListaProdutosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)

        // Configuração da barra de ferramentas com título e botão de voltar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarListaProdutos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Produtos em Estoque"

        // Carrega o fragmento da lista apenas na primeira criação da atividade
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerProdutos, ListaProdutosFragment())
                .commit()
        }
    }

    // Define o comportamento do botão de voltar na barra superior
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}