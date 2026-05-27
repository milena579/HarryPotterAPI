package com.example.harrypotterapi.API

import com.example.harrypotterapi.Model.Estudante
import com.example.harrypotterapi.Model.Feitico
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HPApiService {
    @GET("api/characters/house/{house}")

    suspend fun getStudentsByHouse(@Path("house") house: String): Response<List<Estudante>>

    @GET("spells")
    suspend fun getSpells(): Response<List<Feitico>>
}