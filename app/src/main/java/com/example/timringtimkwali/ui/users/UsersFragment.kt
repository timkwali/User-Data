package com.example.timringtimkwali.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.timringtimkwali.R
import com.example.timringtimkwali.adapters.OnItemClick
import com.example.timringtimkwali.adapters.UsersListRVAdapter
import com.example.timringtimkwali.databinding.FragmentUsersBinding
import com.example.timringtimkwali.model.User


class UsersFragment : Fragment(), OnItemClick {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding

    lateinit var adapter: UsersListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = UsersListRVAdapter(usersList, this)
        val usersRecyclerView  = binding?.usersUsersListRv
        usersRecyclerView?.adapter = adapter
        usersRecyclerView?.layoutManager = GridLayoutManager(this.context, 2)
        usersRecyclerView?.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(item: User, position: Int) {
        Toast.makeText(this.context, "$position", Toast.LENGTH_SHORT).show()
    }

    val list = listOf<String>("one", "two", "three")

    val usersList = listOf<User>(
            User("1","https://randomuser.me/api/portraits/men/55.jpg", "John Doe", "25 March", "Male", list, list),
            User("1","https://randomuser.me/api/portraits/men/55.jpg", "John Doe", "25 March", "Male", list, list),
            User("1","https://randomuser.me/api/portraits/men/55.jpg", "John Doe", "25 March", "Male", list, list),
            User("1","https://randomuser.me/api/portraits/men/55.jpg", "John Doe", "25 March", "Male", list, list),
            User("1","https://randomuser.me/api/portraits/men/55.jpg", "John Doe", "25 March", "Male", list, list),
            User("1","https://randomuser.me/api/portraits/men/55.jpg", "John Doe", "25 March", "Male", list, list),
            User("1","https://randomuser.me/api/portraits/men/55.jpg", "John Doe", "25 March", "Male", list, list),
    )
}