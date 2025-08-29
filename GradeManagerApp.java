class GradeManagerApp {
  public static void main(String[] args) {
    Student student1 = new Student("John", "Doe", 3);
    student1.addTestScore(90.0);
    student1.addTestScore(85.0);
    student1.addTestScore(88.0);
    System.out.println(student1.printStudentReport());
  }
}
