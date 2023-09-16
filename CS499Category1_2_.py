import csv

# Class for Course structure
class Course:
    def __init__(self):
        self.id = ""
        self.title = ""
        self.pre = []

# Function to load course data from a CSV file
def load_course_data(courses):
    csv_file = "courselist.csv"
    courses.clear()  # Clear existing data

    try:
        with open(csv_file, newline='') as file:
            reader = csv.reader(file)

            for row in reader:
                if len(row) >= 2:
                    course = Course()
                    course.id = row[0]
                    course.title = row[1]

                    course.pre = row[2:]

                    courses.append(course)
                else:
                    print("Error: Invalid data format in the CSV file.")
                    return False

        print("File loaded successfully.")
        return True

    except FileNotFoundError:
        print("Error: The course data file 'courselist.csv' is unavailable.")
        return False

# Function to print the list of courses (option 2)
def print_course_list(courses):
    print("\nList of Courses:")
    for course in courses:
        print(f"{course.id}  {course.title}")

# Function to find and print a specific course (option 3)
def print_course_details(courses, course_id):
    found = False

    for course in courses:
        if course.id == course_id:
            found = True
            print("\nCourse Details:")
            print("ID:", course.id)
            print("Title:", course.title)

            print("Prerequisites:", " ".join(course.pre))
            break

    if not found:
        print("\nCourse", course_id, "not found.")

if __name__ == "__main__":
    courses = []
    data_loaded = False

    while True:
        print("\nWelcome to the Course Planner.")
        print("1. Load Data Structure")
        print("2. Print Course List")
        print("3. Print Course")
        print("9. Exit")

        try:
            option = int(input("What Would You Like To Do? "))
        except ValueError:
            print("Invalid choice. Please enter a valid menu option.")
            continue

        if option == 1:
            if not data_loaded:
                data_loaded = load_course_data(courses)
                if data_loaded:
                    print("Course data loaded successfully.")
            else:
                print("Course data is already loaded.")
        elif option == 2:
            if data_loaded:
                print_course_list(courses)
            else:
                print("Course data is not loaded. Please choose option 1 to load data.")
        elif option == 3:
            if data_loaded:
                course_id = input("Enter the course ID: ")
                print_course_details(courses, course_id)
            else:
                print("Course data is not loaded. Please choose option 1 to load data.")
        elif option == 9:
            print("Goodbye.")
            break
        else:
            print("Invalid choice. Please try again.")