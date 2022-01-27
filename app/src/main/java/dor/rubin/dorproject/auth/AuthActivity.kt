package dor.rubin.dorproject.auth

import android.content.DialogInterface
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.auth.FirebaseAuth
import dor.rubin.dorproject.MainActivity
import dor.rubin.dorproject.R
import dor.rubin.dorproject.SplashScreenActivity
import dor.rubin.dorproject.databinding.ActivityAuthBinding
import dor.rubin.dorproject.register.RegisterActivity
import dor.rubin.dorproject.utils.isEmailValid
// login
class AuthActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAuthBinding
    private val email get() = binding.content.etEmail.text.toString()
    private val password get() = binding.content.etPassword.text.toString()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        // click button to login
        binding.content.btnLogin.setOnClickListener {
            login()
        }
        //  click button to page register
        binding.content.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        // click button to update the password
        binding.content.forgotPassword.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val userName = view.findViewById<EditText>(R.id.et_user_name)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(userName)
            })
            builder.setNegativeButton("close", DialogInterface.OnClickListener { _, _ ->

            })
            builder.show()


        }

    }
    // fun update the password
    private fun forgotPassword(username:EditText){
        if (username.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }
        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Toast.makeText(this,"Email sent.",Toast.LENGTH_LONG).show()
                }

            }

    }
     // fun login
    private fun login() {
        if (!checkEmail() || !checkPassword()) {
            Snackbar.make(
                binding.content.btnLogin,
                "Email And password must be valid",
                Snackbar.LENGTH_LONG
            )
                .show()
            hideKeyboard()
            return
        }
        hideKeyboard()
        binding.content.progress.visibility = View.VISIBLE
        //binding.content.progress.animate()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                binding.content.progress.visibility = View.INVISIBLE
                val intent = Intent(this, SplashScreenActivity::class.java)
                startActivity(intent)
                // cant ho back the the login screen
                finish()
            }.addOnFailureListener {
                hideKeyboard()
                binding.content.progress.visibility = View.INVISIBLE
                Snackbar.make(
                    binding.content.btnRegister,
                    it.localizedMessage ?: "Unknown error, try again",
                    Snackbar.LENGTH_LONG
                )
                    .show()

            }
    }
    // fun hide keyboard
    fun hideKeyboard() {
        val current = currentFocus
        val imm = getSystemService(InputMethodManager::class.java)
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
    // fun check if the email or password  are valid!
    fun checkEmail(): Boolean = email.isEmailValid()
    fun checkPassword(): Boolean = password.length >= 6

}