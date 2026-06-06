package com.example.harrypotterapi.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Feitico(
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
) : Serializable
