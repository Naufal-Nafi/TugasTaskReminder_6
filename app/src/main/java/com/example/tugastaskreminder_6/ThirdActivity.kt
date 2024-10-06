package com.example.tugastaskreminder_6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugastaskreminder_6.databinding.ActivitySecondBinding
import com.example.tugastaskreminder_6.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repeat = intent.getStringExtra("REPEAT")
        val date = intent.getStringExtra("DATE")
        val time = intent.getStringExtra("TIME")
        val title = intent.getStringExtra("TITLE")

        with(binding) {
            textTitle.text = title
            textDate.text = date
            textTime.text = time
            textRepeat.text = repeat
        }

    }
}