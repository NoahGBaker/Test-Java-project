
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
    System.out.print("GradeManagerApp running...");
  }
}
