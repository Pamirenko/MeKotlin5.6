package com.example.mekotlin56.data.remote.apiservises


import com.example.mekotlin56.data.models.PhotoResponseItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val END_POINT = "photos"

interface PhotosApiService {

    @POST(END_POINT)
    fun createNewPhoto(@Body photoResponse: PhotoResponseItem):
            Call<PhotoResponseItem>

    @PUT("$END_POINT/{thumbnailUrl}")
    fun updatePhoto(
        @Path("thumbnailUrl") photosId: Int,
        @Body photoResponseItem: PhotoResponseItem
    ): Call<PhotoResponseItem>

    @FormUrlEncoded
    @PATCH("$END_POINT/{id}")
    fun editPhoto(
        @Field("id") idPhoto: Int,
    ): Call<PhotoResponseItem>

    @DELETE("$END_POINT/{id}")
    fun deletePhotos(
        @Path("id") photosId: Int
    ): Call<PhotoResponseItem>
}