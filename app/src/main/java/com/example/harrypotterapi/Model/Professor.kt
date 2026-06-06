package com.example.harrypotterapi.Model

import com.google.gson.annotations.SerializedName

data class Professor(
    @SerializedName("name")
    var nome: String = "",
    @SerializedName("alternate_names")
    var nomesAlternativos: List<String> = emptyList(),
    @SerializedName("species")
    var especie: String = "",
    @SerializedName("house")
    var casa: String = ""
)