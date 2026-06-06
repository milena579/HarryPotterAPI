package com.example.harrypotterapi.Model

import com.google.gson.annotations.SerializedName

data class Personagem(
    @SerializedName("name")
    var nome: String = "",
    @SerializedName("species")
    var especie: String = "",
    @SerializedName("house")
    var casa: String = "",
    @SerializedName("image")
    var imagem: String = ""
)