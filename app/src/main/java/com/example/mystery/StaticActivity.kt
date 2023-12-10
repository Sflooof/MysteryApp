package com.example.mystery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mystery.databinding.ActivityMainBinding
import com.example.mystery.databinding.ActivityStaticBinding

class StaticActivity : AppCompatActivity() {
    lateinit var binding: ActivityStaticBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaticBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //вывол количества правильных ответов
        binding.Txt_r_answer.text = "Правильных ответов: " + intent.getStringExtra("rNumberAnswer")
        binding.Txt_w_answer.text = "Неправильных ответов: " + intent.getStringExtra("wNumberAnswer")
    }

    fun onClickBackBtn(view:View)
    {
        finish()
    }
}