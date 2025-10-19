import java.util.Scanner;

 class AttendanceManager {

     static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎯 ATTENDANCE MANAGEMENT SYSTEM");

        // --- Step 1: Input number of students ---
        int n = 0;
        while (true) {
            System.out.print("Enter number of students: ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine(); // consume newline
                if (n > 0) break;
                else System.out.println("❌ Number of students must be greater than 0.");
            } else {
                System.out.println("❌ Please enter a valid number.");
                sc.next(); // discard invalid input
            }
        }

        // --- Step 2: Input student names ---
        String[] names = new String[n];
        int[] presentDays = new int[n];
        int totalDays = 0;

        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Enter name of student " + (i + 1) + ": ");
                String name = sc.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("❌ Name cannot be empty. Try again.");
                } else if (isDuplicate(name, names, i)) {
                    System.out.println("❌ Duplicate name found. Enter a unique name.");
                } else {
                    names[i] = name;
                    break;
                }
            }
        }

        // --- Step 3: Menu loop ---
        while (true) {
            System.out.println("\n📋 MENU");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance Report");
            System.out.println("3. Reset All Attendance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } else {
                System.out.println("❌ Invalid input. Please enter a number.");
                sc.next(); // discard wrong input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nMark attendance for each student:");
                    for (int i = 0; i < n; i++) {
                        char ch;
                        while (true) {
                            System.out.print(names[i] + " (P/A): ");
                            String input = sc.nextLine().trim();
                            if (input.isEmpty()) {
                                System.out.println("❌ Input cannot be empty.");
                                continue;
                            }
                            ch = Character.toUpperCase(input.charAt(0));
                            if (ch == 'P' || ch == 'A') break;
                            else System.out.println("❌ Invalid input. Enter 'P' or 'A'.");
                        }
                        if (ch == 'P') presentDays[i]++;
                    }
                    totalDays++;
                    System.out.println("✅ Attendance recorded for Day " + totalDays);
                    break;

                case 2:
                    if (totalDays == 0) {
                        System.out.println("📂 No attendance data yet.");
                    } else {
                        System.out.println("\n📊 ATTENDANCE REPORT");
                        System.out.printf("%-20s %-10s %-10s %-10s%n", "Name", "Present", "Total", "Percent");
                        for (int i = 0; i < n; i++) {
                            double percentage = ((double) presentDays[i] / totalDays) * 100;
                            System.out.printf("%-20s %-10d %-10d %-9.2f%%%n",
                                    names[i], presentDays[i], totalDays, percentage);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Are you sure you want to reset all attendance? (Y/N): ");
                    char confirm = Character.toUpperCase(sc.nextLine().charAt(0));
                    if (confirm == 'Y') {
                        for (int i = 0; i < n; i++) presentDays[i] = 0;
                        totalDays = 0;
                        System.out.println("✅ All attendance data has been reset.");
                    } else {
                        System.out.println("❌ Reset cancelled.");
                    }
                    break;

                case 4:
                    System.out.println("👋 Exiting system. Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Enter a number from 1–4.");
            }
        }
    }

    // Helper method to check for duplicate names
    public static boolean isDuplicate(String name, String[] arr, int currentIndex) {
        for (int i = 0; i < currentIndex; i++) {
            if (arr[i] != null && arr[i].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
