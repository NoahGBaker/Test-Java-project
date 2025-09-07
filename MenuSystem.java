public class MenuSystem {

    private Scanner input;
    private GradeManager gradeManager;
    private ReportGenerator reportGenerator;

    public MenuSystem() {
        this.input = new Scanner(System.in);
        this.gradeManager = new GradeManager();
        this.reportGenerator = new ReportGenerator();
    }

    public static void main(String[] args) {
        MenuSystem menu = new MenuSystem();
        menu.run();
    }

    public void run() {
        System.out.println("Grade Management System");

        while (true) {
            try {
                showMenu();
                int choice = getValidChoice();

                if (choice == 1) {
                    addStudent();
                } else if (choice == 2) {
                    addGrade();
                } else if (choice == 3) {
                    showStudentReport();
                } else if (choice == 4) {
                    showClassReport();
                } else if (choice == 5) {
                    showStatistics();
                } else if (choice == 6) {
                    searchStudents();
                } else if (choice == 7) {
                    generateSampleData();
                } else if (choice == 8) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please select 1-8.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }

        input.close();
    }

    private void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade");
        System.out.println("3. View Student Report");
        System.out.println("4. View Class Report");
        System.out.println("5. Show Statistics");
        System.out.println("6. Search Students");
        System.out.println("7. Generate Sample Data");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");
    }

    // Input validation
    private int getValidChoice() {
        try {
            int choice = input.nextInt();
            input.nextLine(); // clear line
            return choice;
        } catch (Exception e) {
            input.nextLine(); // clear bad input
            System.out.println("Please enter a number 1-8.");
            return -1;
        }
    }

    private String getValidName(String prompt) throws Exception {
        System.out.print(prompt);
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            throw new Exception("Name cannot be empty");
        }
        if (name.length() < 2) {
            throw new Exception("Name must be at least 2 characters");
        }

        return name;
    }

    private double getValidGrade() throws BadGradeException {
        try {
            System.out.print("Enter grade (0-100) or -1 to stop: ");
            double grade = input.nextDouble();
            input.nextLine(); // clear line

            if (grade == -1) return -1; // Special case for stopping

            if (grade < 0 || grade > 100) {
                throw new BadGradeException("Grade must be between 0 and 100. You entered: " + grade);
            }

            return grade;
        } catch (Exception e) {
            input.nextLine(); // clear bad input
            if (e instanceof BadGradeException) {
                throw e;
            }
            throw new BadGradeException("Please enter a valid number between 0-100");
        }
    }

    // Menu operations
    private void addStudent() {
        try {
            String firstName = getValidName("First name: ");
            String lastName = getValidName("Last name: ");

            gradeManager.addStudent(firstName, lastName);
            System.out.println("Student added: " + firstName + " " + lastName);

            System.out.print("Add grades now? (y/n): ");
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase("y")) {
                Student newStudent = gradeManager.getAllStudents().get(gradeManager.getStudentCount() - 1);
                addGradesToStudent(newStudent);
            }

        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private void addGrade() {
        try {
            gradeManager.checkIfStudentsExist();

            Student student = selectStudent();
            if (student != null) {
                addGradesToStudent(student);
            }

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addGradesToStudent(Student student) {
        try {
            System.out.println("Adding grades for: " + student.getFullName());

            while (true) {
                try {
                    double grade = getValidGrade();

                    if (grade == -1) {
                        break;
                    }

                    student.addTestScore(grade);
                    System.out.println("Grade added: " + grade);

                } catch (BadGradeException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Error adding grades: " + e.getMessage());
        }
    }

    private void showStudentReport() {
        try {
            gradeManager.checkIfStudentsExist();

            Student student = selectStudent();
            if (student != null) {
                String report = reportGenerator.generateStudentReport(student);
                System.out.println(report);
            }

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showClassReport() {
        try {
            gradeManager.checkIfStudentsExist();

            String report = reportGenerator.generateClassReport(gradeManager);
            System.out.println(report);

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showStatistics() {
        try {
            gradeManager.checkIfStudentsExist();

            String report = reportGenerator.generateStatisticsReport(gradeManager);
            System.out.println(report);

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchStudents() {
        try {
            gradeManager.checkIfStudentsExist();

            String firstName = getValidName("Enter first name: ");
            String lastName = getValidName("Enter last name: ");

            Student found = gradeManager.findStudent(firstName, lastName);
            if (found != null) {
                System.out.println("Found: " + found.getFullName());
                System.out.println("Tests: " + found.getNumberOfTests());
                if (found.getNumberOfTests() > 0) {
                    System.out.println("Average: " + String.format("%.2f", found.getAverageScore()));
                    System.out.println("GPA: " + String.format("%.2f", found.getGPA()));
                }
            } else {
                System.out.println("Student not found.");
            }

        } catch (NoStudentsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void generateSampleData() {
        try {
            System.out.println("Generating sample data...");
            gradeManager.generateSampleData();

            String report = reportGenerator.generateSampleDataSummary(gradeManager);
            System.out.println(report);

        } catch (Exception e) {
            System.out.println("Error generating sample data: " + e.getMessage());
        }
    }

    private Student selectStudent() {
        try {
            ArrayList<Student> students = gradeManager.getAllStudents();

            System.out.println("\nStudents:");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                System.out.println((i + 1) + ". " + student.getFullName() + 
                                 " (Tests: " + student.getNumberOfTests() + ")");
            }

            System.out.print("Select student (1-" + students.size() + ") or 0 to cancel: ");
            int choice = input.nextInt();
            input.nextLine(); 

            if (choice == 0) {
                return null;
            }

            if (choice >= 1 && choice <= students.size()) {
                return gradeManager.getStudent(choice - 1);
            } else {
                System.out.println("Invalid selection.");
                return null;
            }

        } catch (Exception e) {
            input.nextLine();
            System.out.println("Please enter a valid number.");
            return null;
        }
    }
}