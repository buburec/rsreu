import org.junit.jupiter.api.*;
import ru.rsreu.parallel.concurrent.CountDownLatch;
import ru.rsreu.parallel.concurrent.Lock;
import ru.rsreu.parallel.concurrent.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


public class CountDownLatchTest {

    @Test
    public void testCountDownLatch() throws InterruptedException {
        int count = 3;
        CountDownLatch latch = new CountDownLatch(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                latch.countDown();
                System.out.println("Счётчик уменьшен");
            });
        }

        latch.await();
        assertEquals(0, latch.getCount(), "Счётчик должен быть равен нулю после всех уменьшений");
    }

}
