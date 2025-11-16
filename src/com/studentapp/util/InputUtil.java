package com.studentapp.util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner sc = new Scanner(System.in);

    // Safe integer input
    public static int getInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("✘ Invalid input! Please enter a valid number.");
            }
        }
        return value;
    }

    // Safe string input
    public static String getString(String prompt) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = sc.nextLine().trim();
            if (!value.isEmpty()) break;
            System.out.println("✘ Input cannot be empty!");
        }
        return value;
    }
    // Department input with validation: only letters and spaces, length 2..30
    public static String getDepartment(String prompt) {
        while (true) {
            System.out.print(prompt);
            String dept = sc.nextLine().trim();
            if (dept.matches("[a-zA-Z ]{2,30}")) {
                // normalize spaces: collapse multiple spaces to single and trim
                dept = dept.replaceAll("\\s+", " ").trim();
                return dept;
            }
            System.out.println("Invalid Department! Use only letters and spaces (2-30 chars).");
        }
    }}
