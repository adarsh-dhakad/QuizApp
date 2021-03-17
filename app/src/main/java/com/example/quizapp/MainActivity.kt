package com.example.quizapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
   // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val text = findViewById<EditText>(R.id.et_name)
      //  val text_name = text.text.toString()

        val btn_start = findViewById<Button>(R.id.btn_start)
        btn_start.setOnClickListener {

            if (text.text.isEmpty()){
                Toast.makeText(this,"please enter your name ",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"your name ${text.text.toString()}",Toast.LENGTH_LONG).show()

                val intent = Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,text.text.toString())
                startActivity(intent)
                finish()
            }

        }
    }
}