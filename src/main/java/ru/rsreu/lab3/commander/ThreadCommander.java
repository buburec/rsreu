package ru.rsreu.lab3.commander;

import ru.rsreu.lab3.service.ThreadService;

/**
 * Enum representing commands for managing threads.
 * Each command can be executed with a parameter and a ThreadService instance.
 */
public enum ThreadCommander {

    /**
     * Command to start a new thread for integral calculation.
     */
    START {
        @Override
        public boolean execute(String parameter, ThreadService threadService) {
            try {
                double epsilon = Double.parseDouble(parameter);
//                System.out.println("Создан поток с id = " + threadService.startThread(epsilon));
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Некорректное значение числового параметра");
            }
            return true;
        }
    },

    /**
     * Command to stop a running thread.
     */
    STOP {
        @Override
        public boolean execute(String parameter, ThreadService threadService) {
            try {
                int id = Integer.parseInt(parameter);
                threadService.stopThread(id);
            } catch (NumberFormatException e) {
                System.out.println("Некорректное значение числового параметра");
            }
            return true;
        }
    },

    /**
     * Command to wait for a thread to finish.
     */
    AWAIT {
        @Override
        public boolean execute(String parameter, ThreadService threadService) {
            boolean isRunning = true;
            try {
                int id = Integer.parseInt(parameter);
                isRunning = threadService.awaitThread(id);
            } catch (NumberFormatException e) {
                System.out.println("Некорректное значение числового параметра");
            }
            return isRunning;
        }
    },

    /**
     * Command to exit the application.
     */
    EXIT {
        @Override
        public boolean execute(String parameter, ThreadService threadService) {
            System.out.println("Завершение работы программы");
            return false;
        }
    };

    /**
     * Abstract method for executing the command with the given parameter and ThreadService instance.
     *
     * @param parameter The parameter for the command.
     * @param threadService The ThreadService instance to use.
     * @return True if the command executed successfully, false if the application should exit.
     */
    public abstract boolean execute(String parameter, ThreadService threadService);
}
