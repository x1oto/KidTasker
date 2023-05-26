package com.x1oto.kidtasker.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.x1oto.kidtasker.data.AuthRepositoryImpl
import com.x1oto.kidtasker.data.FirebaseDataSource
import com.x1oto.kidtasker.databinding.FragmentHomeBinding
import com.x1oto.kidtasker.presentation.status.Status
import com.x1oto.kidtasker.presentation.viewmodel.HomeViewModel
import com.x1oto.kidtasker.presentation.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchDataFromFirebase()

        binding.button.setOnClickListener {
            viewModel.signOut()
            requireActivity().finish()
        }

        binding.addTaskFb.setOnClickListener {
            AddTaskDialog.show(this)
        }
        notifyUser()
    }


    @SuppressLint("SetTextI18n")
    private fun notifyUser() {
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Loading -> {
                    binding.apply {
                        textView.isVisible = false
                        button.isVisible = false
                        homePb.isVisible = true
                    }
                }
                is Status.Success -> {
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                    binding.apply {
                        textView.isVisible = true
                        button.isVisible = true
                        homePb.isVisible = false
                    }
                }
                is Status.Error -> {
                    Toast.makeText(requireContext(), status.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                val name = user.name
                val email = user.email
                val role = user.role

                binding.textView.text = "You logged in as: $role"
            }
        }
    }


}