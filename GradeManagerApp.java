
class GradeManagerApp {
  public static void main(String[] args) {
    ReportGenerator reportGenerator = new ReportGenerator();
    StudentA student1 = new StudentA("John", "Doe");
    student1.addTestScore(90.0);
    student1.addTestScore(85.0);
    student1.addTestScore(88.0);
    System.out.println(reportGenerator.generateReport(student1));
    student1.removeTestScore(85.0);
    System.out.println(reportGenerator.generateReport(student1));
  }
  StudentA currentStudent = new StudentA("John", "Doe");

  public void addStudent(String name, String lastname) {
    currentStudent = new StudentA(name, lastname);
  }
}
