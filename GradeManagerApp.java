
class GradeManagerApp {
  public StudentA currentStudent;
  public void addStudent(String name, String lastname) {
    currentStudent = new StudentA(name, lastname);
    System.out.println("Student added: " + currentStudent.getName() + " " + currentStudent.getLastname());
  }
}
