import java.util.ArrayList;
import java.util.Collections;

public class StatisticsGenerator {
    
    public static void displayStudentStatistics(StudentA student) {
        if (student == null) {
            System.out.println("No student selected for statistics.");
            return;
        }
        
        System.out.println("\n=== Individual Student Statistics ===");
        System.out.println("Student: " + student.getName() + " " + student.getLastname());
        System.out.println("Total Tests: " + student.getNumberOfTests());
        
        if (student.getNumberOfTests() == 0) {
            System.out.println("No test scores available for analysis.");
            return;
        }
        
        double[] scores = student.getTestScores();
        int numTests = student.getNumberOfTests();
        
        System.out.println("Average Score: " + String.format("%.2f", student.getAverageScore()));
        System.out.println("GPA: " + String.format("%.2f", student.getGpa()));
        
        double highest = getHighestScore(scores, numTests);
        double lowest = getLowestScore(scores, numTests);
        double median = getMedianScore(scores, numTests);
        double standardDeviation = getStandardDeviation(scores, numTests, student.getAverageScore());
        
        System.out.println("Highest Score: " + String.format("%.2f", highest));
        System.out.println("Lowest Score: " + String.format("%.2f", lowest));
        System.out.println("Median Score: " + String.format("%.2f", median));
        System.out.println("Standard Deviation: " + String.format("%.2f", standardDeviation));
        
        displayGradeDistribution(scores, numTests);
        
        displayPerformanceTrend(scores, numTests);
    }
    
    public static void displaySampleStatistics() {
        System.out.println("\n=== Sample Class Statistics ===");
        
        StudentA[] sampleClass = createSampleClass();
        
        System.out.println("Class Size: " + sampleClass.length + " students");
        
        double classAverage = calculateClassAverage(sampleClass);
        double classGPA = calculateClassGPA(sampleClass);
        double highestClassScore = getHighestClassScore(sampleClass);
        double lowestClassScore = getLowestClassScore(sampleClass);
        
        System.out.println("Class Average: " + String.format("%.2f", classAverage));
        System.out.println("Class GPA: " + String.format("%.2f", classGPA));
        System.out.println("Highest Score in Class: " + String.format("%.2f", highestClassScore));
        System.out.println("Lowest Score in Class: " + String.format("%.2f", lowestClassScore));
        
        displayClassGradeDistribution(sampleClass);
        
        displayTopPerformers(sampleClass);
    }
    
    private static double getHighestScore(double[] scores, int numTests) {
        double highest = scores[0];
        for (int i = 1; i < numTests; i++) {
            if (scores[i] > highest) {
                highest = scores[i];
            }
        }
        return highest;
    }
    
    private static double getLowestScore(double[] scores, int numTests) {
        double lowest = scores[0];
        for (int i = 1; i < numTests; i++) {
            if (scores[i] < lowest) {
                lowest = scores[i];
            }
        }
        return lowest;
    }
    
    private static double getMedianScore(double[] scores, int numTests) {
        ArrayList<Double> sortedScores = new ArrayList<>();
        for (int i = 0; i < numTests; i++) {
            sortedScores.add(scores[i]);
        }
        Collections.sort(sortedScores);
        
        if (numTests % 2 == 0) {
            return (sortedScores.get(numTests/2 - 1) + sortedScores.get(numTests/2)) / 2.0;
        } else {
            return sortedScores.get(numTests/2);
        }
    }
    
    private static double getStandardDeviation(double[] scores, int numTests, double average) {
        double sum = 0;
        for (int i = 0; i < numTests; i++) {
            sum += Math.pow(scores[i] - average, 2);
        }
        return Math.sqrt(sum / numTests);
    }
    
    private static void displayGradeDistribution(double[] scores, int numTests) {
        int[] gradeCount = new int[5];
        
        for (int i = 0; i < numTests; i++) {
            if (scores[i] >= 90) gradeCount[0]++;
            else if (scores[i] >= 80) gradeCount[1]++;
            else if (scores[i] >= 70) gradeCount[2]++;
            else if (scores[i] >= 60) gradeCount[3]++;
            else gradeCount[4]++;
        }
        
        System.out.println("\nGrade Distribution:");
        System.out.println("A grades: " + gradeCount[0] + " (" + String.format("%.1f", (gradeCount[0] * 100.0 / numTests)) + "%)");
        System.out.println("B grades: " + gradeCount[1] + " (" + String.format("%.1f", (gradeCount[1] * 100.0 / numTests)) + "%)");
        System.out.println("C grades: " + gradeCount[2] + " (" + String.format("%.1f", (gradeCount[2] * 100.0 / numTests)) + "%)");
        System.out.println("D grades: " + gradeCount[3] + " (" + String.format("%.1f", (gradeCount[3] * 100.0 / numTests)) + "%)");
        System.out.println("F grades: " + gradeCount[4] + " (" + String.format("%.1f", (gradeCount[4] * 100.0 / numTests)) + "%)");
    }
    
