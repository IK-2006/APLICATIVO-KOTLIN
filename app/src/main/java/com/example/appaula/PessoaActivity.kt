package com.example.appaula

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appaula.domain.Pessoa

class PessoaActivity : AppCompatActivity() {
    var editNome: EditText? = null
    var editIdade:EditText? = null
    var textResultado: TextView? = null
    var editLogs:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pessoa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editNome = findViewById<EditText>(R.id.userNome)
        editIdade = findViewById<EditText>(R.id.editIdade)
        textResultado = findViewById<TextView>(R.id.textResultado)
        editLogs = findViewById<EditText>(R.id.editLogs)

        val buttonProcessar = findViewById<Button>(R.id.buttonProcessar)
        buttonProcessar.setOnClickListener {calcular(buttonProcessar)}

        val buttonLimpar = findViewById<Button>(R.id.buttonLimpar)
        buttonLimpar.setOnClickListener {limpar(buttonLimpar)}

    }

    fun calcular(view: View?) {
        val pes = Pessoa(
            null, editNome?.text.toString(),
            editIdade?.text.toString().toInt()
        )
        textResultado?.text = pes.getDadosPessoa()
        editLogs?.text?.append(pes.getDadosPessoa()+"\n")
    }

    fun limpar(view: View?) {
        editNome?.text?.clear()
        editIdade?.text?.clear()
        textResultado?.text = ""
        editLogs?.text?.clear()
    }

}