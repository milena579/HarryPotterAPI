package com.example.harrypotterapi.Controller

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.harrypotterapi.Model.Feitico
import com.example.harrypotterapi.R

class DetalhesFeitico : AppCompatActivity() {

    private lateinit var txtNome: TextView
    private lateinit var txtDescricao: TextView
    private lateinit var txtVoltar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datalhes_feitico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detalhesFeitico)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtNome = findViewById(R.id.txtNome)
        txtDescricao = findViewById(R.id.txtDescricao)
        txtVoltar = findViewById(R.id.txtVoltar)

        val feitico = intent.getSerializableExtra("feitico") as? Feitico

        if (feitico != null) {
            txtNome.text = feitico.name
            txtDescricao.text = feitico.description
        } else {
            txtNome.text = "-"
            txtDescricao.text = "-"
        }

        txtVoltar.setOnClickListener {
            finish()
        }
    }
}