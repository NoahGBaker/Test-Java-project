import java.util.ArrayList;
import java.util.Scanner;

class GradeManager {
    private ArrayList<Student> students;
    public Integer classNumber;
    
    public GradeManager() {
        this.students = new ArrayList<>();
    }

    // Student collection management
    public void addStudent(String firstName, String lastName) {
        Student newStudent = new Student(firstName, lastName, classNumber);
        students.add(newStudent);
    }

    public void removeStudent(int index) throws Exception {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
        } else {
            throw new Exception("Invalid student index");
        }
    }

    public Student getStudent(int index) throws Exception {
        if (index >= 0 && index < students.size()) {
            return students.get(index);
        } else {
            throw new Exception("Invalid student index");
        }
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public int getStudentCount() {
        return students.size();
    }

    public void checkIfStudentsExist() throws NoStudentsException {
        if (students.isEmpty()) {
            throw new NoStudentsException("No students found. Add students first.");
        }
    }

    // Search functionality
    public Student findStudent(String firstName, String lastName) {
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstName) && 
                student.getLastName().equalsIgnoreCase(lastName)) {
                return student;
            }
        }
        return null;
    }

    public int findStudentIndex(String firstName, String lastName) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getFirstName().equalsIgnoreCase(firstName) && 
                student.getLastName().equalsIgnoreCase(lastName)) {
                return i;
            }
        }
        return -1;
    }

    // Statistical operations
    public double getClassAverage() {
        if (students.isEmpty()) return 0.0;

        double total = 0;
        int studentsWithGrades = 0;

        for (Student student : students) {
            if (student.getNumberOfTests() > 0) {
                total += student.getAverageScore();
                studentsWithGrades++;
            }
        }

        if (studentsWithGrades > 0) {
            return Math.round((total / studentsWithGrades) * 100.0)/100.0;
        } else {
            return 0.0;
        }
    }

    public double getClassGPA() {
        if (students.isEmpty()) return 0.0;

        double total = 0;
        int studentsWithGrades = 0;

        for (Student student : students) {
            if (student.getNumberOfTests() > 0) {
                total += student.getGPA();
                studentsWithGrades++;
            }
        }

        if (studentsWithGrades > 0) {
            return Math.round((total / studentsWithGrades) * 100.0)/100.0;
        } else {
            return 0.0;
        }
    }

    public double getHighestGrade() {
        double highest = 0;
        for (Student student : students) {
            for (int i = 0; i < student.getNumberOfTests(); i++) {
                if (student.getTestScores()[i] > highest) {
                    highest = student.getTestScores()[i];
                }
            }
        }
        return highest;
    }

    public double getLowestGrade() {
        double lowest = 100;
        for (Student student : students) {
            for (int i = 0; i < student.getNumberOfTests(); i++) {
                if (student.getTestScores()[i] < lowest) {
                    lowest = student.getTestScores()[i];
                }
            }
        }
        if (lowest == 100) {
            return 0;
        } else {
            return lowest;
        }
    }

    public int getTotalGradesCount() {
        int total = 0;
        for (Student student : students) {
            total += student.getNumberOfTests();
        }
        return total;
    }

    // Sample data generation
    public void generateSampleData() {
        students.clear(); // Clear existing data

        Student student1 = new Student("John", "Smith", classNumber);
        try {
            int randomTests = (int) (Math.random() * 5) + 1;
            for (int i = 0; i < randomTests; i++) {
                student1.addTestScore(Math.round((85.0 + (Math.random() * 10))*100)/100);
            }
        } catch (BadGradeException e) { /* Should not happen */ }
        students.add(student1);

        Student student2 = new Student("Jane", "Doe", classNumber);
        try {
            int randomTests = (int) (Math.random() * 5) + 1;
            for (int i = 0; i < randomTests; i++) {
                student2.addTestScore(Math.round((70.0 + (Math.random() * 30))*100)/100);
            }
        } catch (BadGradeException e) { /* Should not happen */ }
        students.add(student2);

        Student student3 = new Student("Bob", "Johnson", classNumber);
        try {
            int randomTests = (int) (Math.random() * 5) + 1;
            for (int i = 0; i < randomTests; i++) {
                student3.addTestScore(Math.round((50 + (Math.random() * 50))*100)/100);
            }
        } catch (BadGradeException e) { /* Should not happen */ }
        students.add(student3);

        Student student4 = new Student("Alice", "Brown", classNumber);
        try {
            int randomTests = (int) (Math.random() * 5) + 1;
            for (int i = 0; i < randomTests; i++) {
                student4.addTestScore(Math.round((Math.random() * 95)*100)/100);
            }
        } catch (BadGradeException e) { /* Should not happen */ }
        students.add(student4);

        Student student5 = new Student("Mike", "Wilson", classNumber);
        try {
            int randomTests = (int) (Math.random() * 5) + 1;
            for (int i = 0; i < randomTests; i++) {
                student5.addTestScore(Math.round((85.0 + (Math.random() * 10))*100)/100);
            }
        } catch (BadGradeException e) { /* Should not happen */ }
        students.add(student5);
    }
    // Get all students from a specific class
    public ArrayList<Student> getStudentsFromClass(int classNumber) {
        ArrayList<Student> classStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getClassesTaken().contains(classNumber)) {
                classStudents.add(student);
            }
        }
        return classStudents;
    }
    // Check if there are students in the current class
    public void checkIfStudentsExistInClass(int classNumber) throws NoStudentsException {
        if (getStudentsFromClass(classNumber).isEmpty()) {
            throw new NoStudentsException("No students found in Class " + classNumber + ". Add students first.");
        }
    }
    public ArrayList<Student> getStudentsFromOtherClasses(int excludeClassNumber) {
        ArrayList<Student> otherStudents = new ArrayList<>();
        for (Student student : students) {
            if (!student.getClassesTaken().contains(excludeClassNumber)) {
                otherStudents.add(student);
            }
        }
        return otherStudents;
    }
}