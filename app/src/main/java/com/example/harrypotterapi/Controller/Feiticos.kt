package com.example.harrypotterapi.Controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterapi.API.RetrofitHelper
import com.example.harrypotterapi.FeiticoAdapter
import com.example.harrypotterapi.R
import kotlinx.coroutines.launch

class Feiticos : AppCompatActivity() {

    private lateinit var recyclerFeiticos: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var txtErro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feiticos)

        recyclerFeiticos = findViewById(R.id.recyclerFeiticos)
        progressBar = findViewById(R.id.progressBar)
        txtErro = findViewById(R.id.txtErro)

        recyclerFeiticos.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.feiticos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        carregarFeiticos()
    }

    private fun carregarFeiticos() {
        progressBar.visibility = View.VISIBLE
        txtErro.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val response = RetrofitHelper.api.getSpells()
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val lista = response.body() ?: emptyList()
                    recyclerFeiticos.adapter = FeiticoAdapter(lista) { feitico ->
                        val intent = Intent(
                            this@Feiticos,
                            DetalhesFeitico::class.java
                        )
                        intent.putExtra("feitico", feitico)
                        startActivity(intent)
                    }
                } else {
                    txtErro.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                progressBar.visibility = View.GONE
                e.printStackTrace()
                txtErro.visibility = View.VISIBLE
            }
        }
    }
}
