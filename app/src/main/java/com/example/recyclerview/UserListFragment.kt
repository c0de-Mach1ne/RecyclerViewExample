package com.example.recyclerview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.UserListBinding
import com.example.recyclerview.model.User
import com.example.recyclerview.model.UserListener
import com.example.recyclerview.model.UserService

class UserListFragment : Fragment() {
    private lateinit var binding: UserListBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var listener: RouteToDetails

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RouteToDetails) {
            listener = context
        }
    }

    private val usersService: UserService
        get() = (activity?.applicationContext as App).usersService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                usersService.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
                listener.routeToDetailsScreen(user)
            }
        })

        usersService.addListener(userListener)

        val layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        usersService.addListener(userListener)
    }

    override fun onDetach() {
        super.onDetach()
        usersService.removeListener(userListener)
    }

    private val userListener: UserListener = {
        adapter.users = it
    }
}