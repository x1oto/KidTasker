package com.x1oto.kidtasker.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.x1oto.kidtasker.data.AuthRepositoryImpl
import com.x1oto.kidtasker.data.FirebaseDataSource
import com.x1oto.kidtasker.databinding.FragmentLoginBinding
import com.x1oto.kidtasker.presentation.status.Status
import com.x1oto.kidtasker.presentation.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupBt.setOnClickListener {
            validateData()
        }

        notifyUser()
    }

    private fun notifyUser() {
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Loading -> {}
                is Status.Success -> {
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), HomeActivity::class.java))
                    requireActivity().finish()
                }
                is Status.Error -> {
                    Toast.makeText(requireContext(), status.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateData() {
        binding.apply {
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(context, "Empty fields!", Toast.LENGTH_LONG).show()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEt.error = "Invalid email format!"
                return
            }
            
            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}