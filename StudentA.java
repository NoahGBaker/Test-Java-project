import java.util.ArrayList;
import java.util.Arrays;

public class StudentA {
  private String name;
  private String lastname;
  private double[] testScores;
  private int numberOfTests;
  private static int studentID = 0;

  public StudentA(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
    this.testScores = new double[1000];
    this.numberOfTests = 0;
    StudentA.studentID++;
  }

  // Getters
  public int getStudentID() {
    return StudentA.studentID;
  }
  public String getName() {
    return this.name;
  }
  public String getLastname() {
    return this.lastname;
  }
  public double[] getTestScores() {
    return this.testScores;
  }
  public int getNumberOfTests() {
    return this.numberOfTests;
  }
  public double getAverageScore() {
    if (this.numberOfTests == 0) {
      return 0.0;
    }
    double average = 0;
    for (int i = 0; i < this.numberOfTests; i++) { 
      average += this.testScores[i];
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
  public void setTestScores(double[] testScores) {
    this.testScores = testScores;
  }
  public void setNumberOfTests(int numberOfTests) {
    this.numberOfTests = numberOfTests;
  }
  public void addTestScore(double score) {
    if (this.numberOfTests < this.testScores.length) {
      this.testScores[this.numberOfTests] = score;
      this.numberOfTests++;
    } else {
      System.out.println("Test scores array is full.  Cannot add more scores.");
    }

  }
  public void removeTestScore(double score)  {
    int index = -1;
    for (int i = 0; i < this.numberOfTests; i++) {
      if (this.testScores[i] == score) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      for (int i = index; i < this.numberOfTests - 1; i++) {
        this.testScores[i] = this.testScores[i + 1];
      }
      this.numberOfTests--;
    }
  }

  // Methods

  public String getTestScoreReport() {
    String testScoreReports = "";
    for (int i = 0; i < this.numberOfTests; i++) {
      testScoreReports += this.testScores[i] + "\n";
    }
    return testScoreReports;
  }
}