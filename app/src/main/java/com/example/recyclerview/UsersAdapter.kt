package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.ItemUserBinding
import com.example.recyclerview.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var users: List<User> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding){
            tvUserName.text = user.name
            tvUserCompany.text = user.company
            if(user.photo.isNotBlank()){
                Glide.with(ivUserAvatar.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_default_avatar)
                    .error(R.drawable.ic_user_error)
                    .into(ivUserAvatar)
            }else{
                ivUserAvatar.setImageResource(R.drawable.ic_user_default_avatar)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)
}