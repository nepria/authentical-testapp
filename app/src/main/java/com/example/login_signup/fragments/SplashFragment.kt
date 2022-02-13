package com.example.login_signup.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.login_signup.R
import com.example.login_signup.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        val mAuth = FirebaseAuth.getInstance()
        fun AppCompatActivity.makeItFullScreenStatusBarVisible(){
            supportActionBar?.hide()
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)}
        val time : Long = 2000
        Handler().postDelayed({
            if(mAuth!!.currentUser == null)
                findNavController().navigate(R.id.action_splashFragment_to_signupFragment)
            else
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)

        }, time)

        return binding.root
    }


}