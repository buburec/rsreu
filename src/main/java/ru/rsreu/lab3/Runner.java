package ru.rsreu.lab3;



import ru.rsreu.lab3.service.ThreadService;

import java.util.Scanner;

/**
 * The Runner class is the entry point of the application.
 * It initializes the IntegralCalculator and starts the calculation in a separate thread.
 * @version 1.0
 * @author Kirill Popov
 */
public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ThreadService threadService = ThreadService.getInstance();
        String[] parameters = scanner.nextLine().split(" ");
        threadService.startThread(Double.parseDouble(parameters[1]), Integer.parseInt(parameters[2]));
    }
}