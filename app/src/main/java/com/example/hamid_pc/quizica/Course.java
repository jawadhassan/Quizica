package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 1/26/2017.
 */

public class Course {
    private String courseCode;
    private String courseName;
    private String courseInstructor;

    public Course(String courseCode, String courseName, String courseInstructor) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
    }

    public Course() {

    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }
}
