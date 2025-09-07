import java.util.ArrayList;

class GradeManagerApp {
    private ArrayList<StudentA> students;
    
    public GradeManagerApp() {
        this.students = new ArrayList<>();
    }
    
    public void addStudent(String name, String lastname) {
        StudentA newStudent = new StudentA(name, lastname);
        students.add(newStudent);
        System.out.println("Student added: " + newStudent.getName() + " " + newStudent.getLastname());
    }
    
    public void addTestScore(int studentIndex, double score) {
        if (studentIndex >= 0 && studentIndex < students.size()) {
            students.get(studentIndex).addTestScore(score);
            System.out.println("Test score added: " + score);
        } else {
            System.out.println("Invalid student selection.");
        }
    }
    
    public void removeTestScore(int studentIndex, double score) {
        if (studentIndex >= 0 && studentIndex < students.size()) {
            students.get(studentIndex).removeTestScore(score);
            System.out.println("Test score removed: " + score);
        } else {
            System.out.println("Invalid student selection.");
        }
    }
    
    public ArrayList<StudentA> getStudents() {
        return students;
    }
    
    public StudentA getStudent(int index) {
        if (index >= 0 && index < students.size()) {
            return students.get(index);
        }
        return null;
    }
    
    public int getStudentCount() {
        return students.size();
    }
    
    public StudentA findStudent(String name, String lastname) {
        for (StudentA student : students) {
            if (student.getName().equalsIgnoreCase(name) && 
                student.getLastname().equalsIgnoreCase(lastname)) {
                return student;
            }
        }
        return null;
    }
    
    public int findStudentIndex(String name, String lastname) {
        for (int i = 0; i < students.size(); i++) {
            StudentA student = students.get(i);
            if (student.getName().equalsIgnoreCase(name) && 
                student.getLastname().equalsIgnoreCase(lastname)) {
                return i;
            }
        }
        return -1;
    }
    
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the class.");
            return;
        }
        
        System.out.println("\n=== All Students ===");
        for (int i = 0; i < students.size(); i++) {
            StudentA student = students.get(i);
            System.out.println((i + 1) + ". " + student.getName() + " " + student.getLastname() + 
                             " (Tests: " + student.getNumberOfTests() + ")");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("GradeManagerApp running...");
        
        // Create an instance of GradeManagerApp
        GradeManagerApp app = new GradeManagerApp();
        
        // Add multiple students
        app.addStudent("John", "Doe");
        app.addStudent("Jane", "Smith");
        app.addStudent("Bob", "Johnson");
        
        // Add some test scores to different students
        app.addTestScore(0, 85.5); // John
        app.addTestScore(0, 92.0);
        app.addTestScore(0, 78.5);
        
        app.addTestScore(1, 88.0); // Jane
        app.addTestScore(1, 91.5);
        
        app.addTestScore(2, 76.0); // Bob
        app.addTestScore(2, 82.5);
        app.addTestScore(2, 89.0);
        
        // Display class information
        System.out.println("\n=== Class Summary ===");
        System.out.println("Total Students: " + app.students.size());
        
        if (!app.students.isEmpty()) {
            System.out.println("\n=== All Student Reports ===");
            for (int i = 0; i < app.students.size(); i++) {
                StudentA student = app.students.get(i);
                System.out.println("\n[" + (i + 1) + "] " + student.getName() + " " + student.getLastname());
                System.out.println("    Tests: " + student.getNumberOfTests());
                if (student.getNumberOfTests() > 0) {
                    System.out.println("    Average: " + String.format("%.2f", student.getAverageScore()));
                    System.out.println("    GPA: " + String.format("%.2f", student.getGpa()));
                } else {
                    System.out.println("    No test scores yet");
                }
            }
        }
    }
}