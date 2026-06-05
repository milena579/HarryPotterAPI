package com.example.harrypotterapi.Controller

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.harrypotterapi.R

class DatalhesFeitico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_datalhes_feitico)

        val txtNome =
            findViewById<TextView>(R.id.txtNome)

        val txtDescricao =
            findViewById<TextView>(R.id.txtDescricao)

        val nome =
            intent.getStringExtra("nome")

        val descricao =
            intent.getStringExtra("descricao")

        txtNome.text = nome
        txtDescricao.text = descricao

        findViewById<TextView>(R.id.txtVoltar)
            .setOnClickListener {

                finish()
            }
    }
}