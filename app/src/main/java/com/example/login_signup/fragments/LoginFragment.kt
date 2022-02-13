package com.example.login_signup.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.login_signup.R
import com.example.login_signup.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentLoginBinding.inflate(inflater, container, false)
        binding.signupTextView.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            userLoggedIn()

        }

        binding.donThaveAnAccounButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        return binding.root
    }

    private fun userLoggedIn() {
        val view = View.inflate(requireContext(), R.layout.custom_dialogue,null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.background_light)
        val email = binding.etEmailLogin.text.toString().trim()
        val password = binding.etPassLogin.text.toString().trim()
        when {
            TextUtils.isEmpty(email) -> {
                Toast.makeText(
                    requireContext(),
                    "Please enter email",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TextUtils.isEmpty(password) -> {
                Toast.makeText(
                    requireContext(),
                    "Please enter password",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Welcome back!", Toast.LENGTH_SHORT)
                        .show()

                    dialog.dismiss()
                   findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Enter correct credentials", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
    }
}