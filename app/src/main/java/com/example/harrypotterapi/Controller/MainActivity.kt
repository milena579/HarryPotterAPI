package com.example.harrypotterapi.Controller

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.harrypotterapi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<LinearLayout>(R.id.btnChar).setOnClickListener {
            startActivity(Intent(this, PersonagemEspecifico::class.java))
        }

        findViewById<LinearLayout>(R.id.btnTeacher).setOnClickListener {
            startActivity(Intent(this, ProfessorEscola::class.java))
        }

        findViewById<LinearLayout>(R.id.btnHouse).setOnClickListener {
            startActivity(Intent(this, EstudantePorCasa::class.java))
        }

        findViewById<LinearLayout>(R.id.btnExit).setOnClickListener {
            finishAffinity()
        }
    }
}