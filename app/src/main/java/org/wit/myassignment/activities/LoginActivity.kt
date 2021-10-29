package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityLoginBinding
import org.wit.myassignment.models.Users
import timber.log.Timber


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var user = Users()
    val users = ArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        btnRegLogin.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        Loginbutton.setOnClickListener{

            //user info not being taken from UserMemStore and check effectively yet..

            if(user.email == loginEmail.toString() && user.password == loginPassword.toString()) {
                val intent = Intent(this, SplashScreen::class.java)
                startActivity(intent)
                finish()
            } else {Snackbar.make(it, R.string.login_error, Snackbar.LENGTH_LONG)
                .show()}

            Timber.i("Login Button Pressed: $user")
        }

        skipButton.setOnClickListener{
            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun findOne(loginEmail: TextInputEditText?): Users? {
        var foundUser: Users? = users.find { p -> p.email == loginEmail.toString() }
        return foundUser
    }
}