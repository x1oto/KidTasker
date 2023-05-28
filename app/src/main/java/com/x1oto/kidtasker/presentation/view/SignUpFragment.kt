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
import com.x1oto.kidtasker.data.FirebaseDataSource
import com.x1oto.kidtasker.data.AuthRepositoryImpl
import com.x1oto.kidtasker.databinding.FragmentSignupBinding
import com.x1oto.kidtasker.presentation.status.Status
import com.x1oto.kidtasker.presentation.viewmodel.SignInViewModel
import com.x1oto.kidtasker.presentation.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
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
                is Status.Loading -> {
                }

                is Status.Success -> {
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), HomeActivity::class.java))
                    requireActivity().finish()
                }

                is Status.Error -> {
                    Toast.makeText(requireContext(), status.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateData() {
        binding.apply {
            val email = emailEt.text.toString()
            val name = nameEt.text.toString()
            val password = passwordEt.text.toString()
            val confirmPass = confirmPasswordEt.text.toString()
            val accountCategory = modeSw.isChecked

            if (email.isBlank() || name.isBlank() || password.isBlank() || confirmPass.isBlank()) {
                Toast.makeText(context, "Empty fields!", Toast.LENGTH_LONG).show()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEt.error = "Invalid email format!"
                return
            }

            if (name.length > 8) {
                nameEt.error = "Name cannot be longer than 8 characters!"
                return
            }

            if (password.length < 6) {
                passwordEt.error = "Password must be at least 6 numbers!"
                return
            }

            if (password != confirmPass) {
                passwordEt.error = "Passwords do not match!"
                confirmPasswordEt.error = "Passwords do not match!"
                return
            }

            signUp(name, email, password, accountCategory)
        }
    }

    private fun signUp(name: String, email: String, password: String, isParent: Boolean) {
        viewModel.signUp(name, email, password, isParent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
