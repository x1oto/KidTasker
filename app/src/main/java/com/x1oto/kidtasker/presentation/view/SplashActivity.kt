package com.x1oto.kidtasker.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up splash screen
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        // Delay and navigate to next activity
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            navigateToNextActivity()
        }
    }

    private fun navigateToNextActivity() {
        val intent = if (firebaseAuth.currentUser == null) {
            // If user is not authenticated, navigate to AuthActivity
            Intent(this, AuthActivity::class.java)
        } else {
            // If user is authenticated, navigate to HomeActivity
            Intent(this, HomeActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
