import java.util.ArrayList;
import java.util.Scanner;


public class MenuSystem {
    private int currentClass = 0;
    private Scanner input;
    private GradeManager gradeManager;
    private ReportGenerator reportGenerator;

    public MenuSystem() {
        this.input = new Scanner(System.in);
        this.gradeManager = new GradeManager();
        this.reportGenerator = new ReportGenerator();
    }

    public static void main(String[] args) {
        MenuSystem menu = new MenuSystem();
        menu.run();
    }

    public void showClassMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Add Class");
        System.out.println("2. Manage Class " + currentClass);
        System.out.println("3. Search and Select a class");
        System.out.println("4. View Class Report");
        System.out.println("5. Show Statistics");
        System.out.println("6. Exit");
    }

    /*
    TODO:
    - Add Class
    - Manage Class
    - View Class Report
    - Show Statistics
    - Exit
    - Add Student to Existing Class
    */
    // Run the menu system and operations
    public void run() {
        gradeManager.classNumber = currentClass;
        System.out.println("Grade Management System");
        while (true) {
            showClassMenu();
            int choiceC = getValidChoice();
            if (choiceC == 1) {
                addClass();
            } else if (choiceC == 3) {
                searchClass();
            }else if (choiceC == 2) {
                System.out.println("Managing Class " + currentClass);
                runClass();
            } else if (choiceC == 4) {
                showClassReport();
            } else if (choiceC == 5) {
                showStatistics();
            } else if (choiceC == 6) {
                System.out.println("Goodbye!");
                break;
            }
        }

        
        input.close();
    }
    // Search for a class and set it as the current class
    public void searchClass() {
        try {
            System.out.println("Searching for a class...");
            System.out.println("Enter class number bewtween " + 0 + " and " + currentClass + ": ");
            int classNumber = input.nextInt();
            input.nextLine();
            currentClass = classNumber;
            System.out.println("Now managing Class " + currentClass);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // run the inside class menu system and operations
    public void runClass() {
        while (true) {
            try {
                showMenu();
                int choice = getValidChoice();

                if (choice == 1) {
                    addStudent();
                } else if (choice == 2) {
                    addStudentFromExistingClass(currentClass);
                } else if (choice == 3) {
                    addGrade();
                } else if (choice == 4) {
                    showStudentReport();
                } else if (choice == 5) {
                    searchStudents();
                } else if (choice == 6) {
                    generateSampleData();
                } else if (choice == 7) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please select 1-8.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    // Show the menu options for inside the class
    private void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Add Student");
        System.out.println("2. Add Student from Existing Class");
        System.out.println("3. Add Grade");
        System.out.println("4. View Student Report");
        System.out.println("5. Search Students");
        System.out.println("6. Generate Sample Data");
        System.out.println("7. Back to Class Menu");
        System.out.print("Enter choice: ");
    }

    // Input validation
    private int getValidChoice() {
        try {
            int choice = input.nextInt();
            input.nextLine(); 
            return choice;
        } catch (Exception e) {
            input.nextLine();
            System.out.println("Please enter a number 1-8.");
            return -1;
        }
    }
    // Get valid name from user (runs twice for first and last name)
    private String getValidName(String prompt) throws Exception {
        System.out.print(prompt);
        String name = input.nextLine();

        if (name.isEmpty()) {
            throw new Exception("Name cannot be empty");
        }
        if (name.length() < 2) {
            throw new Exception("Name must be at least 2 characters");
        }

        return name;
    }
    // Get valid grade from user (0-100) throws BadGradeException if out of range
    private double getValidGrade() throws BadGradeException {
        try {
            System.out.print("Enter grade (0-100) or -1 to stop: ");
            double grade = input.nextDouble();
            input.nextLine(); 

            if (grade == -1) return -1;

            if (grade < 0 || grade > 100) {
                throw new BadGradeException("Grade must be between 0 and 100. You entered: " + grade);
            }
            if (grade != 0 && Math.round(grade * 1000) % 10 != 0) {
                throw new BadGradeException("Grade cannot have more than two decimals places. You entered: " + grade);
            }

            return grade;

        // Catch and throw BadGradeException if grade is out of range
        } catch (Exception e) {
            if (e instanceof BadGradeException) {
                throw e;
            }
            input.nextLine(); 
            throw new BadGradeException("Please enter a valid number between 0-100");
        }
    }

    // Menu operations
    // Add student and optionally add grades
    private void addStudent() {
        try {
            String firstName = getValidName("First name: ");
            String lastName = getValidName("Last name: ");
            
            gradeManager.addStudent(firstName, lastName);
            System.out.println("Student added: " + firstName + " " + lastName);

            System.out.print("Add grades now? (y/n): ");
            String answer = input.nextLine().trim();
            answer = answer.toLowerCase();
            if (answer.isEmpty()) answer = "n";
            if (!answer.equals("y") && !answer.equals("n"))
                answer = "n";
            if (answer.equals("n")) return;

            // Ask if user wants to add grades to the newly added student
            if (answer.equals("y")) {
                // Get the newly added student then add grades
                Student newStudent = gradeManager.getAllStudents().get(gradeManager.getStudentCount() - 1);
                addGradesToStudent(newStudent);
            }

        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    // Add grades to an existing student
    private void addGrade() {
        try {
            gradeManager.checkIfStudentsExist();

            Student student = selectStudent();
            if (student != null) {
                addGradesToStudent(student);
            }

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Method to add grades to a student
    private void addGradesToStudent(Student student) {
        System.out.println("Adding grades for: " + student.getFullName());

        while (true) {
            try {
                double grade = getValidGrade();

                if (grade == -1) {
                    break;
                }

                student.addTestScore(grade);
                System.out.println("Grade added: " + grade);

            } catch (BadGradeException e) {
                System.out.println("Error: " + e.getMessage()); 
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    // Generate and display reports for students and class
    // Search throug all students and display report
    private void showStudentReport() {
        try {
            gradeManager.checkIfStudentsExist();

            Student student = selectStudent();
            if (student != null) {
                String report = reportGenerator.generateStudentReport(student);
                System.out.println(report);
            }

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Generate and display class report
    private void showClassReport() {
        try {
            gradeManager.checkIfStudentsExist();

            String report = reportGenerator.generateClassReport(gradeManager);
            System.out.println(report);

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Generate and display class statistics
    private void showStatistics() {
        try {
            gradeManager.checkIfStudentsExist();

            String report = reportGenerator.generateStatisticsReport(gradeManager);
            System.out.println(report);

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Search for students by first and last name
    private void searchStudents() {
        try {
            gradeManager.checkIfStudentsExist();

            String firstName = getValidName("Enter first name: ");
            String lastName = getValidName("Enter last name: ");

            Student found = gradeManager.findStudent(firstName, lastName);
            // Display student info if found
            if (found != null) {
                System.out.println("Found: " + found.getFullName());
                System.out.println("Tests: " + found.getNumberOfTests());
                if (found.getNumberOfTests() > 0) {
                    System.out.println("Average: " + found.getAverageScore());
                    System.out.println("GPA: " + found.getGPA());
                }
            } else {
                System.out.println("Student not found.");
            }

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Generate sample data for testing purposes
    // Displays a summary of the generated data
    private void generateSampleData() {
        try {
            System.out.println("Generating sample data...");
            gradeManager.generateSampleData();

            String report = reportGenerator.generateSampleDataSummary(gradeManager);
            System.out.println(report);

        } catch (Exception e) {
            System.out.println("Error generating sample data: " + e.getMessage());
        }
    }
    // Select a student from the current class (used in addGrade and showStudentReport)
    private Student selectStudent() {
        try {
            ArrayList<Student> students = gradeManager.getStudentsFromClass(currentClass);

            if (students.isEmpty()) {
                System.out.println("No students in Class " + currentClass);
                return null;
            }

            System.out.println("\nStudents in Class " + currentClass + ":");
            // Display all students in the current class
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                System.out.println((i + 1) + ". " + student.getFullName() + 
                                 " (Tests: " + student.getNumberOfTests() + ")");
            }

            System.out.print("Select student (1-" + students.size() + ") or 0 to cancel: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 0) {
                return null;
            }

            if (choice >= 1 && choice <= students.size()) {
                return gradeManager.getStudent(choice - 1);
            } else {
                System.out.println("Invalid selection.");
                return null;
            }

        } catch (Exception e) {
            input.nextLine(); 
            System.out.println("Please enter a valid number.");
            return null;
        }
    }
    // Add a new class
    private void addClass() {
        System.out.println("Adding a new class...");
        currentClass++;
        System.out.println("Class " + currentClass + " added.");
        System.out.println("Now managing Class " + currentClass);
    }
    // Add a student from an existing class to the current class
    private void addStudentFromExistingClass(int currentClassNumber) {
        try {
            // Get all students from OTHER classes (not current class)
            ArrayList<Student> otherClassStudents = gradeManager.getStudentsFromOtherClasses(currentClassNumber);

            if (otherClassStudents.isEmpty()) {
                System.out.println("No students found in other classes.");
                return;
            }

            // Show students from other classes
            System.out.println("\nStudents from other classes:");
            for (int i = 0; i < otherClassStudents.size(); i++) {
                Student student = otherClassStudents.get(i);
                System.out.println((i + 1) + ". " + student.getFullName() + 
                                 " (Classes: " + student.getClassesTaken() + ")");
            }

            System.out.print("Select student (1-" + otherClassStudents.size() + ") or 0 to cancel: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 0) return;

            if (choice >= 1 && choice <= otherClassStudents.size()) {
                Student selectedStudent = otherClassStudents.get(choice - 1);

                // Add current class to their classes taken list
                selectedStudent.getClassesTaken().add(currentClassNumber);

                System.out.println("Student " + selectedStudent.getFullName() + 
                                 " added to Class " + currentClassNumber);
                System.out.println("Now in classes: " + selectedStudent.getClassesTaken());
            } else {
                System.out.println("Invalid selection.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}