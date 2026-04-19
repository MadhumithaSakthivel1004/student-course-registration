package StudentCourseRegistration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistrationManager manager = new RegistrationManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Course Registration Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. Drop Course");
            System.out.println("5. View Details");
            System.out.println("6. Simulate Multithreaded Registration");
            System.out.println("7. Exit");
     
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String sid = sc.nextLine();
                        System.out.print("Enter Student Name: ");
                        String sname = sc.nextLine();
                        manager.addStudent(new Student(sid, sname));
                        break;

                    case 2:
                        System.out.print("Enter Course Code: ");
                        String ccode = sc.nextLine();
                        System.out.print("Enter Course Title: ");
                        String ctitle = sc.nextLine();
                        System.out.print("Enter Capacity: ");
                        int cap = sc.nextInt();
                        manager.addCourse(new Course(ccode, ctitle, cap));
                        break;

                    case 3:
                        System.out.print("Enter Student ID: ");
                        sid = sc.nextLine();
                        System.out.print("Enter Course Code: ");
                        ccode = sc.nextLine();
                        manager.enrollStudent(sid, ccode);
                        break;

                    case 4:
                        System.out.print("Enter Student ID: ");
                        sid = sc.nextLine();
                        System.out.print("Enter Course Code: ");
                        ccode = sc.nextLine();
                        manager.dropCourse(sid, ccode);
                        break;

                    case 5:
                        manager.viewDetails();
                        break;

                    case 6:
                        manager.simulateMultithreadedRegistration();
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}