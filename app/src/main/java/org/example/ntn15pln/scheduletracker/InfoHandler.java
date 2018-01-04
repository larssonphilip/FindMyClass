package org.example.ntn15pln.scheduletracker;

public class InfoHandler {
    private String courseName, courseCode, programCode, lectureInfo, start, stop, startDate, stopDate, startTime, stopTime, roomNr, teacherSignature, secondTeacherSignature;

    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setProgramCode(String programCode) { this.programCode = programCode; }
    public void setLectureInfo(String lectureInfo) { this.lectureInfo = lectureInfo; }

    public void setStart(String start) {
        this.start = start;
        setStartDate();
        setStartTime();
    }

    public void setStop(String stop) {
        this.stop = stop;
        setStopDate();
        setStopTime();
    }

    public void setRoomNr(String roomNr) { this.roomNr = roomNr; }
    public void setSecondTeacherSignature(String secondTeacherSignature) { this.secondTeacherSignature = secondTeacherSignature; }
    public void setTeacherSignature(String teacherSignature) { this.teacherSignature = teacherSignature; }


    public void setStartDate() { startDate = start.substring(0, start.lastIndexOf("T")); }
    public void setStartTime() { startTime = start.substring(start.lastIndexOf("T")); }
    public void setStopDate() { stopDate = stop.substring(0, stop.lastIndexOf("T")); }
    public void setStopTime() { stopTime = stop.substring(stop.lastIndexOf("T"));}


    public String getStartDate() { return startDate; }
    public String getStartTime() { return startTime; }
    public String getStopDate() { return stopDate; }
    public String getStopTime() { return stopTime; }
    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public String getProgramCode() { return programCode; }
    public String getLectureInfo() { return lectureInfo; }
    public String getStart() { return start; }
    public String getStop() { return stop; }
    public String getRoomNr() { return roomNr; }
    public String getTeacherSignature() { return teacherSignature + " " + secondTeacherSignature; }
}
