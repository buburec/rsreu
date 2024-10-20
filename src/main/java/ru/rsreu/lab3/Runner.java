package ru.rsreu.lab3;



import ru.rsreu.lab3.entity.ResultWrapper;
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
        System.out.println("start[s] <epsilon> <threadPoolSize> <asyncSize>");
        Scanner scanner = new Scanner(System.in);

        String[] parameters = scanner.nextLine().split(" ");
        double epsilon = Double.parseDouble(parameters[1]);
        int threadPoolSize = Integer.parseInt(parameters[2]);
        int asyncSize = Integer.parseInt(parameters[3]);
        ApplicationContext.initializeContext(threadPoolSize, asyncSize);

        ThreadService threadService = ThreadService.getInstance();
        ResultWrapper resultWrapper = threadService.startThreadPool(epsilon);
        System.out.println(resultWrapper);
        threadService.stopThreadPool();
    }
}