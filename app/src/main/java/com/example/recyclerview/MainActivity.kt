package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.User
import com.example.recyclerview.model.UserListener
import com.example.recyclerview.model.UserService

interface RouteToDetails {
    fun routeToDetailsScreen(user: User)
}

class MainActivity : AppCompatActivity(), RouteToDetails {

    private lateinit var binding: ActivityMainBinding

//    private lateinit var adapter: UsersAdapter

//    private val usersService: UserService
//        get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, UserListFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun routeToDetailsScreen(user: User) {
        val fragment = UserDetailsFragment.newInstance(user)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

//        adapter = UsersAdapter(object : UserActionListener {
//            override fun onUserMove(user: User, moveBy: Int) {
//                usersService.moveUser(user, moveBy)
//            }
//
//            override fun onUserDelete(user: User) {
//                usersService.deleteUser(user)
//            }
//
//            override fun onUserDetails(user: User) {
//                Toast.makeText(this@MainActivity, "User: ${user.name}", Toast.LENGTH_SHORT).show()
//                val fragment = UserDetailsFragment.newInstance(user)
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainer, fragment)
//                    .addToBackStack(null)
//                    .commit()
//            }
//        })
//
//        val layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = adapter
//
//        usersService.addListener(userListener)
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        usersService.removeListener(userListener)
//    }
//
//    private val userListener: UserListener = {
//        adapter.users = it
//    }
}