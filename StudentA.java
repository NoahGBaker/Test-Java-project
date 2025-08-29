import java.util.ArrayList;
public class StudentA {
  private String name;
  private String lastname;
  private ArrayList<Double> testScores;
  private int numberOfTests;
  private static int studentID = 0;

  public StudentA(String name, String lastname, int numberOfTests) {
    this.name = name;
    this.lastname = lastname;
    this.numberOfTests = numberOfTests;
    this.testScores = new ArrayList<Double> = numberOfTests;
    this.studentID++;
  }

  // Getters
  public int getStudentID() {
    return this.studentID;
  }
  public String getName() {
    return this.name;
  }
  public String getLastname() {
    return this.lastname;
  }
  public ArrayList<Double> getTestScores() {
    return this.testScores;
  }
  public int getNumberOfTests() {
    return this.numberOfTests;
  }
  public double getAverageScore() {
    double average = 0;
    for (double score : this.testScores) {
      average += score;
    }
    return average / this.numberOfTests;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public void setTestScores(ArrayList<Double> testScores) {
    this.testScores = testScores;
  }
  public void setNumberOfTests(int numberOfTests) {
    this.numberOfTests = numberOfTests;
  }
  public void addTestScore(double score) {
    this.testScores.add(score);
    this.numberOfTests++;
  }

  // Methods

  public String printStudentReport() {
    String testScoreReports = "";
    for (double score : this.testScores) {
      testScoreReports += score + "\n";
    }
    return "Student ID: " + this.studentID + "\nName: " + this.name + " " + this.lastname + "\nTestScores: " + testScoreReports +  "\nAverage Score: " + this.getAverageScore();
  }
}