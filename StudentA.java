import java.util.ArrayList;
import java.util.Arrays;

public class StudentA {
  // Grade scale constants
  private static final double MAX_GRADE = 100.0;
  private static final double MIN_GRADE = 0.0;
  
  private String name;
  private String lastname;
  private double[] testScores;
  private int numberOfTests;
  private static int studentID = 0;

  public StudentA(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
    this.testScores = new double[10];
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
      return 0.0; // Avoid division by zero
    }
    double average = 0;
    for (int i = 0; i < this.numberOfTests; i++) { // Iterate based on numberOfTests
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
    if (score < MIN_GRADE || score > MAX_GRADE) {
      System.out.println("Invalid score: " + score + ". Score must be between " + MIN_GRADE + " and " + MAX_GRADE);
      return;
    }
    
    if (this.numberOfTests < this.testScores.length) {
      this.testScores[this.numberOfTests] = score;
      this.numberOfTests++;
    } else {
      // Handle the case where the array is full (resize or throw an exception)
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

  // Grade scale methods
  public String getLetterGrade(double score) {
    if (score >= 97) return "A+";
    else if (score >= 93) return "A";
    else if (score >= 90) return "A-";
    else if (score >= 87) return "B+";
    else if (score >= 83) return "B";
    else if (score >= 80) return "B-";
    else if (score >= 77) return "C+";
    else if (score >= 73) return "C";
    else if (score >= 70) return "C-";
    else if (score >= 67) return "D+";
    else if (score >= 63) return "D";
    else if (score >= 60) return "D-";
    else return "F";
  }
  
  public double getGradePoints(double score) {
    if (score >= 97) return 4.0;
    else if (score >= 93) return 4.0;
    else if (score >= 90) return 3.7;
    else if (score >= 87) return 3.3;
    else if (score >= 83) return 3.0;
    else if (score >= 80) return 2.7;
    else if (score >= 77) return 2.3;
    else if (score >= 73) return 2.0;
    else if (score >= 70) return 1.7;
    else if (score >= 67) return 1.3;
    else if (score >= 63) return 1.0;
    else if (score >= 60) return 0.7;
    else return 0.0;
  }
  
  public String getOverallLetterGrade() {
    return getLetterGrade(getAverageScore());
  }
  
  public double getGPA() {
    return getGradePoints(getAverageScore());
  }
  
  public static double getMaxGrade() {
    return MAX_GRADE;
  }
  
  public static double getMinGrade() {
    return MIN_GRADE;
  }

  // Methods
  public String printStudentReport() {
    String testScoreReports = "";
    for (int i = 0; i < this.numberOfTests; i++) {
      testScoreReports += this.testScores[i] + " (" + getLetterGrade(this.testScores[i]) + ")\n";
    }
    return "Student ID: " + StudentA.studentID + 
           "\nName: " + this.name + " " + this.lastname + 
           "\nTest Scores: \n" + testScoreReports + 
           "\nAverage Score: " + String.format("%.2f", this.getAverageScore()) + 
           "\nOverall Grade: " + this.getOverallLetterGrade() + 
           "\nGPA: " + String.format("%.2f", this.getGPA()) + 
           "\nGrade Scale: " + MIN_GRADE + "-" + MAX_GRADE;
  }
}