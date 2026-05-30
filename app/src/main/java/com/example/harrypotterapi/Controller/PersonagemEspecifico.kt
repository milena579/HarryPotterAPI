package com.example.harrypotterapi.Controller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.example.harrypotterapi.API.RetrofitHelper
import com.example.harrypotterapi.R
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class PersonagemEspecifico : AppCompatActivity() {

    private lateinit var etPersonagemId: TextInputEditText
    private lateinit var btnBuscar: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var cardResultado: CardView
    private lateinit var txtErro: TextView

    private lateinit var imgPersonagem: ImageView
    private lateinit var txtNomePersonagem: TextView
    private lateinit var txtEspecie: TextView
    private lateinit var txtCasa: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personagem_especifico)

        etPersonagemId    = findViewById(R.id.etPersonagemId)
        btnBuscar         = findViewById(R.id.btnBuscar)
        progressBar       = findViewById(R.id.progressBar)
        cardResultado     = findViewById(R.id.cardResultado)
        txtErro           = findViewById(R.id.txtErro)
        imgPersonagem     = findViewById(R.id.imgPersonagem)
        txtNomePersonagem = findViewById(R.id.txtNomePersonagem)
        txtEspecie        = findViewById(R.id.txtEspecie)
        txtCasa           = findViewById(R.id.txtCasa)

        btnBuscar.setOnClickListener {
            val id = etPersonagemId.text.toString().trim()
            if (id.isNotEmpty()) {
                buscarPersonagem(id)
            }
        }
    }

    private fun buscarPersonagem(id: String) {
        cardResultado.visibility = View.GONE
        txtErro.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = RetrofitHelper.api.getCharacterById(id)

                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val lista = response.body()
                    if (!lista.isNullOrEmpty()) {
                        val personagem = lista[0]

                        txtNomePersonagem.text = personagem.nome.ifEmpty { "—" }
                        txtEspecie.text        = personagem.especie.ifEmpty { "—" }
                        txtCasa.text           = personagem.casa.ifEmpty { "—" }

                        if (personagem.imagem.isNotEmpty()) {
                            Picasso.get()
                                .load(personagem.imagem)
                                .placeholder(R.drawable.hp_logo)
                                .into(imgPersonagem)
                        } else {
                            imgPersonagem.setImageResource(R.drawable.defaulthp)
                        }

                        cardResultado.visibility = View.VISIBLE
                    } else {
                        mostrarErro()
                    }
                } else {
                    mostrarErro()
                }

            } catch (e: Exception) {
                progressBar.visibility = View.GONE
                e.printStackTrace()
                mostrarErro()
            }
        }
    }

    private fun mostrarErro() {
        txtErro.visibility = View.VISIBLE
        cardResultado.visibility = View.GONE
    }
}