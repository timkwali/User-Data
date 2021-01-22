package com.example.timringtimkwali.ui.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.timringtimkwali.R
import com.example.timringtimkwali.adapters.OnItemClick
import com.example.timringtimkwali.adapters.UsersListRVAdapter
import com.example.timringtimkwali.databinding.FragmentUsersBinding
import com.example.timringtimkwali.model.User
import com.example.timringtimkwali.network.NetWorkLiveData
import com.example.timringtimkwali.viewmodel.UsersViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(), OnItemClick {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding
    private val usersViewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UsersListRVAdapter
    private lateinit var allUsers: List<User>

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

        checkNetworkAvailability()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(item: User, position: Int) {

        val bundle = bundleOf(
            "id" to item.id,
            "avatar" to item.avatar,
            "fullName" to item.fullName,
            "createdAt" to item.createdAt,
            "gender" to item.gender,
            "colors" to item.colors?.let { listToString(it) },
            "countries" to item.countries?.let { listToString(it) }
        )
        findNavController().navigate(R.id.userDetailsFragment, bundle)
    }

    private fun checkNetworkAvailability() {
        /** MONITOR NETWORK AVAILABILITY */
        val netWorkLiveData = NetWorkLiveData(requireContext())
        netWorkLiveData.observe(viewLifecycleOwner, Observer {
            if(it) {
                binding?.usersNetworkTv?.visibility = View.INVISIBLE
                binding?.usersProgressPb?.visibility = View.VISIBLE
                binding?.usersUsersListRv?.visibility = View.VISIBLE
                getAllUsers()
            } else {
                binding?.apply {
                    usersProgressPb?.visibility = View.INVISIBLE
                    usersUsersListRv?.visibility = View.INVISIBLE
                    usersNetworkTv?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun listToString(list: List<String>): String {
        return if(list.isEmpty()) {
            ""
        } else {
            val stringBuilder = StringBuilder()
            for(item in list) {
                stringBuilder.append("$item, ")
            }
            stringBuilder.substring(0, stringBuilder.length-2)
        }
    }

    private fun getAllUsers() {
        usersViewModel.result.observe(viewLifecycleOwner, Observer{
            if(it == "Success") {
                binding?.usersProgressPb?.visibility = View.INVISIBLE
            } else {
                binding?.usersProgressPb?.visibility = View.INVISIBLE
                binding?.usersNetworkTv?.visibility = View.VISIBLE
            }
        })

        usersViewModel.allUsers.observe(viewLifecycleOwner, Observer {
            val list = mutableListOf<User>()
            for (t in it) {
                list.add(User(t.id, t.avatar, t.fullName, t.createdAt, t.gender, t.colors, t.countries))
            }
            allUsers = list
            adapter = UsersListRVAdapter(allUsers, this)
            val usersRecyclerView = binding?.usersUsersListRv
            usersRecyclerView?.adapter = adapter
            usersRecyclerView?.layoutManager = GridLayoutManager(this.context, 2)
            usersRecyclerView?.setHasFixedSize(true)
        })
    }
}