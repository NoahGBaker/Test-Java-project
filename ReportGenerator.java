public class ReportGenerator {
  public ReportGenerator() {
    System.out.println("ReportGenerator created");
  }
  public String generateReport(StudentA student) {
    return student.printStudentReport();
  }
}