package com.x1oto.kidtasker.presentation.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.x1oto.kidtasker.data.AuthRepositoryImpl
import com.x1oto.kidtasker.data.FirebaseDataSource
import com.x1oto.kidtasker.data.ViewModelFactory
import com.x1oto.kidtasker.databinding.ActivityHomeBinding
import com.x1oto.kidtasker.presentation.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}