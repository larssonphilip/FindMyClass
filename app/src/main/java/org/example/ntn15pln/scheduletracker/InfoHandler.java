package org.example.ntn15pln.scheduletracker;

public class InfoHandler {
    private String courseName, courseCode, programCode, lectureInfo, start, stop, roomNr, teacherSignature, secondTeacherSignature;

    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setProgramCode(String programCode) { this.programCode = programCode; }
    public void setLectureInfo(String lectureInfo) { this.lectureInfo = lectureInfo; }
    public void setStart(String start) { this.start = start; }
    public void setStop(String stop) { this.stop = stop; }
    public void setRoomNr(String roomNr) { this.roomNr = roomNr; }
    public void setSecondTeacherSignature(String secondTeacherSignature) { this.secondTeacherSignature = secondTeacherSignature; }
    public void setTeacherSignature(String teacherSignature) { this.teacherSignature = teacherSignature; }

    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public String getProgramCode() { return programCode; }
    public String getLectureInfo() { return lectureInfo; }
    public String getStart() { return start; }
    public String getStop() { return stop; }
    public String getRoomNr() { return roomNr; }
    public String getTeacherSignature() { return teacherSignature + " " + secondTeacherSignature; }
}
