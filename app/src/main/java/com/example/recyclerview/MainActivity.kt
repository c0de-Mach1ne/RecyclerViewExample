package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.User

interface RouteToDetails {
    fun routeToDetailsScreen(user: User)
}

class MainActivity : AppCompatActivity(), RouteToDetails {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun routeToDetailsScreen(user: User) {
        findNavController(binding.root).navigate(R.id.action_userListFragment_to_userDetailsFragment,
            bundleOf(UserDetailsFragment.ARG_USER to user)
            )
    }
}
