package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 1/26/2017.
 */

public class Course {
    private String CourseCode;
    private String CourseName;
    private String CourseInstructor;

    public Course(String courseCode, String courseName, String courseInstructor) {
        CourseCode = courseCode;
        CourseName = courseName;
        CourseInstructor = courseInstructor;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public String getCourseInstructor() {
        return CourseInstructor;
    }
}
