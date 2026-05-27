package com.example.harrypotterapi.Model

import com.google.gson.annotations.SerializedName

data class Estudante(
    @SerializedName("name")
    var nome: String = "",
    @SerializedName("house")
    var casa: String = "",
    @SerializedName("image")
    var imagem: String = ""
)
