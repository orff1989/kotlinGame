package com.example.kotlingame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class GussTheCityChallenge : AppCompatActivity() {
    private var counter_for_right_answers=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_guss_the_city_challenge)

        var time_in_sec=15;
        val timer = object: CountDownTimer(15000, 1000) {
            @SuppressLint("WrongViewCast")
            override fun onTick(millisUntilFinished: Long) {
                println("$time_in_sec sec")

                val timeView: TextView = findViewById(R.id.thetimeView) as TextView
                timeView.text="Time left: $time_in_sec"

                val scoreView: TextView = findViewById(R.id.thescoreView) as TextView
                scoreView.text="Score: $counter_for_right_answers"

                time_in_sec--
            }

            override fun onFinish() {
                println("Time is up with $counter_for_right_answers points")
                val intent = Intent(this@GussTheCityChallenge, MainActivity::class.java)
                startActivity(intent)
            }
        }
        timer.start() // starting the timer

        game() // starting the game
    }


    private fun game() {
        val button_1: Button = findViewById(R.id.bu1)
        val button_2: Button = findViewById(R.id.bu2)
        val button_3: Button = findViewById(R.id.bu3)
        val button_4: Button = findViewById(R.id.bu4)
        val imageView = findViewById<ImageView>(R.id.theimageView)

        var random_city_num:Int
        var random_of_fake_city_num1:Int
        var random_of_fake_city_num2:Int
        var random_of_fake_city_num3:Int

        val cities_list = ArrayList<Int>()
        val cities_arr = resources.obtainTypedArray(R.array.cities)
        (0 until cities_arr.length()).forEach {
            val flag = cities_arr.getResourceId(it, -1)
            cities_list.add(flag)
        }
        cities_arr.recycle()

        // getting a random city number
        do {
            random_city_num = (0 until cities_list.size).random()
            random_of_fake_city_num1 = (0 until cities_list.size).random()
            random_of_fake_city_num2 = (0 until cities_list.size).random()
            random_of_fake_city_num3 = (0 until cities_list.size).random()
        }while (random_city_num==random_of_fake_city_num1 || random_city_num==random_of_fake_city_num2 || random_city_num==random_of_fake_city_num3)
        // setting the image city on the screen
        imageView.setImageDrawable(ContextCompat.getDrawable(this, cities_list[random_city_num]))

        // getting the right city name
        imageView.setTag(cities_list[random_city_num])
        var right_city_name = resources.getResourceName(imageView.getTag() as Int)
        right_city_name=right_city_name.substring(right_city_name.indexOf('/')+1).replace('_',' ')

        //getting the fake city name 1
        imageView.setTag(cities_list[random_of_fake_city_num1])
        var fake_city_name1 = resources.getResourceName(imageView.getTag() as Int)
        fake_city_name1=fake_city_name1.substring(fake_city_name1.indexOf('/')+1).replace('_',' ')

        //getting the fake city name 2
        imageView.setTag(cities_list[random_of_fake_city_num2])
        var fake_city_name2 = resources.getResourceName(imageView.getTag() as Int)
        fake_city_name2 = fake_city_name2.substring(fake_city_name2.indexOf('/')+1).replace('_',' ')

        //getting the fake city name 3
        imageView.setTag(cities_list[random_of_fake_city_num3])
        var fake_city_name3 = resources.getResourceName(imageView.getTag() as Int)
        fake_city_name3=fake_city_name3.substring(fake_city_name3.indexOf('/')+1).replace('_',' ')


        val right_button = (1..4).random() // getting a random number to place the right answer

        // setting the answers in button txt
        if (right_button==1) {
            button_1.text = right_city_name
            button_2.text = fake_city_name1
            button_3.text = fake_city_name2
            button_4.text = fake_city_name3
        }
        else if (right_button==2){
            button_1.text = fake_city_name1
            button_2.text = right_city_name
            button_3.text = fake_city_name2
            button_4.text = fake_city_name3
        }
        else if (right_button==3){
            button_1.text = fake_city_name1
            button_2.text = fake_city_name2
            button_3.text = right_city_name
            button_4.text = fake_city_name3
        }
        else{
            button_1.text = fake_city_name1
            button_2.text = fake_city_name2
            button_3.text = fake_city_name3
            button_4.text = right_city_name
        }

        // validating the answer and starting new game
        button_1.setOnClickListener {
            if (right_button==1) counter_for_right_answers++
            println(counter_for_right_answers)
            game()
        }

        button_2.setOnClickListener {
            if (right_button==2) counter_for_right_answers++
            println(counter_for_right_answers)
            game()
        }

        button_3.setOnClickListener {
            if (right_button==3) counter_for_right_answers++
            println(counter_for_right_answers)
            game()
        }

        button_4.setOnClickListener {
            if (right_button==4) counter_for_right_answers++
            println(counter_for_right_answers)
            game()
        }
    }

}