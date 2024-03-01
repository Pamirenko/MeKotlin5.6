package com.example.mekotlin56.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mekotlin56.R
import com.example.mekotlin56.data.models.PhotoResponseItem
import com.example.mekotlin56.databinding.FragmentRequestsBinding
import com.example.mekotlin56.ui.viewmodels.PhotosViewModels

class RequestsFragment : Fragment() {
    private var _binding: FragmentRequestsBinding? = null
    private val binding get() = _binding!!
    private val viewModels by viewModels<PhotosViewModels>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createPhoto()
        updatePhotos()
        editPhotos()
        deletePhotos()
    }

    private fun createPhoto() = with(binding) {
        btnPost.setOnClickListener {
            viewModels.createNewPhoto(
                photo = PhotoResponseItem(
                    title = "car",
                    url = "https://via.placeholder.com/600/92c952",
                    thumbnailUrl = "no",
                    albumId = 1
                ),
                onFailure = { error ->
                    Log.e("tag", "error:${error}")
                })
            findNavController().navigateUp()

        }
    }

    private fun updatePhotos() = with(binding) {
        btnPut.setOnClickListener {
            Log.e("click", "ошибка")
            viewModels.updatePhotos(
                photosId = 3,
                photoResponseItem = PhotoResponseItem(
                    id = 3,
                    title = "red",
                    url = "",
                    thumbnailUrl = "",
                    albumId = 1
                ),
                onSuccess = { photoResponse ->
                    findNavController().navigateUp()
                    Toast.makeText(requireContext(), photoResponse.toString(), Toast.LENGTH_SHORT).show()
                },
                onFailure = { error ->
                    Log.e("error", error)
                }
            )
        }
    }


    private fun editPhotos() = with(binding) {
        btnPath.setOnClickListener {
            val parameter = PhotoResponseItem(
                id = 20,
                title = "mar",
                url = "https://via.placeholder.com/600/92c952",
                thumbnailUrl = "no",
                albumId = 10
            )
            viewModels.editPhoto(
                photoParams = parameter,
                photoId = parameter.id,
                onFailure = { error ->
                    Log.e("update", "error:${error}")
                }
            )
            findNavController().navigateUp()
        }
    }

    private fun deletePhotos() = with(binding) {
        btnDelete.setOnClickListener {
            viewModels.deletePhotos(
                deletePhoto = PhotoResponseItem(
                    title = "delete",
                    url = "delete",
                    thumbnailUrl = "delete",
                    albumId = 1
                ),
                onFailure = { error ->
                }
            )
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}