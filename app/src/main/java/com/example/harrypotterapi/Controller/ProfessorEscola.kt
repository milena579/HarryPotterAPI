package com.example.harrypotterapi.Controller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.example.harrypotterapi.API.RetrofitHelper
import com.example.harrypotterapi.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class ProfessorEscola : AppCompatActivity() {

    private lateinit var etNomeProfessor: TextInputEditText
    private lateinit var btnBuscar: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var cardResultado: CardView
    private lateinit var txtErro: TextView

    private lateinit var txtNomeProfessor: TextView
    private lateinit var txtNomesAlternativos: TextView
    private lateinit var txtEspecie: TextView
    private lateinit var txtCasa: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_professor_escola)

        etNomeProfessor      = findViewById(R.id.etNomeProfessor)
        btnBuscar            = findViewById(R.id.btnBuscar)
        progressBar          = findViewById(R.id.progressBar)
        cardResultado        = findViewById(R.id.cardResultado)
        txtErro              = findViewById(R.id.txtErro)
        txtNomeProfessor     = findViewById(R.id.txtNomeProfessor)
        txtNomesAlternativos = findViewById(R.id.txtNomesAlternativos)
        txtEspecie           = findViewById(R.id.txtEspecie)
        txtCasa              = findViewById(R.id.txtCasa)

        btnBuscar.setOnClickListener {
            val nome = etNomeProfessor.text.toString().trim()
            if (nome.isNotEmpty()) {
                buscarProfessor(nome)
            }
        }
    }

    private fun buscarProfessor(nomeBusca: String) {
        cardResultado.visibility = View.GONE
        txtErro.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = RetrofitHelper.api.getAllStaff()

                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val lista = response.body() ?: emptyList()

                    val professor = lista.firstOrNull {
                        it.nome.contains(nomeBusca, ignoreCase = true)
                    }

                    if (professor != null) {
                        txtNomeProfessor.text = professor.nome.ifEmpty { "—" }

                        txtNomesAlternativos.text =
                            if (professor.nomesAlternativos.isNotEmpty())
                                professor.nomesAlternativos.joinToString(", ")
                            else "—"

                        txtEspecie.text = professor.especie.ifEmpty { "—" }
                        txtCasa.text    = professor.casa.ifEmpty { "—" }

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