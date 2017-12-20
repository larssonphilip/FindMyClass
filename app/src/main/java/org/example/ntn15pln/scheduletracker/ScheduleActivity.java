package org.example.ntn15pln.scheduletracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

public class ScheduleActivity extends AppCompatActivity{
    private CalendarView mCalendarView;
    private static String chosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_calendar);
        setContentView(R.layout.activity_schedule);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);


    }

    private void setListeners() {
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Uppdatera schema under kalender efter vilken dag som är vald.
                chosenDate = "" + year + month + dayOfMonth;
            }
        });
    }

    public static String getChosenDate() {
        return chosenDate;
    }
}
