import java.util.ArrayList;
class Student {
    private String firstName;
    private String lastName;
    private double[] testScores;
    private int numberOfTests;
    private static int nextStudentID = 1000;
    private int studentID;
    private ArrayList<Integer> classesTaken;

    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.testScores = new double[100]; // Max 100 tests
        this.numberOfTests = 0;
        this.studentID = nextStudentID++;
    }

    public Student(String firstName, String lastName, int classNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.testScores = new double[100];
        this.numberOfTests = 0;
        this.studentID = nextStudentID++;
        this.classesTaken = new ArrayList<>();
        this.classesTaken.add(classNumber);
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public int getStudentID() { return studentID; }
    public int getNumberOfTests() { return numberOfTests; }
    public double[] getTestScores() { return testScores; }
    public ArrayList<Integer> getClassesTaken() { return classesTaken; }
    public ArrayList<Object> getAllInfo() {
        ArrayList<Object> info = new ArrayList<>();
        info.add(firstName);
        info.add(lastName);
        info.add(studentID);
        info.add(classesTaken);
        return info;
    }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    

    // Grade management
    public void addTestScore(double score) throws BadGradeException {
        if (score < 0 || score > 100) {
            throw new BadGradeException("Grade must be between 0 and 100");
        }
        if (numberOfTests < testScores.length) {
            testScores[numberOfTests] = score;
            numberOfTests++;
        } else {
            throw new RuntimeException("Cannot add more test scores - limit reached");
        }
    }

    public void removeTestScore(double score) {
        for (int i = 0; i < numberOfTests; i++) {
            if (testScores[i] == score) {
                // Shift remaining scores left
                for (int j = i; j < numberOfTests - 1; j++) {
                    testScores[j] = testScores[j + 1];
                }
                numberOfTests--;
                break;
            }
        }
    }

    // GPA calculations
    public double getAverageScore() {
        if (numberOfTests == 0) return 0.0;

        double sum = 0;
        for (int i = 0; i < numberOfTests; i++) {
            sum += testScores[i];
        }
        return Math.round((sum / numberOfTests) * 100.0) / 100.0;
    }

    public double getGPA() {
        if (numberOfTests == 0) return 0.0;

        double totalPoints = 0;
        for (int i = 0; i < numberOfTests; i++) {
            if (testScores[i] >= 90) totalPoints += 4.0;
            else if (testScores[i] >= 80) totalPoints += 3.0;
            else if (testScores[i] >= 70) totalPoints += 2.0;
            else if (testScores[i] >= 60) totalPoints += 1.0;
            else totalPoints += 0.0;
        }
        return Math.round((totalPoints / numberOfTests) * 100.0) / 100.0;
    }

    public String getLetterGrade(double score) {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }
}