package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityLoginBinding
import org.wit.myassignment.models.Users
import timber.log.Timber


private lateinit var binding: ActivityLoginBinding
var user = Users()
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        btnRegLogin.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        Loginbutton.setOnClickListener{

            val intent = Intent(this, TrainerListActivity::class.java)
            // startActivity(intent)
            //finish()


            if(user.email.equals(loginEmail)) {
                startActivity(intent)
                finish()
            } else {Snackbar.make(it, R.string.login_error, Snackbar.LENGTH_LONG)
                .show()}


            Timber.i("Login Button Pressed: $user")
        }

        skipButton.setOnClickListener{
            val intent = Intent(this, TrainerListActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}