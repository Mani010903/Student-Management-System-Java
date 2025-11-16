package com.studentapp.main;

import java.util.List;
import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import com.studentapp.service.StudentServiceImpl;
import com.studentapp.util.InputUtil;

import static com.studentapp.util.ConsoleColors.*;

public class MainApp {

    // Display a list of students in table format
    private static void displayTable(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println(YELLOW + "No students found." + RESET);
            return;
        }

        int wId = 4, wName = 22, wAge = 5, wDept = 22, wMarks = 7;
        String fmt = "| %-" + (wId - 1) + "s | %-" + (wName - 1) + "s | %-" +
                     (wAge - 1) + "s | %-" + (wDept - 1) + "s | %-" +
                     (wMarks - 1) + "s |%n";

        String line = "+" + "-".repeat(wId + 1)
                    + "+" + "-".repeat(wName + 2)
                    + "+" + "-".repeat(wAge + 2)
                    + "+" + "-".repeat(wDept + 2)
                    + "+" + "-".repeat(wMarks + 2) + "+";

        System.out.println(BLUE + line + RESET);
        System.out.printf(fmt, "ID", "Name", "Age", "Dept", "Marks");
        System.out.println(BLUE + line + RESET);

        for (Student st : list) {
            String nm = st.getName();
            if (nm.length() > wName - 1) nm = nm.substring(0, wName - 4) + "...";

            String dp = st.getDepartment();
            if (dp.length() > wDept - 1) dp = dp.substring(0, wDept - 4) + "...";

            String marksColor = st.getMarks() >= 80 ? GREEN : st.getMarks() >= 50 ? YELLOW : RED;
            System.out.printf(fmt, st.getId(), nm, st.getAge(), dp, marksColor + st.getMarks() + RESET);
        }
        System.out.println(BLUE + line + RESET);
    }

    // Display table with pagination
 // Display table with pagination and optional search by ID
    private static void displayTableWithPagination(List<Student> list, int pageSize, StudentService service) {
        if (list.isEmpty()) {
            System.out.println(YELLOW + "No students found." + RESET);
            return;
        }

        int totalStudents = list.size();
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

        for (int page = 0; page < totalPages; page++) {
            System.out.println("\nPage " + (page + 1) + " of " + totalPages);

            int start = page * pageSize;
            int end = Math.min(start + pageSize, totalStudents);

            displayTable(list.subList(start, end));

            // If last page, break
            if (page == totalPages - 1) break;

            // Prompt user
            String input = InputUtil.getString("Press Enter to view next page or type ID:<number> to search student: ").trim();

            if (input.toUpperCase().startsWith("ID:")) {
                try {
                    int searchId = Integer.parseInt(input.substring(3).trim());
                    Student stu = service.getStudentById(searchId);
                    if (stu != null) displayTable(List.of(stu));
                    else System.out.println(RED + "✘ Student Not Found" + RESET);
                    // After search, repeat current page
                    page--;
                } catch (NumberFormatException e) {
                    System.out.println(RED + "✘ Invalid ID format!" + RESET);
                    page--; // repeat current page
                }
            }
        }
    }


    public static void main(String[] args) {
        StudentService service = new StudentServiceImpl();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Search Student by Name");
            System.out.println("7. Search Student by Course");
            System.out.println("8. Exit");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1: // Add Student
                    String name = InputUtil.getString("Enter Name: ");
                    int age = InputUtil.getInt("Enter Age: ");
                    String dept = InputUtil.getDepartment("Enter Department: ");

                    int marks = InputUtil.getInt("Enter Marks: ");

                    Student s = new Student(0, name, age, dept, marks);
                    if (service.addStudent(s)) System.out.println(GREEN + "✔ Student Added Successfully" + RESET);
                    else System.out.println(RED + "✘ Failed to Add Student" + RESET);
                    break;

                case 2: // View All Students
                    List<Student> allStudents = service.getAllStudents();

                    System.out.println("\nSort Students By:");
                    System.out.println("1. Name\n2. Marks\n3. Age\n4. ID\n5. Department\n6. No Sorting");
                    int sortChoice = InputUtil.getInt("Enter choice: ");

                    switch (sortChoice) {
                        case 1 -> allStudents.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
                        case 2 -> allStudents.sort((s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks())); // descending
                        case 3 -> allStudents.sort((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()));
                        case 4 -> allStudents.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId())); // ascending
                        case 5 -> allStudents.sort((s1, s2) -> s1.getDepartment().compareToIgnoreCase(s2.getDepartment()));
                        case 6 -> {} // no sorting
                        default -> System.out.println(YELLOW + "⚠ Invalid choice, showing unsorted list." + RESET);
                    }

                    int pageSize = InputUtil.getInt("Enter number of students per page: ");
                    displayTableWithPagination(allStudents, pageSize, service);
                    break;

                case 3: // Search by ID
                    int sid = InputUtil.getInt("Enter Student ID: ");
                    Student stu = service.getStudentById(sid);
                    if (stu != null) displayTable(List.of(stu));
                    else System.out.println(RED + "✘ Student Not Found" + RESET);
                    break;

                case 4: // Update
                    int uid = InputUtil.getInt("Enter ID to Update: ");
                    String nname = InputUtil.getString("Enter New Name: ");
                    int nage = InputUtil.getInt("Enter New Age: ");
                    String ndept = InputUtil.getDepartment("Enter New Department: ");

                    int nmarks = InputUtil.getInt("Enter New Marks: ");

                    Student upd = new Student(uid, nname, nage, ndept, nmarks);
                    if (service.updateStudent(upd)) System.out.println(GREEN + "✔ Student Updated Successfully" + RESET);
                    else System.out.println(RED + "✘ Update Failed" + RESET);
                    break;

                case 5: // Delete
                    int did = InputUtil.getInt("Enter ID to Delete: ");
                    if (service.deleteStudent(did)) System.out.println(GREEN + "✔ Student Deleted" + RESET);
                    else System.out.println(RED + "✘ Delete Failed" + RESET);
                    break;

                case 6: // Search by Name
                    String nameQuery = InputUtil.getString("Enter Name to search: ");
                    displayTable(service.searchStudentsByName(nameQuery));
                    break;

                case 7: // Search by Course
                	String deptQuery = InputUtil.getDepartment("Enter Department to search: ");

                    displayTable(service.searchStudentscourse(deptQuery));
                    break;

                case 8: // Exit
                    System.out.println(BLUE + "Exiting... Goodbye!" + RESET);
                    System.exit(0);

                default:
                    System.out.println(YELLOW + "⚠ Invalid choice!" + RESET);
            }
        }
    }
}
