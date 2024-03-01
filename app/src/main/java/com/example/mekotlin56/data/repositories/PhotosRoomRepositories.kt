package com.example.mekotlin56.data.repositories

import com.example.mekotlin56.data.models.PhotoResponseItem
import com.example.mekotlin56.utils.App

class PhotosRoomRepositories {

    fun addPhotos(photoResponseItem: PhotoResponseItem) {
        App.db.photosDao().insertAll(photoResponseItem)
    }

    fun getPhotos() = App.db.photosDao().getAll()

    fun editPhoto(photoResponseItem: PhotoResponseItem) {
        return App.db.photosDao().editPhoto(photoResponseItem)
    }

    fun deletePhoto(photoResponseItem: PhotoResponseItem) {
        return App.db.photosDao().deletePhoto(photoResponseItem)
    }
}