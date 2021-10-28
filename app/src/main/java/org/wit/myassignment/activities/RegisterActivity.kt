package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityRegisterBinding
import org.wit.myassignment.databinding.ActivityTrainerListBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.Users
import timber.log.Timber

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var app: MainApp
    var user = Users()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MainApp


        btnLogRegister.setOnClickListener {
            onBackPressed()
        }

        RegisterButton.setOnClickListener() {
            user.name = binding.userName.text.toString()
            user.email = binding.userEmail.text.toString()
            user.name = binding.userPassword.text.toString()
            if (user.name.isEmpty()) {
                Snackbar.make(it, R.string.enter_user_name, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                app.users.createUser(user)
            }
        }
        Timber.i("add Button Pressed: $user")
        setResult(RESULT_OK)
        finish()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }

}