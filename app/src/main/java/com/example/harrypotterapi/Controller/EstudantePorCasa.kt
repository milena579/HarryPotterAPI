package com.example.harrypotterapi.Controller

import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterapi.API.RetrofitHelper
import com.example.harrypotterapi.EstudanteAdapter
import com.example.harrypotterapi.R
import kotlinx.coroutines.launch

class EstudantePorCasa : AppCompatActivity() {
    private lateinit var recyclerStudents: RecyclerView
    private lateinit var radioGroupHouses: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_estudante_por_casa)

        recyclerStudents =
            findViewById(R.id.recyclerEstudantes)

        radioGroupHouses =
            findViewById(R.id.radioGroupCasas)

        recyclerStudents.layoutManager =
            LinearLayoutManager(this)

        val rbGrifinoria = findViewById<RadioButton>(R.id.rbGrifinoria)
        val rbSonserina = findViewById<RadioButton>(R.id.rbSonserina)
        val rbLufa = findViewById<RadioButton>(R.id.rbLufa)
        val rbCorvinal = findViewById<RadioButton>(R.id.rbCorvinal)

        val txtGrifinoria = findViewById<TextView>(R.id.txtGrifinoria)
        val txtSonserina = findViewById<TextView>(R.id.txtSonserina)
        val txtLufa = findViewById<TextView>(R.id.txtLufa)
        val txtCorvinal = findViewById<TextView>(R.id.txtCorvinal)

        val radioButtons = listOf(rbGrifinoria, rbSonserina, rbLufa, rbCorvinal)
        val textViews = listOf(txtGrifinoria, txtSonserina, txtLufa, txtCorvinal)

        radioButtons.forEach { radio ->

            radio.setOnClickListener {

                radioButtons.forEach { it.isChecked = false }
                textViews.forEach { it.setTextColor(Color.parseColor("#AAAAAA")) }

                radio.isChecked = true

                val house = when (radio.id) {
                    R.id.rbGrifinoria -> {
                        txtGrifinoria.setTextColor(Color.RED)
                        "gryffindor"
                    }
                    R.id.rbSonserina -> {
                        txtSonserina.setTextColor(Color.GREEN)
                        "slytherin"
                    }
                    R.id.rbLufa -> {
                        txtLufa.setTextColor(Color.YELLOW)
                        "hufflepuff"
                    }
                    R.id.rbCorvinal -> {
                        txtCorvinal.setTextColor(Color.BLUE)
                        "ravenclaw"
                    }
                    else -> ""
                }

                buscarEstudantes(house)
            }
        }
        rbGrifinoria.performClick()
    }

    private fun buscarEstudantes(house: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitHelper.api.getStudentsByHouse(house)

                if (response.isSuccessful) {
                    val students = response.body() ?: emptyList()
                    recyclerStudents.adapter = EstudanteAdapter(students)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}