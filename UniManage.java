package src;

import java.util.Scanner;

public class UniManage {

    public static void main(String[] args) {
        StudentBST studentTree = new StudentBST();
        CourseList courseCatalog = new CourseList();
        UndoStack undoStack = new UndoStack();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- UniManage ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Drop Student from Course");
            System.out.println("5. Undo Last Action");
            System.out.println("6. Display Course Catalog");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Student GPA: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    Student newStudent = new Student(name, id, gpa);
                    studentTree.insert(newStudent);
                    undoStack.push("add student " + id);
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Course Code (e.g., CS101): ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter Max Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Course newCourse = new Course(courseCode, courseName, capacity);
                    courseCatalog.addCourse(newCourse);
                    undoStack.push("add course " + courseCode);
                    System.out.println("Course added successfully!");
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    int studentIdToEnroll = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Course Code: ");
                    String courseCodeToEnroll = scanner.nextLine();

                    Student studentToEnroll = studentTree.search(studentIdToEnroll);
                    if (studentToEnroll == null) {
                        System.out.println("Error: Student with ID " + studentIdToEnroll + " not found.");
                        break;
                    }

                    Course courseToEnroll = courseCatalog.findCourse(courseCodeToEnroll);
                    if (courseToEnroll == null) {
                        System.out.println("Error: Course with code " + courseCodeToEnroll + " not found.");
                        break;
                    }

                    if (courseToEnroll.currentEnrollment < courseToEnroll.capacity) {
                        courseToEnroll.currentEnrollment++;
                        System.out.println("Success: " + studentToEnroll.name + " enrolled in " + courseToEnroll.courseName);
                    } else {
                        courseToEnroll.waitlist.enqueue(studentToEnroll);
                        System.out.println("Course Full. " + studentToEnroll.name + " added to waitlist for " + courseToEnroll.courseName);
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID to drop: ");
                    int studentIdToDrop = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Course Code to drop from: ");
                    String courseCodeToDrop = scanner.nextLine();

                    Course courseToDrop = courseCatalog.findCourse(courseCodeToDrop);
                    if (courseToDrop == null) {
                        System.out.println("Error: Course not found.");
                        break;
                    }

                    // For simplicity, we assume the student is in the class to drop.
                    // A more robust implementation would check this.
                    courseToDrop.currentEnrollment--;
                    System.out.println("Student dropped from course.");

                    if (!courseToDrop.waitlist.isEmpty()) {
                        Student nextStudent = courseToDrop.waitlist.dequeue();
                        courseToDrop.currentEnrollment++;
                        System.out.println("Spot opened! " + nextStudent.name + " moved from waitlist to class.");
                    }
                    break;
                case 5:
                    String lastAction = undoStack.pop();
                    if (lastAction == null) {
                        System.out.println("Nothing to undo.");
                    } else {
                        System.out.println("Undoing last action: " + lastAction);
                        String[] parts = lastAction.split(" ");
                        if (parts.length > 1) {
                            String action = parts[0] + " " + parts[1];
                            if (action.equals("add student")) {
                                int studentIdToRemove = Integer.parseInt(parts[2]);
                                studentTree.remove(studentIdToRemove);
                                System.out.println("Student with ID " + studentIdToRemove + " removed.");
                            } else if (action.equals("add course")) {
                                // This is simplified: assumes last added course is the one to remove.
                                courseCatalog.removeTail();
                                System.out.println("Last added course removed.");
                            }
                        }
                    }
                    break;
                case 6:
                    courseCatalog.displayCatalog();
                    break;
                case 7:
                    System.out.println("Exiting UniManage. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
