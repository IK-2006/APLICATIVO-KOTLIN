package br.upf.ads.mercadoestoque

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appaula.R

// Tela principal do aplicativo que serve como um menu de navegação
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Configura a barra de ferramentas (Toolbar) no topo da tela
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Mercado Estoque"
    }

    // Carrega o arquivo de menu (ícones e opções) na Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Lida com o clique em cada item do menu superior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_listar -> {
                // Abre a tela que mostra a lista de produtos
                val intent = Intent(this, ListaProdutosActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_cadastrar -> {
                // Abre a tela para cadastrar um novo produto
                val intent = Intent(this, CadastroProdutoActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_sair -> {
                // Faz o "logout", voltando para o Login e limpando o histórico de telas
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