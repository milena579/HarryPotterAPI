package com.example.harrypotterapi

import com.example.harrypotterapi.Model.Estudante

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EstudanteAdapter(private val studentList: List<Estudante>) : RecyclerView.Adapter<EstudanteAdapter.StudentViewHolder>() {
    inner class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgStudent: ImageView = itemView.findViewById(R.id.imgEstudante)
        val txtName: TextView = itemView.findViewById(R.id.txtNome)
        val txtHouse: TextView = itemView.findViewById(R.id.txtCasa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estudante, parent, false)

        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]

        holder.txtName.text = student.nome

        holder.txtHouse.text = student.casa

        if (student.imagem.isNotEmpty()) {
            Picasso.get()
                .load(student.imagem)
                .placeholder(R.drawable.hp_logo)
                .into(holder.imgStudent)
        } else {
            holder.imgStudent.setImageResource(R.drawable.defaulthp)
        }
    }

}