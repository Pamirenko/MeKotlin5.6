package com.example.mekotlin56.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mekotlin56.data.models.PhotoResponseItem
import com.example.mekotlin56.data.repositories.PhotosApiRepositories
import com.example.mekotlin56.data.repositories.PhotosRoomRepositories
import com.example.mekotlin56.utils.App

class PhotosViewModels : ViewModel() {

    private val photosApiRepositories = PhotosApiRepositories()
    val photosRoomRepositories = PhotosRoomRepositories()
    val photosLiveData: LiveData<MutableList<PhotoResponseItem>> = App.db.photosDao().getAll()

    fun createNewPhoto(
        photo: PhotoResponseItem,
        onFailure: (message: String) -> Unit
    ) {

        photosApiRepositories.createNewPhoto(
            photo = photo,
            onResponse = {
                photosRoomRepositories.addPhotos(photo)
            },
            onFailure = { message: String, t ->
                onFailure(message)
            })
        photosRoomRepositories.addPhotos(
            PhotoResponseItem(
                id = 4,
                albumId = 2,
                title = "red",
                thumbnailUrl = "",
                url = "https://via.placeholder.com/600/d32776"
            )
        )
    }

    fun updatePhotos(
        photosId: Int,
        photoResponseItem: PhotoResponseItem,
        onSuccess: (photoResponse: PhotoResponseItem) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        photosApiRepositories.updatePhoto(photosId = photosId,
            photoResponseItem = photoResponseItem,
            onResponse = onSuccess,

            onFailure = { message, t ->
                onFailure(message)
            })
        photosLiveData.value.let {
            photosLiveData.value?.set(photosId, photoResponseItem)
        }
    }


    fun editPhoto(
        photoId: Int,
        photoParams: PhotoResponseItem,
        onFailure: (message: String) -> Unit
    ) {
        photosApiRepositories.editPhoto(photoParams = photoParams, onResponse = {
            photosRoomRepositories.editPhoto(photoParams)
            Log.e("tag", "photos:${photosRoomRepositories.getPhotos()}")
        }) { _, t ->
            val message = t.message ?: "Unknown error!"
            Log.e("tag", message)
            onFailure(t.message ?: "unknown error")
        }
        photosLiveData.value.let {
            photosLiveData.value?.set(photoId, photoParams)
        }
    }

    fun deletePhotos(
        deletePhoto: PhotoResponseItem,
        onFailure: (message: String) -> Unit,
    ) {
        if (photosLiveData !== null) {
            photosLiveData.value.let {
                photosLiveData.value?.remove(deletePhoto)

                Log.e("Exception", "exception")
            }
        }
        photosApiRepositories.deletePhoto(
            deletePhoto = deletePhoto,
            onResponse = {
                photosRoomRepositories.deletePhoto(deletePhoto)
            },
            onFailure = { message: String, t ->
                onFailure(message)
            })
    }
}