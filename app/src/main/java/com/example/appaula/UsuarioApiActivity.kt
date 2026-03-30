package com.example.appaula

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.appaula.dao.AppDatabase
import com.example.appaula.dao.UserDao
import com.example.appaula.domain.User
import com.example.appaula.util.Util

class UsuarioApiActivity : AppCompatActivity() {

    var dao: UserDao? = null
    lateinit var selecionado:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_usuarioapi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializar o dao com a implementação a usar na activity
        dao = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java, "appaula").
        allowMainThreadQueries().build().userDao()

        listar()

        val lstUser = findViewById<ListView>(R.id.lstUser)
        lstUser.setOnItemClickListener {parent, view, position, id ->
            selecionado = parent.getItemAtPosition(position) as User
            findViewById<EditText>(R.id.edtUserNome).setText(selecionado?.nome)
            findViewById<EditText>(R.id.edtUserEmail).setText(selecionado?.email)
            findViewById<EditText>(R.id.edtUserSenha).setText(selecionado?.senha)
        }

        val btnInserirUser = findViewById<Button>(R.id.btnInserirUser)
        btnInserirUser.setOnClickListener {inserir(btnInserirUser)}

        val btnAlterarUser = findViewById<Button>(R.id.btnAlterarUser)
        btnAlterarUser.setOnClickListener {alterar(btnAlterarUser)}

        val btnExcluirUser = findViewById<Button>(R.id.btnExcluirUser)
        btnExcluirUser.setOnClickListener {excluir(btnExcluirUser)}

    }

    fun listar(){
        val lista = dao?.getAll()
        val listaUser = findViewById<ListView>(R.id.lstUser)
        listaUser.adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, lista!!)
    }

    fun inserir(view: View?){
        var cod = dao?.getMaiorId()
        cod = cod!! + 1
        val novo = User(
            cod,
            findViewById<EditText>(R.id.edtUserNome).text.toString(),
            findViewById<EditText>(R.id.edtUserEmail).text.toString(),
            findViewById<EditText>(R.id.edtUserSenha).text.toString()
        )
        dao?.insertOne(novo)
        Toast.makeText(getApplicationContext(),
            "${novo.nome} inserido com sucesso!",
            Toast.LENGTH_LONG).show()
        listar()
        limpar()
    }

    fun alterar(view: View?){
        selecionado?.nome = findViewById<EditText>(R.id.edtUserNome).text.toString()
        selecionado?.email = findViewById<EditText>(R.id.edtUserEmail).text.toString()
        selecionado?.senha = findViewById<EditText>(R.id.edtUserSenha).text.toString()
        dao?.updateOne(selecionado)
        Toast.makeText(getApplicationContext(),
            "${selecionado.nome} alterado com sucesso!",
            Toast.LENGTH_LONG).show()
        listar()
        limpar()
    }
    fun excluir(view: View?){
        Util.dialogoConfirma("Confirma a exclusão de ${selecionado.nome}?",
            this, {
                dao?.delete(selecionado)
                Toast.makeText(getApplicationContext(),
                    "${selecionado.nome} excluído com sucesso!",
                    Toast.LENGTH_LONG).show()
                listar()
                limpar()
            });
    }

    fun limpar() {
        findViewById<EditText>(R.id.edtUserNome).text.clear()
        findViewById<EditText>(R.id.edtUserEmail).text.clear()
        findViewById<EditText>(R.id.edtUserSenha).text.clear()
    }

}