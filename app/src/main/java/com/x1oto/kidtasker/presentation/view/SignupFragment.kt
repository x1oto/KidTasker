package com.x1oto.kidtasker.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.x1oto.kidtasker.data.FirebaseAuthDataSource
import com.x1oto.kidtasker.data.SignupRepositoryImpl
import com.x1oto.kidtasker.data.SignupViewModelFactory
import com.x1oto.kidtasker.databinding.FragmentSignupBinding
import com.x1oto.kidtasker.presentation.viewmodel.SignupStatus
import com.x1oto.kidtasker.presentation.viewmodel.SignupViewModel

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val firebaseAuthDataSource = FirebaseAuthDataSource()
    private val signupRepository = SignupRepositoryImpl(firebaseAuthDataSource)
    private val viewModel: SignupViewModel by viewModels { SignupViewModelFactory(signupRepository) }


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

        binding.btnSignup.setOnClickListener {
            val email = "test1@mail.send"
            val password = "password"

            viewModel.signUp(email, password)
        }

        viewModel.signupStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is SignupStatus.Loading -> {
                    // Відображення прогресу завантаження, якщо потрібно
                }
                is SignupStatus.Success -> {
                    // Обробка успішної реєстрації
                    Toast.makeText(requireContext(), "Реєстрація пройшла успішно", Toast.LENGTH_SHORT).show()

                    // Виконуємо додаткові дії, якщо потрібно
                }
                is SignupStatus.Error -> {
                    // Обробка помилки реєстрації
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}