package com.example.guru_login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity : AppCompatActivity() {

        //private var mAuth : FirebaseAuth? = null
        private lateinit var auth: FirebaseAuth
        private val TAG : String = "CreateAccount"

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_signup)

            auth = FirebaseAuth.getInstance()

            //사용자가 입력한 값들
//        var email: String = et_email.text.toString()
//        var password: String = et_password.text.toString()
            val email = findViewById<EditText>(R.id.edt_login_email)
            val password = findViewById<EditText>(R.id.edt_signip_pw)


            //새로운 계정을 생성한다.
            btn_signup_finish.setOnClickListener {
                //Log.d(TAG, "Data: " + email.text + password.text)

                if (email.text.toString().length == 0 || password.text.toString().length == 0){
                    Toast.makeText(this, "email 혹은 password를 반드시 입력하세요.", Toast.LENGTH_SHORT).show()
                } else {
                    auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                val user = auth.currentUser
                                //updateUI(user)
                                // 아니면 액티비티를 닫아 버린다.
                                finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                //updateUI(null)
                                //입력필드 초기화
                                email?.setText("")
                                password?.setText("")
                                email.requestFocus()
                            }
                        }
                }
            }


//            bt_cancel.setOnClickListener {
//                finish()
//                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit)
//            }
        }
    }

