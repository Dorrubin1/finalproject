package dor.rubin.dorproject.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth
import dor.rubin.dorproject.MainActivity
import dor.rubin.dorproject.R
import dor.rubin.dorproject.auth.AuthActivity
import dor.rubin.dorproject.databinding.ActivityRegisterBinding
import dor.rubin.dorproject.utils.isEmailValid


class RegisterActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRegisterBinding

    private val email get() = binding.register.etEmailRegister.text.toString()
    private val password get() = binding.register.etPasswordRegister.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        // click button to register
        binding.register.btnRegisterRegister.setOnClickListener {
            register()
        }
        // back to login page
        binding.register.btnBackRegister.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }


    }

    // check the email and password to register
    private fun register() {
        if (!checkEmail() || !checkPassword()) {
            Snackbar.make(
                binding.register.btnRegisterRegister,
                "Email And password must be valid",
                Snackbar.LENGTH_LONG
            )
                .show()
            return
        }
        hideKeyboard()
        //binding.content.progress.animate()
        binding.register.progressRegister.visibility = View.VISIBLE
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                binding.register.progressRegister.visibility = View.INVISIBLE
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                // cant ho back the the login screen
                finish()
            }.addOnFailureListener {
                hideKeyboard()
                binding.register.progressRegister.visibility = View.INVISIBLE
                Snackbar.make(
                    binding.register.btnRegisterRegister,
                    it.localizedMessage ?: "Unknown error, try again",
                    Snackbar.LENGTH_LONG
                )
                    .show()

            }
    }
    // hide key board
    private fun hideKeyboard() {
        val imm = getSystemService(InputMethodManager::class.java)
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    fun checkEmail(): Boolean = email.isEmailValid()
    fun checkPassword(): Boolean = password.length >= 6


}