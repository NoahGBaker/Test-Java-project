import java.util.Scanner;
public class MenuSystem {

  private static Scanner scanner = new Scanner(System.in);
  private static GradeManagerApp gradeManagerApp = new GradeManagerApp();
  private StudentA[] class1 = new StudentA[1000];

  
  public static void main(String[] args) {
    System.out.println("Menu System");
    while (true) {
      System.out.println("Please select an option:");
      System.out.println("1. Add Student");
      System.out.println("2. Enter/Update Grades");
      System.out.println("3. View Student Report");
      System.out.println("4. Generate Class Report");
      System.out.println("5. Calculate Statistics");
      System.out.println("6. Search Students");
      System.out.println("7. Generate Sample Statistics");
      System.out.println("8. Exit");
      int option = scanner.nextInt();
      if (option == 1) {
        System.out.println("Please enter the student's name:");
        String name = scanner.next();
        System.out.println("Please enter the student's lastname:");
        String lastname = scanner.next();
        System.out.println("Would you like do add any existing grades? (y/n)");
        String answer = scanner.next();
        while (answer.equals("y")) {
          System.out.println("Please enter the test score:");
          double score = scanner.nextDouble();
          StudentA currentStudent = gradeManagerApp.currentStudent;
          currentStudent.addTestScore(score);
          System.out.println("Test score added.");
          System.out.println("Would you like to add another test score? (y/n)");
          answer = scanner.next();
        }
        if (gradeManagerApp != null) {
          try {
            System.out.println("Adding student: " + name + " " + lastname);
            gradeManagerApp.addStudent(name, lastname);
          } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
          }
        } else {
          System.out.println("gradeManagerApp is null");
        }
      }
      else if (option == 2) {
        handleGradeEntry();
      }
      else if (option == 3) {
        handleStudentReport();
      }
      else if (option == 4) {
        handleClassReport();
      }
      else if (option == 5) {
        handleStatistics();
      }
      else if (option == 6) {
        handleSearchStudents();
      }
      else if (option == 7) {
        StatisticsGenerator.displaySampleStatistics();
      }
      else if (option == 8) {
        System.out.println("Goodbye!");
        break;
      }
      else {
        System.out.println("Invalid option. Please select 1-8.");
      }
    }
    scanner.close();
  }

  private static void handleGradeEntry() {
    if (gradeManagerApp.currentStudent == null) {
      System.out.println("No student selected. Please add a student first.");
      return;
    }
    
    System.out.println("Current student: " + gradeManagerApp.currentStudent.getName() + " " + gradeManagerApp.currentStudent.getLastname());
    System.out.println("1. Add Test Score");
    System.out.println("2. Remove Test Score");
    System.out.println("3. View Current Scores");
    System.out.println("4. Back to Main Menu");
    
    int choice = scanner.nextInt();
    
    if (choice == 1) {
      System.out.println("Please enter the test score (0-100):");
      double score = scanner.nextDouble();
      if (score >= 0 && score <= 100) {
        gradeManagerApp.addTestScore(score);
      } else {
        System.out.println("Invalid score. Please enter a score between 0 and 100.");
      }
    }
    else if (choice == 2) {
      if (gradeManagerApp.currentStudent.getNumberOfTests() == 0) {
        System.out.println("No test scores to remove.");
        return;
      }
      System.out.println("Current scores:");
      System.out.println(gradeManagerApp.currentStudent.getTestScoreReport());
      System.out.println("Please enter the score to remove:");
      double score = scanner.nextDouble();
      gradeManagerApp.removeTestScore(score);
    }
    else if (choice == 3) {
      if (gradeManagerApp.currentStudent.getNumberOfTests() == 0) {
        System.out.println("No test scores available.");
      } else {
        System.out.println(gradeManagerApp.currentStudent.getTestScoreReport());
      }
    }
  }
  
  private static void handleStudentReport() {
    if (gradeManagerApp.currentStudent == null) {
      System.out.println("No student selected. Please add a student first.");
      return;
    }
    
    StudentA student = gradeManagerApp.currentStudent;
    System.out.println("\n=== Student Report ===");
    System.out.println("Student: " + student.getName() + " " + student.getLastname());
    System.out.println("Student ID: " + student.getStudentID());
    System.out.println("Number of Tests: " + student.getNumberOfTests());
    
    if (student.getNumberOfTests() > 0) {
      System.out.println("Average Score: " + student.getAverageScore());
      System.out.println("GPA: " + student.getGpa());
      System.out.println("\nTest Scores:");
      System.out.println(student.getTestScoreReport());
    } else {
      System.out.println("No test scores available.");
    }
  }
  
  private static void handleClassReport() {
    if (gradeManagerApp.currentStudent == null) {
      System.out.println("No students in the system. Please add students first.");
      return;
    }
    
    System.out.println("\n=== Class Report ===");
    
    handleStudentReport();
    
    try {
      ReportGenerator reportGen = new ReportGenerator();
      System.out.println("\n" + reportGen.generateReport(gradeManagerApp.currentStudent));
    } catch (Exception e) {
      System.out.println("Error generating report: " + e.getMessage());
    }
  }
  
  private static void handleStatistics() {
    if (gradeManagerApp.currentStudent == null) {
      System.out.println("No student selected for statistics.");
      return;
    }
    
    StatisticsGenerator.displayStudentStatistics(gradeManagerApp.currentStudent);
  }
  
  private static void handleSearchStudents() {
    System.out.println("\n=== Search Students ===");
    
    if (gradeManagerApp.currentStudent != null) {
      System.out.println("Current student: " + gradeManagerApp.currentStudent.getName() + " " + gradeManagerApp.currentStudent.getLastname());
      System.out.println("Would you like to view this student's details? (y/n)");
      String answer = scanner.next();
      if (answer == "y") {
        handleStudentReport();
      }
    } else {
      System.out.println("No students in the system.");
    }
  }
}