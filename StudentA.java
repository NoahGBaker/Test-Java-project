import java.util.ArrayList;
import java.util.Arrays;

public class StudentA {
  private String name;
  private String lastname;
  private double[] testScores;
  private int numberOfTests;
  private static int studentID = 0;


  public StudentA() {
    this.name = "";
    this.lastname = "";
    this.testScores = new double[1000];
    this.numberOfTests = 0;
  }
  
  public StudentA(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
    this.testScores = new double[1000];
    this.numberOfTests = 0;
    StudentA.studentID++;
  }

  public StudentA(String name, String lastname, double[] testScores) {
    this.name = name;
    this.lastname = lastname;
    this.testScores = testScores;
    this.numberOfTests = testScores.length;
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
    double result = average / this.numberOfTests;
    result = Math.round(result * 100.0) / 100.0;
    return result;
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
      double testScore = this.testScores[i];
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
    return testScoreReports;
  }

  public Double getGpa() {
    double gpa = 0.0;
    for (int i = 0; i < this.numberOfTests; i++) {
      if (this.testScores[i] >= 90) {
        gpa += 4;
      }
      else if (this.testScores[i] >= 80) {
        gpa +=3;
      } 
      else if (this.testScores[i] >= 70) {
        gpa += 2;
      }
      else if (this.testScores[i] >= 60) {
        gpa += 1;
      }
      else {
        gpa += 0;
      }
    }
    double result = gpa / (double) this.numberOfTests;
    result = Math.round(result * 100.0) / 100.0;
    return result;
  }
}