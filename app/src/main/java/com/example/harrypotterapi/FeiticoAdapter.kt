package com.example.harrypotterapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterapi.Model.Feitico

class FeiticoAdapter(

    private val lista: List<Feitico>,
    private val onClick: (Feitico) -> Unit

) : RecyclerView.Adapter<FeiticoAdapter.FeiticoViewHolder>() {
    class FeiticoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome = view.findViewById<TextView>(R.id.txtNome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeiticoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_feitico,
                parent,
                false
            )
        return FeiticoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeiticoViewHolder, position: Int) {
        val spell = lista[position]
        holder.nome.text = spell.name
        holder.itemView.setOnClickListener {
            onClick(spell)
        }
    }

    override fun getItemCount() = lista.size
}