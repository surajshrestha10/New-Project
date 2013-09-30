package com.example.dateandtimedialog;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText et_time, et_date;
	static final int timeDialog = 0;
	static final int dateDialog = 1;
	int hour, minute;
	int yr, month, day;// change 1

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_time = (EditText) findViewById(R.id.editText1);
		et_date = (EditText) findViewById(R.id.editText2);

		Calendar today = Calendar.getInstance();
		yr = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH);
		day = today.get(Calendar.DAY_OF_MONTH);

		et_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				showDialog(timeDialog);

			}

		});

		et_date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(dateDialog);
			}

		});

	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case timeDialog:

			return new TimePickerDialog(this, mTimeSetListener, hour, minute,
					false);

		case dateDialog:
			return new DatePickerDialog(this, mDateSetListener, yr, month, day);

		}
		return null;

	}

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourofDay, int minuteofHour) {
			// TODO Auto-generated method stub
			hour = hourofDay;
			minute = minuteofHour;

			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
			Date date = new Date(0, hour, minute);
			String strDate = timeFormat.format(date);
			et_time.setText(strDate);

		}
	};

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthofYear,
				int dayofYear) {
			// TODO Auto-generated method stub
			yr = year;
			month = monthofYear;
			day = dayofYear;

			Toast.makeText(
					MainActivity.this,
					"Year is :" + " " + yr + " " + "Month :" + " "
							+ monthofYear + " ", 1000).show();

			et_date.setText(yr + ":" + month + ":" + day);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
