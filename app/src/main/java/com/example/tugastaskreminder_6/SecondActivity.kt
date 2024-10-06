package com.example.tugastaskreminder_6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tugastaskreminder_6.databinding.ActivitySecondBinding
import java.util.Locale


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val repeat = listOf(
            "Once",
            "Daily",
            "Mon to Fri"
        )



        with(binding) {

            //SpinnerRepeat
            spinnerRepeat.adapter = ArrayAdapter(
                this@SecondActivity,
                android.R.layout.simple_spinner_dropdown_item, repeat
            )


            //DatePicker
            val selectDateLayout: LinearLayout = selectDate
            val dateTextView: TextView = textViewDate

            dateTextView.text = "Select Date"

            selectDateLayout.setOnClickListener {
                val calendar = Calendar.getInstance()
                val currentYear = calendar.get(Calendar.YEAR)
                val currentMonth = calendar.get(Calendar.MONTH)
                val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this@SecondActivity,
                    { _, year, month, dayOfMonth ->
                        // Format the selected date as "MM/dd/yyyy"
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, dayOfMonth)
                        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                        val formattedDate = dateFormat.format(selectedDate.time)

                        // Update the TextView with the selected date
                        dateTextView.text = formattedDate
                    },
                    currentYear,
                    currentMonth,
                    currentDay
                )
                datePickerDialog.show()

            }


            //TimePicker
            textViewTime.text = "00 : 00 : 00"

            textViewTime.setOnClickListener {

                val calendar = Calendar.getInstance()
                val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
                val currentMinute = calendar.get(Calendar.MINUTE)


                val timePickerDialog = TimePickerDialog(
                    this@SecondActivity,
                    { _, hourOfDay, minute ->
                        val formattedTime = String.format("%02d : %02d : 00", hourOfDay, minute)
                        textViewTime.text = formattedTime
                    },
                    currentHour,
                    currentMinute,
                    true
                )

                timePickerDialog.show()
            }


            buttonToThirdActivity.setOnClickListener {
                val builder = AlertDialog.Builder(this@SecondActivity)
                builder.setTitle("SimpliRemind")
                builder.setMessage("Do you want to add this as new task?")
                builder.setPositiveButton("yes") { _, _, ->
                    val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                    title = editTextTitle.text.toString()
                    val selectedRepeat = spinnerRepeat.selectedItem.toString()
                    intent.putExtra("TITLE", title)
                    intent.putExtra("DATE", dateTextView.text)
                    intent.putExtra("TIME", textViewTime.text)
                    if (selectedRepeat == "Once") {
                        intent.putExtra("REPEAT", "Repeated Once")
                    } else {
                        intent.putExtra("REPEAT", selectedRepeat)
                    }

                    startActivity(intent)
                }
                builder.setNegativeButton("No") { dialog, _, ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }
    }
}