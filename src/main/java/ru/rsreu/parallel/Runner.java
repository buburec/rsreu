package ru.rsreu.parallel;



import ru.rsreu.parallel.entity.ResultWrapper;
import ru.rsreu.parallel.service.ThreadService;

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

        ResultWrapper resultWrapper = ThreadService.getInstance().startThreadPool(epsilon);
        System.out.println(resultWrapper);
        ThreadService.getInstance().stopThreadPool();
    }
}