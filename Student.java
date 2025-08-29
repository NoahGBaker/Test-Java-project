import java.util.ArrayList;
public class Student {
  ptivate static final int STUDENT_ID;
  private String name;
  priavte String lastname;
  private ArrayList<Double> testScores;
  private int numberOfTests;
  
  public Student(String name, String lastname, int numberOfTests) {
    this.STUDENT_ID++;
    this.name = name;
    this.lastname = lastname;
    this.numberOfTests = numberOfTests;
    this.testScores = new ArrayList<Double> numberOfTests;
    
  }

  // Getters
  
  public int getStudentID() {
    return this.STUDENT_ID;
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
  public int getNumberOfTests()
    return this.numberOfTests;
  }
  public double getAverageScore() {
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
  public void setTestScores(double[] testScores) {
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
    return "Student ID: " + this.STUDENT_ID + "\nName: " + this.name + " " + this.lastname + "\nAverage Score: " + this.getAverageScore();
  }
}