    private static void displayPerformanceTrend(double[] scores, int numTests) {
        if (numTests < 2) {
            System.out.println("\nNot enough data for trend analysis.");
            return;
        }
        
        System.out.println("\nPerformance Trend:");
        for (int i = 0; i < numTests; i++) {
            String trend = "";
            if (i > 0) {
                if (scores[i] > scores[i-1]) {
                    trend = " ↑";
                } else if (scores[i] < scores[i-1]) {
                    trend = " ↓";
                } else {
                    trend = " →";
                }
            }
            System.out.println("Test " + (i+1) + ": " + String.format("%.1f", scores[i]) + trend);
        }
    }
    
    private static StudentA[] createSampleClass() {
        StudentA[] sampleClass = new StudentA[5];
        
        // Student 1
        sampleClass[0] = new StudentA("Alice", "Johnson");
        sampleClass[0].addTestScore(95.0);
        sampleClass[0].addTestScore(87.5);
        sampleClass[0].addTestScore(92.0);
        
        // Student 2
        sampleClass[1] = new StudentA("Bob", "Smith");
        sampleClass[1].addTestScore(78.0);
        sampleClass[1].addTestScore(82.5);
        sampleClass[1].addTestScore(85.0);
        
        // Student 3
        sampleClass[2] = new StudentA("Carol", "Davis");
        sampleClass[2].addTestScore(88.0);
        sampleClass[2].addTestScore(91.0);
        sampleClass[2].addTestScore(89.5);
        
        // Student 4
        sampleClass[3] = new StudentA("David", "Wilson");
        sampleClass[3].addTestScore(72.0);
        sampleClass[3].addTestScore(75.5);
        sampleClass[3].addTestScore(79.0);
        
        // Student 5
        sampleClass[4] = new StudentA("Emma", "Brown");
        sampleClass[4].addTestScore(96.0);
        sampleClass[4].addTestScore(94.5);
        sampleClass[4].addTestScore(98.0);
        
        return sampleClass;
    }
    
    private static double calculateClassAverage(StudentA[] students) {
        double totalAverage = 0;
        for (StudentA student : students) {
            totalAverage += student.getAverageScore();
        }
        return totalAverage / students.length;
    }
    
    private static double calculateClassGPA(StudentA[] students) {
        double totalGPA = 0;
        for (StudentA student : students) {
            totalGPA += student.getGpa();
        }
        return totalGPA / students.length;
    }
    
    private static double getHighestClassScore(StudentA[] students) {
        double highest = 0;
        for (StudentA student : students) {
            double[] scores = student.getTestScores();
            for (int i = 0; i < student.getNumberOfTests(); i++) {
                if (scores[i] > highest) {
                    highest = scores[i];
                }
            }
        }
        return highest;
    }
    
    private static double getLowestClassScore(StudentA[] students) {
        double lowest = 100;
        for (StudentA student : students) {
            double[] scores = student.getTestScores();
            for (int i = 0; i < student.getNumberOfTests(); i++) {
                if (scores[i] < lowest) {
                    lowest = scores[i];
                }
            }
        }
        return lowest;
    }
    
    private static void displayClassGradeDistribution(StudentA[] students) {
        int[] gradeCount = new int[5];
        int totalTests = 0;
        
        for (StudentA student : students) {
            double[] scores = student.getTestScores();
            for (int i = 0; i < student.getNumberOfTests(); i++) {
                if (scores[i] >= 90) gradeCount[0]++;
                else if (scores[i] >= 80) gradeCount[1]++;
                else if (scores[i] >= 70) gradeCount[2]++;
                else if (scores[i] >= 60) gradeCount[3]++;
                else gradeCount[4]++;
                totalTests++;
            }
        }
        
        System.out.println("\nClass Grade Distribution:");
        System.out.println("A grades: " + gradeCount[0] + " (" + String.format("%.1f", (gradeCount[0] * 100.0 / totalTests)) + "%)");
        System.out.println("B grades: " + gradeCount[1] + " (" + String.format("%.1f", (gradeCount[1] * 100.0 / totalTests)) + "%)");
        System.out.println("C grades: " + gradeCount[2] + " (" + String.format("%.1f", (gradeCount[2] * 100.0 / totalTests)) + "%)");
        System.out.println("D grades: " + gradeCount[3] + " (" + String.format("%.1f", (gradeCount[3] * 100.0 / totalTests)) + "%)");
        System.out.println("F grades: " + gradeCount[4] + " (" + String.format("%.1f", (gradeCount[4] * 100.0 / totalTests)) + "%)");
    }
    
    private static void displayTopPerformers(StudentA[] students) {
        System.out.println("\nTop 3 Students by GPA:");
        StudentA[] sortedStudents = new StudentA[students.length];
        System.arraycopy(students, 0, sortedStudents, 0, students.length);
        
        for (int i = 0; i < sortedStudents.length - 1; i++) {
            for (int j = 0; j < sortedStudents.length - 1 - i; j++) {
                if (sortedStudents[j].getGpa() < sortedStudents[j + 1].getGpa()) {
                    StudentA temp = sortedStudents[j];
                    sortedStudents[j] = sortedStudents[j + 1];
                    sortedStudents[j + 1] = temp;
                }
            }
        }
        
        int count = Math.min(3, sortedStudents.length);
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + sortedStudents[i].getName() + " " + 
                             sortedStudents[i].getLastname() + " - GPA: " + 
                             String.format("%.2f", sortedStudents[i].getGpa()));
        }
    }
}