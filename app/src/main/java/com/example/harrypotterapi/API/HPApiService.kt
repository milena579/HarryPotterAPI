package com.example.harrypotterapi.API

import com.example.harrypotterapi.Model.Estudante
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HPApiService {
    @GET("api/characters/house/{house}")
    suspend fun getStudentsByHouse(@Path("house") house: String): Response<List<Estudante>>

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): Response<List<Personagem>>
 
    @GET("api/characters/staff")
    suspend fun getAllStaff(): Response<List<Professor>>
}