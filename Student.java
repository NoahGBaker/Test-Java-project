import java.util.ArrayList;

public class Student {
  private static int studentIdCounter = 0;
  private int studentId;
  private String name;
  private String lastname;
  private ArrayList<Double> testScores;
  private int numberOfTests;
  
  public Student(String name, String lastname, int numberOfTests) {
    this.studentId = ++studentIdCounter;
    this.name = name;
    this.lastname = lastname;
    this.numberOfTests = numberOfTests;
    this.testScores = new ArrayList<Double>();
  }

  // Getters
  
  public int getStudentID() {
    return this.studentId;
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
    double average = 0.0;
    for (double score : this.testScores) {
      average += score;
    }
    return average / this.testScores.size();
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
    this.numberOfTests = testScores.size();
  }
  public void setNumberOfTests(int numberOfTests) {
    this.numberOfTests = numberOfTests;
  }
  public void addTestScore(double score) {
    this.testScores.add(score);
  }

  // Methods
  public String printStudentReport() {
    return "Student ID: " + this.studentId + "\nName: " + this.name + " " + this.lastname + "\nAverage Score: " + this.getAverageScore();
  }
}