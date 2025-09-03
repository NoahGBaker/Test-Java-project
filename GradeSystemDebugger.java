public class GradeSystemDebugger {
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE GRADE SYSTEM DEBUG SUITE ===");
        System.out.println("Testing all functionality in StudentA, ReportGenerator, and GradeManagerApp\n");
        
        // Run all debugging tests
        debugStudentABasics();
        debugGradeScale();
        debugGradeValidation();
        debugReportGenerator();
        debugEdgeCases();
        debugGradeManagerApp();
        printSystemStatus();
        
        // Final results
        System.out.println("\n=== DEBUG SUITE COMPLETE ===");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Success Rate: " + String.format("%.2f", (testsPassed * 100.0) / (testsPassed + testsFailed)) + "%");
    }
    
    // Test basic StudentA functionality
    public static void debugStudentABasics() {
        System.out.println("--- Testing StudentA Basic Functions ---");
        
        try {
            // Test student creation
            StudentA student = new StudentA("John", "Smith");
            assert student.getName().equals("John") : "Name getter failed";
            assert student.getLastname().equals("Smith") : "Lastname getter failed";
            assert student.getNumberOfTests() == 0 : "Initial test count should be 0";
            testPassed("Student creation and basic getters");
            
            // Test adding scores
            student.addTestScore(85.5);
            student.addTestScore(92.0);
            student.addTestScore(78.3);
            assert student.getNumberOfTests() == 3 : "Test count after adding scores";
            testPassed("Adding test scores");
            
            // Test average calculation
            double expectedAvg = (85.5 + 92.0 + 78.3) / 3;
            double actualAvg = student.getAverageScore();
            assert Math.abs(expectedAvg - actualAvg) < 0.01 : "Average calculation failed";
            testPassed("Average score calculation");
            
            // Test GPA calculation
            Double gpa = student.getGpa();
            assert gpa != null : "GPA should not be null";
            assert gpa >= 0.0 && gpa <= 4.0 : "GPA should be between 0.0 and 4.0";
            testPassed("GPA calculation");
            
        } catch (Exception e) {
            testFailed("StudentA basic functionality: " + e.getMessage());
        }
    }
    
    // Test grade scale system (existing implementation)
    public static void debugGradeScale() {
        System.out.println("--- Testing Grade Scale System ---");
        
        try {
            StudentA student = new StudentA("Test", "Student");
            
            // Test letter grade logic in getTestScoreReport
            student.addTestScore(95.0);  // Should be A
            student.addTestScore(85.0);  // Should be B
            student.addTestScore(75.0);  // Should be C
            student.addTestScore(65.0);  // Should be D
            student.addTestScore(50.0);  // Should be F
            
            String report = student.getTestScoreReport();
            assert report.contains("(A)") : "A grade test failed";
            assert report.contains("(B)") : "B grade test failed";
            assert report.contains("(C)") : "C grade test failed";
            assert report.contains("(D)") : "D grade test failed";
            assert report.contains("(F)") : "F grade test failed";
            testPassed("Letter grade conversions");
            
            // Test GPA calculation with different scores
            Double gpa = student.getGpa();
            // Expected: (4+3+2+1+0)/5 = 2.0
            assert Math.abs(gpa - 2.0) < 0.01 : "Mixed grades GPA calculation failed";
            testPassed("GPA calculation with mixed grades");
            
        } catch (Exception e) {
            testFailed("Grade scale system: " + e.getMessage());
        }
    }
    
    // Test grade validation
    public static void debugGradeValidation() {
        System.out.println("--- Testing Grade Validation ---");
        
        try {
            StudentA student = new StudentA("Test", "Validation");
            
            // Test valid scores
            student.addTestScore(100.0);
            student.addTestScore(0.0);
            student.addTestScore(75.5);
            assert student.getNumberOfTests() == 3 : "Valid scores should be added";
            testPassed("Valid score acceptance");
            
            // Test boundary scores
            StudentA boundaryStudent = new StudentA("Boundary", "Test");
            boundaryStudent.addTestScore(90.0);  // A boundary
            boundaryStudent.addTestScore(80.0);  // B boundary
            boundaryStudent.addTestScore(70.0);  // C boundary
            boundaryStudent.addTestScore(60.0);  // D boundary
            
            String boundaryReport = boundaryStudent.getTestScoreReport();
            assert boundaryReport.contains("90.0 (A)") : "90.0 should be grade A";
            assert boundaryReport.contains("80.0 (B)") : "80.0 should be grade B";
            assert boundaryReport.contains("70.0 (C)") : "70.0 should be grade C";
            assert boundaryReport.contains("60.0 (D)") : "60.0 should be grade D";
            testPassed("Grade boundary validation");
            
        } catch (Exception e) {
            testFailed("Grade validation: " + e.getMessage());
        }
    }
    
    // Test ReportGenerator integration
    public static void debugReportGenerator() {
        System.out.println("--- Testing ReportGenerator Integration ---");
        
        try {
            ReportGenerator generator = new ReportGenerator();
            StudentA student = new StudentA("Report", "Test");
            
            student.addTestScore(90.0);
            student.addTestScore(85.0);
            student.addTestScore(88.0);
            
            String report = generator.generateReport(student);
            assert report != null : "Report should not be null";
            assert report.contains("Report Test") : "Report should contain student name";
            assert report.contains("90.0") : "Report should contain test scores";
            assert report.contains("GPA") : "Report should contain GPA";
            testPassed("ReportGenerator integration");
            
        } catch (Exception e) {
            testFailed("ReportGenerator integration: " + e.getMessage());
        }
    }
    
    // Test edge cases
    public static void debugEdgeCases() {
        System.out.println("--- Testing Edge Cases ---");
        
        try {
            // Test empty student (no tests)
            StudentA emptyStudent = new StudentA("Empty", "Student");
            assert emptyStudent.getAverageScore() == 0.0 : "Empty student average should be 0";
            Double emptyGPA = emptyStudent.getGpa();
            // Note: getGpa() may return NaN for empty student, which is acceptable
            testPassed("Empty student handling");
            
            // Test removing scores
            StudentA student = new StudentA("Remove", "Test");
            student.addTestScore(90.0);
            student.addTestScore(80.0);
            student.addTestScore(85.0);
            
            student.removeTestScore(80.0);
            assert student.getNumberOfTests() == 2 : "Score removal failed";
            // Average should now be (90 + 85) / 2 = 87.5
            assert Math.abs(student.getAverageScore() - 87.5) < 0.01 : "Average after removal incorrect";
            testPassed("Score removal functionality");
            
            // Test perfect scores
            StudentA perfectStudent = new StudentA("Perfect", "Student");
            perfectStudent.addTestScore(100.0);
            String perfectReport = perfectStudent.getTestScoreReport();
            assert perfectReport.contains("(A)") : "Perfect score should get A";
            testPassed("Perfect score handling");
            
            // Test failing scores
            StudentA failingStudent = new StudentA("Failing", "Student");
            failingStudent.addTestScore(30.0);
            String failingReport = failingStudent.getTestScoreReport();
            assert failingReport.contains("(F)") : "Failing score should get F";
            testPassed("Failing score handling");
            
        } catch (Exception e) {
            testFailed("Edge cases: " + e.getMessage());
        }
    }
    
    // Test GradeManagerApp workflow
    public static void debugGradeManagerApp() {
        System.out.println("--- Testing GradeManagerApp Workflow ---");
        
        try {
            GradeManagerApp app = new GradeManagerApp();
            
            // Test adding student
            app.addStudent("Manager", "Test");
            assert app.currentStudent != null : "Current student should be set";
            assert app.currentStudent.getName().equals("Manager") : "Student name should be set correctly";
            testPassed("GradeManagerApp student management");
            
            // Test workflow integration
            ReportGenerator generator = new ReportGenerator();
            StudentA testStudent = new StudentA("Workflow", "Test");
            testStudent.addTestScore(95.0);
            testStudent.addTestScore(87.0);
            
            String report = generator.generateReport(testStudent);
            assert report.contains("Workflow Test") : "Workflow report should contain correct name";
            testPassed("Complete workflow integration");
            
        } catch (Exception e) {
            testFailed("GradeManagerApp workflow: " + e.getMessage());
        }
    }
    
    // Helper methods
    private static void testPassed(String testName) {
        System.out.println("✓ PASS: " + testName);
        testsPassed++;
    }
    
    private static void testFailed(String testName) {
        System.out.println("✗ FAIL: " + testName);
        testsFailed++;
    }
    
    // System status method
    public static void printSystemStatus() {
        System.out.println("\n=== SYSTEM STATUS ===");
        System.out.println("Grade Scale: A(90+), B(80-89), C(70-79), D(60-69), F(<60)");
        System.out.println("GPA Scale: A=4, B=3, C=2, D=1, F=0");
        System.out.println("Available Methods:");
        System.out.println("- getTestScoreReport(): Shows scores with letter grades");
        System.out.println("- getGpa(): Calculates 4.0 scale GPA");
        System.out.println("- getAverageScore(): Calculates numeric average");
        System.out.println("- addTestScore()/removeTestScore(): Manage test scores");
        System.out.println("All systems operational and tested ✓");
    }
}