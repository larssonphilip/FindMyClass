package org.example.ntn15pln.scheduletracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import org.example.ntn15pln.scheduletracker.Controllers.ICalParser;
import org.example.ntn15pln.scheduletracker.Controllers.InfoHandler;
import org.example.ntn15pln.scheduletracker.Controllers.MarkerPositionHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Denna klass hanterar och skapar själva schemat.
 * Den hanterar även kopplingen mellan kalendern och listan,
 * som fylls på med det valda programmets kurser på rätt dag.
 */
public class ScheduleActivity extends Activity {
    private ListView scheduleList;
    private ArrayList<InfoHandler> lecturesOfTheDay;
    private MarkerPositionHandler mph;
    private MyAdapter adapter;
    private ArrayList<InfoHandler> list;
    private CalendarView mCalendarView;
    private String chosenDate;
    private Calendar date;
    private SimpleDateFormat sdf;
    private ICalParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initItems();

        scheduleList = (ListView) findViewById(R.id.schedule_list);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        setAdapter();
        scheduleList.setAdapter(adapter);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                /*
                * Koden nedanför ser till så att kalenderns datum skrivs till chosenDate
                * i rätt format, för att kunna matchas med schemats datum.
                */
                if(month >= 0 || month <= 13) {
                    month++;
                    if (month > 9 && dayOfMonth <= 9) {
                        chosenDate = "" + year + month + "0" + dayOfMonth;
                    } else if (month <= 9 && dayOfMonth > 9) {
                        chosenDate = "" + year + "0" + month + dayOfMonth;
                    } else {
                        chosenDate = "" + year + month + dayOfMonth;
                    }
                }

                getTodaysLectures();
                adapter.notifyDataSetChanged();
            }
        });

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mph.setMarker(lecturesOfTheDay.get(position).getRoomNr());
                MapActivity.setMarkerPos(mph.getX(), mph.getY());
                Intent intent = new Intent(ScheduleActivity.this, MapActivity.class);
                ScheduleActivity.this.startActivity(intent);
            }
        };

        scheduleList.setOnItemClickListener(clickListener);
    }

    /**
     * getTodaysLectures() tar de lektioner som matchar det valda datumet hos kalendern och lägger
     * in dom i lecturesOfTheDay-listan.
     */
    public void getTodaysLectures() {
        lecturesOfTheDay.clear();
        for(InfoHandler info : list) {
            if(chosenDate.equals(info.getDate())) {
                lecturesOfTheDay.add(info);
            }
        }
    }

    /**
     * setList() hämtar en lista med informationen som kommer från ICAL-filen, ifrån ICalParsern.
     */
    public void setList() {
        list.clear();
        list.addAll(parser.getInfoList());
    }

    public void initItems() {
        parser = new ICalParser();
        parser.parseICS();

        sdf = new SimpleDateFormat("yyyyMMdd");
        date = Calendar.getInstance();
        chosenDate = sdf.format(date.getTime());
        list = new ArrayList<>();
        mph = new MarkerPositionHandler();
        setList();
        lecturesOfTheDay = new ArrayList<>();
        getTodaysLectures();
    }

    public void setAdapter() {
        adapter = new MyAdapter();
    }

    /**
     * MyAdapter ändrar så att listan ser ut som den gör, istället för att se ut som en vanlig lista.
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lecturesOfTheDay.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder viewHolder;
            if(view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(R.layout.schedule_list_style, parent, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.startTime.setText(lecturesOfTheDay.get(position).getStartTime());
            viewHolder.stopTime.setText(lecturesOfTheDay.get(position).getStopTime());
            //Ändra till .getCourseName() när det är fixat.
            viewHolder.courseName.setText(lecturesOfTheDay.get(position).getCourseCode());
            viewHolder.roomNr.setText(lecturesOfTheDay.get(position).getRoomNr());
            viewHolder.teacherSignature.setText(lecturesOfTheDay.get(position).getTeacherSignature());
            return view;
        }

        private class ViewHolder {
            TextView startTime, stopTime, courseName, roomNr, teacherSignature;

            public ViewHolder(View view) {
                startTime = (TextView) view.findViewById(R.id.start_time);
                stopTime = (TextView) view.findViewById(R.id.stop_time);
                courseName = (TextView) view.findViewById(R.id.course_name);
                roomNr = (TextView) view.findViewById(R.id.room);
                teacherSignature = (TextView) view.findViewById(R.id.teacher);
            }
        }
    }
}
