package com.saadkhan.mvvmuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.saadkhan.mvvmuserapp.databinding.UserAdapterLayoutBinding
import com.saadkhan.mvvmuserapp.model.User
import com.saadkhan.mvvmuserapp.utils.Companion.Companion.userObject
import com.saadkhan.mvvmuserapp.utils.getUsername
import javax.inject.Inject

class UserAdapter @Inject constructor() :
    ListAdapter<User, UserAdapter.ImageViewHolder>(diffCallback) {

    inner class ImageViewHolder(val binding: UserAdapterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            UserAdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currChar = getItem(position)
        holder.binding.apply {
            holder.itemView.apply {
                tvName.text = currChar.name?.getUsername() ?: ""
                tvEmail.text = "${currChar?.email}"
                tvPhone.text = "${currChar?.phone}"

                userImage.load(currChar?.picture?.large) {
                    crossfade(true)
                    crossfade(1000)
                }

                this.setOnClickListener {
                    userObject = getItem(position)
                    mListener?.onItemClick()
                }
            }
        }
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick()
    }
}