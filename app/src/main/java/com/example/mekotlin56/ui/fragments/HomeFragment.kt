package com.example.mekotlin56.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mekotlin56.R
import com.example.mekotlin56.databinding.FragmentHomeBinding
import com.example.mekotlin56.ui.adapters.PhotosAdapter
import com.example.mekotlin56.ui.viewmodels.PhotosViewModels

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val photosAdapter = PhotosAdapter()
    private val viewModels: PhotosViewModels by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initialize()
        toRequestFragment()
        deletePhotos()
    }

    private fun observe() {
        viewModels.photosLiveData.observe(viewLifecycleOwner) { photos ->
            photosAdapter.setPhotoList(photos)
        }
    }

    private fun initialize() {
        binding.rvJson.adapter = photosAdapter
    }

    private fun toRequestFragment() {
        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_requestsFragment)
        }
    }

    private fun deletePhotos() {
        photosAdapter.setOnItemLongClickListener(object :
            PhotosAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                val photoToDelete = photosAdapter._photoList[position]
                viewModels.deletePhotos(
                    photoToDelete,
                    onFailure = { message: String ->
                        Log.e("failureDelete", message)
                    })
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}