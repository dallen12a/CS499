package CoursePlanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
	String id, title;
	List<String> pre;

	Course() {
		pre = new ArrayList<>();
		    }
		}

		public class CoursePlanner {

		    // Function to load course data from a CSV file
		    static boolean loadCourseData(List<Course> courses) {
		        String csvFile = "courselist.csv";
		        String line;
		        courses.clear(); // Clear existing data

		        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
		            System.out.println("File loaded successfully.");

		            while ((line = br.readLine()) != null) {
		                String[] data = line.split(",");
		                Course course = new Course();

		                if (data.length >= 2) {
		                    course.id = data[0];
		                    course.title = data[1];

		                    for (int i = 2; i < data.length; i++) {
		                        course.pre.add(data[i]);
		                    }

		                    courses.add(course);
		                } else {
		                    System.err.println("Error: Invalid data format in the CSV file.");
		                    return false;
		                }
		            }
		        } catch (IOException e) {
		            System.err.println("Error: The course data file 'courselist.csv' is unavailable.");
		            return false;
		        }

		        return true;
		    }

		    // Function to print the list of courses
		    static void printCourseList(List<Course> courses) {
		        System.out.println("\nList of Courses:");
		        for (Course course : courses) {
		            System.out.println(course.id + " " + course.title);
		        }
		    }

		    // Function to find and print a specific course
		    static void printCourseDetails(List<Course> courses, String courseID) {
		        boolean found = false;

		        for (Course course : courses) {
		            if (course.id.equals(courseID)) {
		                found = true;
		                System.out.println("\nCourse Details:");
		                System.out.println("ID: " + course.id);
		                System.out.println("Title: " + course.title);

		                if (course.pre.isEmpty()) {
		                    System.out.println("Prerequisites: None");
		                } else {
		                    System.out.print("Prerequisites: ");
		                    for (String pre : course.pre) {
		                        System.out.print(pre + " ");
		                    }
		                    System.out.println();
		                }
		                break;
		            }
		        }

		        if (!found) {
		            System.out.println("\nCourse " + courseID + " not found.");
		        }
		    }

		    public static void main(String[] args) {
		        List<Course> courses = new ArrayList<>();
		        boolean dataLoaded = false;

		        Scanner scanner = new Scanner(System.in);

		        while (true) {
		            System.out.println("\nWelcome to the Course Planner.");
		            System.out.println("1. Load Data Structure");
		            System.out.println("2. Print Course List");
		            System.out.println("3. Print Course");
		            System.out.println("9. Exit");
		            System.out.print("What Would You Like To Do? ");

		            int option;
		            if (scanner.hasNextInt()) {
		                option = scanner.nextInt();
		            } else {
		                System.out.println("Invalid choice. Please enter a valid menu option.");
		                scanner.nextLine(); // Consume the invalid input
		                continue;
		            }

		            switch (option) {
		                case 1:
		                    if (!dataLoaded) { // Check if data is already loaded
		                        dataLoaded = loadCourseData(courses);
		                        if (dataLoaded) {
		                            System.out.println("Course data loaded successfully.");
		                        }
		                    } else {
		                        System.out.println("Course data is already loaded.");
		                    }
		                    break;
		                case 2:
		                    if (dataLoaded) {
		                        printCourseList(courses);
		                    } else {
		                        System.out.println("Course data is not loaded. Please choose option 1 to load data.");
		                    }
		                    break;
		                case 3:
		                    if (dataLoaded) {
		                        System.out.print("Enter the course ID: ");
		                        String courseID = scanner.next();
		                        printCourseDetails(courses, courseID);
		                    } else {
		                        System.out.println("Course data is not loaded. Please choose option 1 to load data.");
		                    }
		                    break;
		                case 9:
		                    System.out.println("Goodbye.");
		                    scanner.close();
		                    return;
		                default:
		                    System.out.println("Invalid choice. Please try again.");
		            }
		        }
		    }
	}
