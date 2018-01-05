package org.example.ntn15pln.scheduletracker;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    //MapActivity map;
    TextView startTime, stopTime, courseName, roomNr, teacherSignature;
    ICalParser kp;
    ListView scheduleList;

    //public String room;
    private static ArrayList<InfoHandler> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        kp = new ICalParser();
        kp.parseICS();

        list = kp.getInfoList();

        scheduleList = (ListView) mView.findViewById(R.id.schedule_list);

        MyAdapter adapter = new MyAdapter();
        scheduleList.setAdapter(adapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //map.getRoom(list.get(position).getRoomNr());
                Intent intent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        };

        scheduleList.setOnItemClickListener(clickListener);

        return mView;
    }

    //Fixa så att enbart den valda dagens lektioner visas.
    class MyAdapter extends BaseAdapter {
        public class ViewHolder{
            TextView startTime, stopTime, courseName, roomNr, teacherSignature;
        }
        @Override
        public int getCount() {
            return list.size();
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
            //ViewHolder holder = new ViewHolder();
            if(view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(R.layout.list_style, parent, false);
            }

            startTime = (TextView) view.findViewById(R.id.start_time);
            stopTime = (TextView) view.findViewById(R.id.stop_time);
            courseName = (TextView) view.findViewById(R.id.course_name);
            roomNr = (TextView) view.findViewById(R.id.room);
            teacherSignature = (TextView) view.findViewById(R.id.teacher);

            /*startTime.setText("Start");
            stopTime.setText("Stop");
            //Ändra till .getCourseName() när det är fixat.
            courseName.setText("Course");
            roomNr.setText("Room");
            teacherSignature.setText("Teacher");*/

            startTime.setText(list.get(position).getStartTime());
            stopTime.setText(list.get(position).getStopTime());
            //Ändra till .getCourseName() när det är fixat.
            courseName.setText(list.get(position).getCourseCode());
            roomNr.setText(list.get(position).getRoomNr());
            teacherSignature.setText(list.get(position).getTeacherSignature());
            return view;
        }
    }

}
