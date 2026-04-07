package com.example.appaula

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appaula.api.RetrofitClient
import com.example.appaula.domain.Produto
import kotlinx.coroutines.launch
import retrofit2.*

class EstoqueCriarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estoque_criar)

        findViewById<Button>(R.id.btnSalvarProduto).setOnClickListener {
            enviarParaApi()
        }
    }

    private fun enviarParaApi() {
        // Criando o objeto com tratamento para campos vazios
        val produto = Produto(
            nome = findViewById<EditText>(R.id.edtNome).text.toString(),
            categoria = findViewById<EditText>(R.id.edtCategoria).text.toString(),
            validade = findViewById<EditText>(R.id.edtValidade).text.toString(),
            codigo_barras = findViewById<EditText>(R.id.edtCodigo).text.toString(),
            quantidade = findViewById<EditText>(R.id.edtQtd).text.toString().toIntOrNull() ?: 0,
            preco = findViewById<EditText>(R.id.edtPreco).text.toString().toDoubleOrNull() ?: 0.0
        )
        lifecycleScope.launch {
            val ProdutoCriado = RetrofitClient.instance.inserir(produto)
        }
        Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
        finish()
    }


            fun onFailure(call: Call<Produto>, t: Throwable) {
                Toast.makeText(this@EstoqueCriarActivity, "Erro ao salvar", Toast.LENGTH_SHORT).show()
            }
}
