package org.example.ntn15pln.scheduletracker.Fragments;

import android.app.Fragment;
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
import org.example.ntn15pln.scheduletracker.MapActivity;
import org.example.ntn15pln.scheduletracker.Controllers.MarkerPositionHandler;
import org.example.ntn15pln.scheduletracker.R;
import org.example.ntn15pln.scheduletracker.ScheduleActivity;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleFragment extends Fragment {
    private ListView scheduleList;
    private ArrayList<InfoHandler> lecturesOfTheDay;
    //private ScheduleActivity mActivity;
    private MarkerPositionHandler mph;
    private MyAdapter adapter;
    private ArrayList<InfoHandler> list;
    private CalendarView mCalendarView;
    private String chosenDate;
    private Calendar date;
    private SimpleDateFormat sdf;
    private  ICalParser kp;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //mActivity = (ScheduleActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_schedule, container, false);

        initItems();

        scheduleList = (ListView) mView.findViewById(R.id.schedule_list);
        mCalendarView = (CalendarView) mView.findViewById(R.id.calendarView);
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        setAdapter();
        scheduleList.setAdapter(adapter);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //month och dayOfMonth behöver en nolla framför sig för att sedan kunna matchas med schemats datum.
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
                Intent intent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        };

        scheduleList.setOnItemClickListener(clickListener);

        return mView;
    }

    public void getTodaysLectures() {
        lecturesOfTheDay.clear();
        for(InfoHandler info : list) {
            if(chosenDate.equals(info.getDate())) {
                lecturesOfTheDay.add(info);
            }
        }
    }

    public void setList() {
        list.clear();
        list.addAll(kp.getInfoList());
    }

    public void initItems() {
        kp = new ICalParser();
        kp.parseICS();


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

    //Fixa så att enbart den valda dagens lektioner visas.
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
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
