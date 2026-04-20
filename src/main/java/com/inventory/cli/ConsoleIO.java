package com.inventory.cli;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleIO {
    private final Scanner in;
    private final PrintStream out;

    public ConsoleIO(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void print(String line) {
        out.println(line);
    }

    public void printf(String fmt, Object... args) {
        out.printf(fmt, args);
    }

    public String readLine(String prompt) {
        out.print(prompt);
        return in.nextLine().trim();
    }

    public String readNonEmpty(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (!value.isEmpty()) {
                return value;
            }
            out.println("Value must not be empty.");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            String raw = readLine(prompt);
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                out.println("Please enter a valid integer.");
            }
        }
    }

    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            out.printf("Please enter a number between %d and %d.%n", min, max);
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            String raw = readLine(prompt);
            try {
                return Double.parseDouble(raw);
            } catch (NumberFormatException e) {
                out.println("Please enter a valid number.");
            }
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            String raw = readLine(prompt);
            try {
                return LocalDate.parse(raw);
            } catch (DateTimeParseException e) {
                out.println("Please enter a date in ISO format (YYYY-MM-DD).");
            }
        }
    }
}
