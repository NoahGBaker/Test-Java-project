import java.util.ArrayList;

class ReportGenerator {
    // Generate reports for students and the class itself
    public String generateStudentReport(Student student) {
        String report = "=== Student Report ===\n";
        report += "Name: " + student.getFullName() + "\n";
        report += "Student ID: " + student.getStudentID() + "\n";
        report += "Number of Tests: " + student.getNumberOfTests() + "\n";

        if (student.getNumberOfTests() > 0) {
            report += "Average Score: " + student.getAverageScore() + "\n";
            report += "GPA: " + student.getGPA() + "\n";
            report += "\nTest Scores:\n";

            double[] scores = student.getTestScores();
            for (int i = 0; i < student.getNumberOfTests(); i++) {
                String letterGrade = student.getLetterGrade(scores[i]);
                report += "Test " + (i + 1) + ": " + scores[i] + " (" + letterGrade + ")\n";
            }
        } else {
            report += "No test scores recorded.\n";
        }

        return report;
    }
    // Generate class report for all students in the class
    public String generateClassReport(GradeManager manager) {
        String report = "=== Class Report ===\n";
        ArrayList<Student> students = manager.getAllStudents();

        report += "Total Students: " + students.size() + "\n";

        if (!students.isEmpty()) {
            report += "Class Average: " + manager.getClassAverage() + "\n";
            report += "Class GPA: " + manager.getClassGPA() + "\n";
            report += "\n=== Individual Students ===\n";

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                report += "\n[" + (i + 1) + "] " + student.getFullName() + "\n";
                report += "    Tests: " + student.getNumberOfTests() + "\n";

                if (student.getNumberOfTests() > 0) {
                    report += "    Average: " + student.getAverageScore() + "\n";
                    report += "    GPA: " + student.getGPA() + "\n";
                } else {
                    report += "    No grades yet\n";
                }
            }
        }

        return report;
    }
    // Generate statistics report for the classq
    public String generateStatisticsReport(GradeManager manager) {
        String report = "=== Class Statistics ===\n";
        report += "Total Students: " + manager.getStudentCount() + "\n";
        report += "Total Grades Recorded: " + manager.getTotalGradesCount() + "\n";

        if (manager.getTotalGradesCount() > 0) {
            report += "Highest Grade: " + manager.getHighestGrade() + "\n";
            report += "Lowest Grade: " + manager.getLowestGrade() + "\n";
            report += "Class Average: " + manager.getClassAverage() + "\n";
            report += "Class GPA: " + manager.getClassGPA() + "\n";
        } else {
            report += "No grades recorded yet.\n";
        }

        return report;
    }
    // Generate sample data summary for testing purposes
    public String generateSampleDataSummary(GradeManager manager) {
        String report = "=== Sample Data Generated ===\n";
        report += "Students added: " + manager.getStudentCount() + "\n";

        ArrayList<Student> students = manager.getAllStudents();
        for (Student student : students) {
            report += "- " + student.getFullName() + " (" + student.getNumberOfTests() + " tests)\n";
        }

        report += "Class Average: " + manager.getClassAverage() + "\n";

        return report;
    }
}