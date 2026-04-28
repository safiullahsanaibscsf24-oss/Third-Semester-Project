package src;

public class Course {
    String courseCode;
    String courseName;
    int capacity;
    int currentEnrollment;
    WaitlistQueue waitlist;

    public Course(String courseCode, String courseName, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.capacity = capacity;
        this.currentEnrollment = 0;
        this.waitlist = new WaitlistQueue();
    }

    public String toString() {
        return "Course: " + courseCode + " - " + courseName + " | Enrollment: " + currentEnrollment + "/" + capacity;
    }
}
