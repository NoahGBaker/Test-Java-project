public class ReportGenerator {
  public ReportGenerator() {
    System.out.println("ReportGenerator created");
  }
  public String generateReport(StudentA student) {
    String testScoreReports = "";
    for (int i = 0; i < student.getNumberOfTests(); i++) {
      double[] testScores = student.getTestScores();
      double testScore = testScores[i];
      if (testScore >= 90) {
        testScoreReports += "Test " + (i + 1) + ": " + testScore + " (A)\n";
      }
      else if (testScore >= 80) {
        testScoreReports += "Test " + (i + 1) + ": " + testScore + " (B)\n";
      }
      else if (testScore >= 70) {
        testScoreReports += "Test " + (i + 1) + ": " + testScore + " (C)\n";
      }
      else if (testScore >= 60) {
        testScoreReports += "Test " + (i + 1) + ": " + testScore + " (D)\n";
      }
      else {
        testScoreReports += "Test " + (i + 1) + ": " + testScore + " (F)\n";
      }
    }
    return "Student ID: " + student.getStudentID() + "\nName: " + student.getName() + " " + student.getLastname() + "\nTest Scores: \n" + testScoreReports +  "\nAverage Score: " + student.getAverageScore() + "\nGPA: " + student.getGpa();
  }

  public String generateClassReport(StudentA[] class1) {
    String classReport = "";
    classReport += "Class Report\n";
    for (int i = 0; i < class1.length; i++) {
      classReport += generateReport(class1[i]) + "\n";
    }
    classReport += "Class Average: " + getClassAverage(class1) + "\n";
    return classReport;
  }

  public double getClassAverage(StudentA[] class1) {
    double classAverage = 0.0;
    for (int i = 0; i < class1.length; i++) {
      classAverage += class1[i].getAverageScore();
    }
    double result = classAverage / (double) class1.length;
    result = Math.round(result * 100.0) / 100.0;
    return result;
  }
}