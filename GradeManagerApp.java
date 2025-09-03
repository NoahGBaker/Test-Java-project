
class GradeManagerApp {
  public StudentA currentStudent;
  public void addStudent(String name, String lastname) {
    currentStudent = new StudentA(name, lastname);
    System.out.println("Student added: " + currentStudent.getName() + " " + currentStudent.getLastname());
  }
  public void addTestScore(double score) {
    currentStudent.addTestScore(score);
    System.out.println("Test score added: " + score);
  }
  public void removeTestScore(double score) {
    currentStudent.removeTestScore(score);
    System.out.println("Test score removed: " + score);
  }
  public static void main(String[] args) {
    System.out.println("GradeManagerApp running...");
    
    // Create an instance of GradeManagerApp
    GradeManagerApp app = new GradeManagerApp();
    
    // Add a student
    app.addStudent("John", "Doe");
    
    // Add some test scores
    app.addTestScore(85.5);
    app.addTestScore(92.0);
    app.addTestScore(78.5);
    
    // Display student information and grades
    if (app.currentStudent != null) {
      System.out.println("\n=== Student Grade Report ===");
      System.out.println("Student: " + app.currentStudent.getName() + " " + app.currentStudent.getLastname());
      System.out.println("Test Scores: " + app.currentStudent.getTestScores());
      System.out.println("Average Score: " + String.format("%.2f", app.currentStudent.getAverageScore()));
      System.out.println("Letter Grade: " + app.currentStudent.getLetterGrade());
      System.out.println("GPA: " + String.format("%.2f", app.currentStudent.getGpa()));
    }
  }
}
