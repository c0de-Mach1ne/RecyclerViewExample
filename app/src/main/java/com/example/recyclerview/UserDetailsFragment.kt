package com.example.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.UserDetailsBinding
import com.example.recyclerview.model.User
import com.github.javafaker.Faker

class UserDetailsFragment : Fragment() {

    private lateinit var binding: UserDetailsBinding
    val faker: Faker = Faker.instance()

    private val user: User
        get() = requireArguments().getSerializable(ARG_USER) as User

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserDetailsBinding.inflate(layoutInflater, container, false)
        binding.tvUserName.text = user.name
        binding.tvUserCompany.text = user.company
        binding.tvTitleDescription.text = "About ${user.company}"
        binding.tvCompanyDescription.text = faker.company().catchPhrase()

        Glide.with(binding.ivCompanyLogo.context)
            .load(user.logo)
            .into(binding.ivCompanyLogo)

        if (user.photo.isNotBlank()) {
            Glide.with(binding.ivUserPhoto.context)
                .load(user.photo)
                .circleCrop()
                .into(binding.ivUserPhoto)
        }else{
            Glide.with(binding.ivUserPhoto.context).clear(binding.ivUserPhoto)
            binding.ivUserPhoto.setImageResource(R.drawable.ic_user_photo)
        }
        return binding.root
    }

    companion object {
        private const val ARG_USER = "ARG_USER"

        fun newInstance(user: User): UserDetailsFragment {
            val fragment = UserDetailsFragment()
            fragment.arguments = bundleOf(ARG_USER to user)
            return fragment
        }
    }
}