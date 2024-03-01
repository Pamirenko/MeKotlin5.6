package com.example.mekotlin56.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mekotlin56.R
import com.example.mekotlin56.data.models.PhotoResponseItem
import com.example.mekotlin56.databinding.ItemPhotoBinding
import com.example.mekotlin56.utils.ErganAdapter

class PhotosAdapter : ErganAdapter<PhotosAdapter.PhotoViewHolder>() {

    var _photoList = emptyList<PhotoResponseItem>()

    private var onItemLongClickListener: OnItemLongClickListener? = null

    fun setPhotoList(photoList: List<PhotoResponseItem>) {
        this._photoList = photoList
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(photoResponseItem: PhotoResponseItem) = with(binding) {
            val imageUrl = photoResponseItem.url
            Glide.with(ivCover.context)
                .load(imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(ivCover)
            tvTitle.text = photoResponseItem.title
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemLongClickListener?.onItemLongClick(position)
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount() = _photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.onBind(_photoList[position])
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.onItemLongClickListener = listener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }
}