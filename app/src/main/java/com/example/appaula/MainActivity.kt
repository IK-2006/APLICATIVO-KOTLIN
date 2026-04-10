package br.upf.ads.mercadoestoque

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appaula.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Configura a Toolbar como ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Mercado Estoque"
    }

    // Infla o menu na Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Trata os cliques nos itens do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_listar -> {
                val intent = Intent(this, ListaProdutosActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_cadastrar -> {
                val intent = Intent(this, CadastroProdutoActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_sair -> {
                // Volta para a tela de login e limpa a pilha
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
                Toast.makeText(this, "Saindo...", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}