package org.example.ntn15pln.scheduletracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class KronoxParser {
    private String courseName,courseCode,programCode,lectureInfo,start,stop,roomNr,teacherSignature,secondTeacherSignature;

    public void parseICS()throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("./SchemaICAL.ics"));
        String s;
        while((s=br.readLine())!=null){
            String[]holder=s.split(" ");
            start=(s.contains("DTSTART:"))?s.substring(s.lastIndexOf(":")+1):start;
            stop=(s.contains("DTEND:"))?s.substring(s.lastIndexOf(":")+1):stop;

            if(holder[0].equals("SUMMARY:Program:")){
                programCode=holder[1];
                for(int i=2;i<holder.length;i++){
                    switch(holder[i]){
                        case"Kurs.grp:":
                            courseCode=holder[i+1];
                        case"Sign:":
                            teacherSignature=holder[i+1];
                            if(!holder[i+2].equals("Moment:"))secondTeacherSignature=holder[i+2];
                            else secondTeacherSignature="";
                        case"Moment:":
                            lectureInfo=holder[i+1];
                    }
                }
            }
        }
    }
}
