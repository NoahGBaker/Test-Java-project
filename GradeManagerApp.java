
class GradeManagerApp {
  public static void main(String[] args) {
    ReportGenerator reportGenerator = new ReportGenerator();
    StudentA student1 = new StudentA("John", "Doe");
    StudentA student2 = new StudentA("Jane", "Doe");
    StudentA student3 = new StudentA("Jack", "Doe");
    StudentA student4 = new StudentA("Jill", "Doe");
    StudentA student5 = new StudentA("Jake", "Doe");
    StudentA student6 = new StudentA("Jenny", "Doe");
    StudentA[] class1 = {student1, student2, student3, student4, student5, student6};
    student1.addTestScore(90.0);
    student1.addTestScore(85.0);
    student1.addTestScore(88.0);
    System.out.println(reportGenerator.generateReport(student1));
    student1.removeTestScore(85.0);
    System.out.println(reportGenerator.generateReport(student1));
    student2.addTestScore(95.0);
    System.out.println(reportGenerator.generateClassReport(class1));
    
  }
  StudentA currentStudent = new StudentA("John", "Doe");

  public void addStudent(String name, String lastname) {
    currentStudent = new StudentA(name, lastname);
  }

  
}
