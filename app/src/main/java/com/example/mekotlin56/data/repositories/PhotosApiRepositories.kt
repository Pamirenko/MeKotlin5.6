package com.example.mekotlin56.data.repositories

import android.util.Log
import com.example.mekotlin56.data.RetrofitClient
import com.example.mekotlin56.data.models.PhotoResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosApiRepositories {
    private val apiService = RetrofitClient.photosApiService

    fun createNewPhoto(
        photo: PhotoResponseItem,
        onResponse: (photoResponse: PhotoResponseItem) -> Unit,
        onFailure: (message: String, t: Throwable) -> Unit
    ) {
        Log.e("error", "createNewPhoto: Just test")
        apiService.createNewPhoto(photo).enqueue(object : Callback<PhotoResponseItem> {

            override fun onResponse(
                call: Call<PhotoResponseItem>,
                response: Response<PhotoResponseItem>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    onResponse(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PhotoResponseItem>, t: Throwable) {
                onFailure(t.message ?: "Unknown error!", t)
            }
        })
    }

    fun updatePhoto(
        photosId: Int,
        photoResponseItem: PhotoResponseItem,
        onResponse: (photoResponse: PhotoResponseItem) -> Unit,
        onFailure: (message: String, t: Throwable) -> Unit
    ) {
        apiService.updatePhoto(photosId = photosId, photoResponseItem = photoResponseItem)
            .enqueue(object : Callback<PhotoResponseItem> {
                override fun onResponse(
                    call: Call<PhotoResponseItem>,
                    response: Response<PhotoResponseItem>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        onResponse(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<PhotoResponseItem>, t: Throwable) {
                    onFailure(t.message ?: "unknown error", t)
                }
            })

    }

    fun editPhoto(
        photoParams: PhotoResponseItem,
        onResponse: (photosResponseItem: PhotoResponseItem) -> Unit,
        onFailure: (message: String, t: Throwable) -> Unit
    ) {
        apiService.editPhoto(photoParams.id).enqueue(object : Callback<PhotoResponseItem> {
            override fun onResponse(
                call: Call<PhotoResponseItem>,
                response: Response<PhotoResponseItem>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    onResponse(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PhotoResponseItem>, t: Throwable) {
                onFailure(t.message ?: "Unknown error", t)
            }
        })
    }

    fun deletePhoto(
        deletePhoto: PhotoResponseItem,
        onResponse: (photosResponseItem: PhotoResponseItem) -> Unit,
        onFailure: (message: String, t: Throwable) -> Unit
    ) {
        apiService.deletePhotos(deletePhoto.id).enqueue(object : Callback<PhotoResponseItem> {
            override fun onResponse(
                call: Call<PhotoResponseItem>,
                response: Response<PhotoResponseItem>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    onResponse(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PhotoResponseItem>, t: Throwable) {
                onFailure(t.message ?: "unknown error", t)
            }
        })
    }
}