package com.example.mystery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mystery.databinding.ActivityAnswerBinding
import com.example.mystery.databinding.ActivityMainBinding

class AnswerActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnswerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var question = intent.getStringExtra("question")
    }
    //проверка ответа
    fun onClickCheck(view: View) {
        var answerUser = binding.edit_answer.text
        val mainIntent = Intent(this,MainActivity::class.java)
        mainIntent.putExtra("answerUser",answerUser)
        setResult(RESULT_OK,mainIntent)
        finish()
    }

    fun onClickEditAnswer(view: View) {
        if (binding.edit_answer.text.toString()!="")
        {
            binding.BT_check.setEnabled(true)
        }
        else
        {
            binding.BT_check.setEnabled(false)
        }
    }
}