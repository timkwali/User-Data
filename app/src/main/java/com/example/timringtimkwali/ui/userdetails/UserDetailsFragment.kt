package com.example.timringtimkwali.ui.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.timringtimkwali.R
import com.example.timringtimkwali.databinding.FragmentUserDetailsBinding
import com.example.timringtimkwali.utils.RANDOM_IMAGE_URL

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding
    private lateinit var id: String
    private lateinit var avatar: String
    private lateinit var fullName: String
    private lateinit var createdAt: String
    private lateinit var gender: String
    private lateinit var colors: String
    private lateinit var countries: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args= savedInstanceState?.get("colors")

        /** GET BUNDLE DATA */
        id = arguments?.getString("id").toString()
        avatar = arguments?.getString("avatar").toString()
        fullName = arguments?.getString("fullName").toString()
        createdAt = arguments?.getString("createdAt").toString()
        gender = arguments?.getString("gender").toString().capitalize()
        colors = arguments?.getString("colors")!!
        countries = arguments?.getString("countries")!!

//        Toast.makeText(this.context, list.toString(), Toast.LENGTH_SHORT).show()

        /** POPULATE UI */
        setUpUI()

        /** NAVIGATE BACK */
        binding?.userDetailsBackIv?.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpUI() {
        binding?.apply {
            userDetailsUserFullNameTv.text = fullName
            userDetailsGenderTv.text = gender
            userDetailsDateCreatedTv.text = createdAt
            userDetailsColorsTv.text = colors
            userDetailsCountriesTv.text = countries
            setUpImage(userDetailsAvatarIv, avatar)
            setUpImage(userDetailsMainBgIv, RANDOM_IMAGE_URL)
        }
    }

    private fun setUpImage(imageView: ImageView, uri: String){
        val defaultImage = if(imageView == binding?.userDetailsAvatarIv ) R.drawable.ic_person else R.drawable.train_station
        Glide.with(this)
            .load(uri)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(defaultImage)
            .into(imageView)
    }
}