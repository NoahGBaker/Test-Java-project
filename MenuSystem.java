import java.util.Scanner;
public class MenuSystem {

  private static Scanner scanner = new Scanner(System.in);
  private static GradeManagerApp gradeManagerApp = new GradeManagerApp();
  
  public static void main(String[] args) {
    System.out.println("Menu System");
    while (true) {
      System.out.println("Please select an option:");
      System.out.println("1. Add Student");
      System.out.println("2. Add Test Score");
      System.out.println("3. Remove Test Score");
      System.out.println("4. Generate Report");
      System.out.println("5. Exit");
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
          StudentA currentStudent = GradeManagerApp.currentStudent;
          currentStudent.addTestScore(score);
          System.out.println("Test score added.");
          System.out.println("Would you like to add another test score? (y/n)");
          answer = scanner.next();
        }
        if (GradeManagerApp != null) {
          try {
            System.out.println("Adding student: " + name + " " + lastname);
            GradeManagerApp.addStudent(name, lastname);
          } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
          }
        } else {
          System.out.println("GradeManagerApp is null");
        }
      }
      else if (option == 2) {
        System.out.println("Please enter the test score:");
        double score = scanner.nextDouble();
        StudentA currentStudent = gradeManagerApp.currentStudent;
        currentStudent.addTestScore(score);
        System.out.println("Test score added.");
      }
    }
  }

  public void checkAll() {
    if (gradeManagerApp.currentStudent == null) {
      System.out.println("No student selected.");
      System.out.println("Please select a student.");
      System.out.println("1. Add Student");
      System.out.println("2. Search for Existing Student");
      System.out.println("3. Exit");
      int option = scanner.nextInt();
      if (option == 1) {
        System.out.println("Please enter the student's name:");
        String name = scanner.next();
        System.out.println("Please enter the student's lastname:");
        String lastname = scanner.next();
        gradeManagerApp.addStudent(name, lastname);
      }
      else if (option == 2) {
        System.out.println("Please enter the student's name:");
        String name = scanner.next();
        System.out.println("Please enter the student's lastname:");
        String lastname = scanner.next();
        for (StudentA student : gradeManagerApp.class1) {
          if (student.getName().equals(name) && student.getLastname().equals(lastname)) {
            gradeManagerApp.currentStudent = student;
            System.out.println("Student found and selected: " + name + " " + lastname);
            break;
          }
        }
      }
      
      return;
    } else {
      System.out.println("Current student: " + gradeManagerApp.currentStudent.getName() + " " + gradeManagerApp.currentStudent.getLastname());
    }
  }
}