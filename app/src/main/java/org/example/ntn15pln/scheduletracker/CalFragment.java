package org.example.ntn15pln.scheduletracker;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalFragment extends Fragment {
    private int week;
    private View rootView;
    private ImageButton incWeek, decWeek;
    private TextView currentWeek, monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private Calendar calendar;
    private Date date1 = new Date();
    private LocalDate date;
    private DateTime dt;
    private List<TextView> days = new ArrayList<TextView>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cal, container, false);
        calendar = Calendar.getInstance();
        week = calendar.get(Calendar.WEEK_OF_YEAR);
        dt = new DateTime(date1);
        setViews();
        addDaysToList();
        currentWeek.setText(String.valueOf(week));
        setListeners();
        return rootView;
    }

    public void setViews() {
        incWeek = (ImageButton)rootView.findViewById(R.id.increment_week);
        decWeek = (ImageButton) rootView.findViewById(R.id.decrement_week);
        currentWeek = (TextView) rootView.findViewById(R.id.text_week);
        monday = (TextView) rootView.findViewById(R.id.cal_day_monday);
        tuesday = (TextView) rootView.findViewById(R.id.cal_day_tuesday);
        wednesday = (TextView) rootView.findViewById(R.id.cal_day_wednesday);
        thursday = (TextView) rootView.findViewById(R.id.cal_day_thursday);
        friday = (TextView) rootView.findViewById(R.id.cal_day_friday);
        saturday = (TextView) rootView.findViewById(R.id.cal_day_saturday);
        sunday = (TextView) rootView.findViewById(R.id.cal_day_sunday);

    }

    public void addDaysToList() {
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);
        days.add(saturday);
        days.add(sunday);

    }

    public void setWeek() {

    }

    public void setListeners() {
        incWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(week < 52) week++;
                else if(week == 52) week = 1;

                currentWeek.setText(String.valueOf(week));
            }
        });

        decWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(week > 1) week--;
                else if(week == 1) week = 52;

                currentWeek.setText(String.valueOf(week));
            }
        });


    }
}
