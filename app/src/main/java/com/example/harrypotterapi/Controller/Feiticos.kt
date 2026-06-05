package com.example.harrypotterapi.Controller

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
import kotlin.jvm.java

class Feiticos : AppCompatActivity() {

    private lateinit var recyclerFeiticos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feiticos)

        recyclerFeiticos =
            findViewById(R.id.recyclerFeiticos)

        recyclerFeiticos.layoutManager =
            LinearLayoutManager(this)

        buscarFeiticos()
    }

    private fun buscarFeiticos() {

        lifecycleScope.launch {

            try {

                val response =
                    RetrofitHelper.api.getSpells()

                if(response.isSuccessful) {

                    val lista =
                        response.body() ?: emptyList()

                    recyclerFeiticos.adapter =
                        FeiticoAdapter(lista) { spell ->

                            val intent = Intent(
                                this@Feiticos,
                                DatalhesFeitico::class.java
                            )

                            intent.putExtra(
                                "nome",
                                spell.name
                            )

                            intent.putExtra(
                                "descricao",
                                spell.description
                            )

                            startActivity(intent)
                        }
                }

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}