import java.util.Scanner;
import java.util.ArrayList;

public class MenuSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static GradeManagerApp gradeManagerApp = new GradeManagerApp();
    
    public static void main(String[] args) {
        System.out.println("Grade Management System");
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add Student");
            System.out.println("2. Enter/Update Grades");
            System.out.println("3. View Student Report");
            System.out.println("4. Generate Class Report");
            System.out.println("5. Calculate Statistics");
            System.out.println("6. Search Students");
            System.out.println("7. Generate Sample Statistics");
            System.out.println("8. Exit");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (option) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleGradeEntry();
                    break;
                case 3:
                    handleStudentReport();
                    break;
                case 4:
                    handleClassReport();
                    break;
                case 5:
                    handleStatistics();
                    break;
                case 6:
                    handleSearchStudents();
                    break;
                case 7:
                    StatisticsGenerator.displaySampleStatistics();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please select 1-8.");
            }
        }
    }
    
    private static void handleAddStudent() {
        System.out.print("Please enter the student's first name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter the student's last name: ");
        String lastname = scanner.nextLine();
        
        gradeManagerApp.addStudent(name, lastname);
        
        System.out.print("Would you like to add any existing grades? (y/n): ");
        String answer = scanner.nextLine();
        
        if (answer.equalsIgnoreCase("y")) {
            int studentIndex = gradeManagerApp.getStudentCount() - 1; // Just added student
            while (true) {
                System.out.print("Please enter the test score (0-100) or -1 to finish: ");
                double score = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                
                if (score == -1) break;
                
                if (score >= 0 && score <= 100) {
                    gradeManagerApp.addTestScore(studentIndex, score);
                } else {
                    System.out.println("Invalid score. Please enter a score between 0 and 100.");
                }
            }
        }
    }
    
    private static void handleGradeEntry() {
        if (gradeManagerApp.getStudentCount() == 0) {
            System.out.println("No students in the class. Please add a student first.");
            return;
        }
        
        int studentIndex = selectStudent();
        if (studentIndex == -1) return;
        
        StudentA student = gradeManagerApp.getStudent(studentIndex);
        System.out.println("Selected student: " + student.getName() + " " + student.getLastname());
        
        System.out.println("1. Add Test Score");
        System.out.println("2. Remove Test Score");
        System.out.println("3. View Current Scores");
        System.out.println("4. Back to Main Menu");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                System.out.print("Please enter the test score (0-100): ");
                double score = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                if (score >= 0 && score <= 100) {
                    gradeManagerApp.addTestScore(studentIndex, score);
                } else {
                    System.out.println("Invalid score. Please enter a score between 0 and 100.");
                }
                break;
            case 2:
                if (student.getNumberOfTests() == 0) {
                    System.out.println("No test scores to remove.");
                    return;
                }
                System.out.println("Current scores:");
                System.out.println(student.getTestScoreReport());
                System.out.print("Please enter the score to remove: ");
                double scoreToRemove = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                gradeManagerApp.removeTestScore(studentIndex, scoreToRemove);
                break;
            case 3:
                if (student.getNumberOfTests() == 0) {
                    System.out.println("No test scores available.");
                } else {
                    System.out.println("Current scores for " + student.getName() + " " + student.getLastname() + ":");
                    System.out.println(student.getTestScoreReport());
                }
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void handleStudentReport() {
        if (gradeManagerApp.getStudentCount() == 0) {
            System.out.println("No students in the class. Please add a student first.");
            return;
        }
        
        int studentIndex = selectStudent();
        if (studentIndex == -1) return;
        
        StudentA student = gradeManagerApp.getStudent(studentIndex);
        
        System.out.println("\n=== Student Report ===");
        System.out.println("Student: " + student.getName() + " " + student.getLastname());
        System.out.println("Student ID: " + student.getStudentID());
        System.out.println("Number of Tests: " + student.getNumberOfTests());
        
        if (student.getNumberOfTests() > 0) {
            System.out.println("Average Score: " + String.format("%.2f", student.getAverageScore()));
            System.out.println("GPA: " + String.format("%.2f", student.getGpa()));
            System.out.println("\nTest Scores:");
            System.out.println(student.getTestScoreReport());
        } else {
            System.out.println("No test scores available.");
        }
    }
    
    private static void handleClassReport() {
        if (gradeManagerApp.getStudentCount() == 0) {
            System.out.println("No students in the class. Please add students first.");
            return;
        }
        
        System.out.println("\n=== Class Report ===");
        System.out.println("Total Students: " + gradeManagerApp.getStudentCount());
        
        ArrayList<StudentA> students = gradeManagerApp.getStudents();
        
        // Class statistics
        double classTotal = 0;
        int totalTests = 0;
        int studentsWithTests = 0;
        
        for (StudentA student : students) {
            if (student.getNumberOfTests() > 0) {
                classTotal += student.getAverageScore();
                totalTests += student.getNumberOfTests();
                studentsWithTests++;
            }
        }
        
        if (studentsWithTests > 0) {
            double classAverage = classTotal / studentsWithTests;
            System.out.println("Class Average: " + String.format("%.2f", classAverage));
            System.out.println("Total Tests Administered: " + totalTests);
        }
        
        System.out.println("\n=== Individual Student Reports ===");
        for (int i = 0; i < students.size(); i++) {
            StudentA student = students.get(i);
            System.out.println("\n[" + (i + 1) + "] " + student.getName() + " " + student.getLastname());
            System.out.println("    Tests: " + student.getNumberOfTests());
            if (student.getNumberOfTests() > 0) {
                System.out.println("    Average: " + String.format("%.2f", student.getAverageScore()));
                System.out.println("    GPA: " + String.format("%.2f", student.getGpa()));
            } else {
                System.out.println("    No test scores yet");
            }
        }
        
        // Try to generate a formal report using ReportGenerator
        try {
            ReportGenerator reportGen = new ReportGenerator();
            if (!students.isEmpty() && students.get(0).getNumberOfTests() > 0) {
                System.out.println("\n=== Sample Formal Report (First Student) ===");
                System.out.println(reportGen.generateReport(students.get(0)));
            }
        } catch (Exception e) {
            System.out.println("Note: ReportGenerator not available.");
        }
    }
    
    private static void handleStatistics() {
        if (gradeManagerApp.getStudentCount() == 0) {
            System.out.println("No students in the class for statistics.");
            return;
        }
        
        System.out.println("1. Individual Student Statistics");
        System.out.println("2. Class-Wide Statistics");
        System.out.println("3. Back to Main Menu");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                int studentIndex = selectStudent();
                if (studentIndex != -1) {
                    StudentA student = gradeManagerApp.getStudent(studentIndex);
                    StatisticsGenerator.displayStudentStatistics(student);
                }
                break;
            case 2:
                displayClassStatistics();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void displayClassStatistics() {
        ArrayList<StudentA> students = gradeManagerApp.getStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students in the class.");
            return;
        }
        
        System.out.println("\n=== Class-Wide Statistics ===");
        System.out.println("Total Students: " + students.size());
        
        int studentsWithTests = 0;
        double totalGPA = 0;
        double totalAverage = 0;
        double highestScore = 0;
        double lowestScore = 100;
        
        for (StudentA student : students) {
            if (student.getNumberOfTests() > 0) {
                studentsWithTests++;
                totalGPA += student.getGpa();
                totalAverage += student.getAverageScore();
                
                double[] scores = student.getTestScores();
                for (int i = 0; i < student.getNumberOfTests(); i++) {
                    if (scores[i] > highestScore) highestScore = scores[i];
                    if (scores[i] < lowestScore) lowestScore = scores[i];
                }
            }
        }
        
        if (studentsWithTests > 0) {
            System.out.println("Students with test scores: " + studentsWithTests);
            System.out.println("Class GPA Average: " + String.format("%.2f", totalGPA / studentsWithTests));
            System.out.println("Class Score Average: " + String.format("%.2f", totalAverage / studentsWithTests));
            System.out.println("Highest Score in Class: " + String.format("%.2f", highestScore));
            System.out.println("Lowest Score in Class: " + String.format("%.2f", lowestScore));
        } else {
            System.out.println("No students have test scores yet.");
        }
    }
    
    private static void handleSearchStudents() {
        if (gradeManagerApp.getStudentCount() == 0) {
            System.out.println("No students in the class.");
            return;
        }
        
        System.out.println("\n=== Search Students ===");
        System.out.println("1. Search by name");
        System.out.println("2. List all students");
        System.out.println("3. Back to Main Menu");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                
                StudentA found = gradeManagerApp.findStudent(firstName, lastName);
                if (found != null) {
                    System.out.println("Student found: " + found.getName() + " " + found.getLastname());
                    System.out.println("Tests: " + found.getNumberOfTests());
                    if (found.getNumberOfTests() > 0) {
                        System.out.println("Average: " + String.format("%.2f", found.getAverageScore()));
                        System.out.println("GPA: " + String.format("%.2f", found.getGpa()));
                    }
                } else {
                    System.out.println("Student not found.");
                }
                break;
            case 2:
                gradeManagerApp.displayAllStudents();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static int selectStudent() {
        gradeManagerApp.displayAllStudents();
        System.out.print("Select a student (enter number, or 0 to cancel): ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (selection == 0) {
            return -1;
        }
        
        if (selection >= 1 && selection <= gradeManagerApp.getStudentCount()) {
            return selection - 1; // Convert to 0-based index
        } else {
            System.out.println("Invalid selection.");
            return -1;
        }
    }
}