package com.example.engineer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {

    //    private static final String TAG = "Date";
//    private TextView mDisplayDate;
//    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DatePicker picker;
    Button btnGet;
    TextView tvw;
    TextView tvw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        TakeCare engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        tvw=(TextView)findViewById(R.id.tvDate);
        tvw1=(TextView)findViewById(R.id.tvDate1);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tvw.setText("Selected Date: "+ picker.getDayOfMonth()+"-"+ (picker.getMonth() + 1)+"-"+picker.getYear());
                int day  = picker.getDayOfMonth();
                int month= picker.getMonth();
                int year = picker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(calendar.getTime());
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                tvw.setText(currentDate);
                tvw1.setText(date);

                //saves date semester ends

                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Date",date);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), SaveEngineer.class);
                startActivity(intent);
                finish();

                //takes to main game
                Intent mainIntent = new Intent(DateActivity.this, MainActivity.class);
                //passes to mainActivity the engineer
                mainIntent.putExtra("Engineer", engineer);
                mainIntent.putExtra("Date", date);
                //starts main activity
                startActivity(mainIntent);
            }
        });
    }
}