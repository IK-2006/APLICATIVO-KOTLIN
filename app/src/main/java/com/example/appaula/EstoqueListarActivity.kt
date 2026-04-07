package com.example.appaula

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appaula.api.RetrofitClient
import com.example.appaula.domain.Produto
import kotlinx.coroutines.launch
import retrofit2.*

class EstoqueListarActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estoque_listar)

        listView = findViewById(R.id.lstProdutos)
        val btnNovo = findViewById<Button>(R.id.btnAbrirCadastro)

        btnNovo.setOnClickListener {
            startActivity(Intent(this, EstoqueCriarActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun listar() {
        lifecycleScope.launch {
            try {
                val listaProduto = findViewById<ListView>(R.id.lstProdutos)
                val lista = RetrofitClient.instance.getProduto()
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar: ${e.message}")
                Toast.makeText(
                    getApplicationContext(),
                    "Erro ao listar: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}