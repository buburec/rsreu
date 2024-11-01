import org.junit.jupiter.api.Test;
import ru.rsreu.parallel.concurrent.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockTest {
    @Test
    public void testLockWorksAsExpected() throws InterruptedException, ExecutionException {
        Lock lock = new Lock();
        AtomicInteger sharedResource = new AtomicInteger(0);
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable task = () -> {
            try {
                lock.lock();
                int currentValue = sharedResource.get();
                Thread.sleep(100); // Симулируем задержку
                sharedResource.set(currentValue + 1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        };

        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            futures.add(executorService.submit(task));
        }

        for (Future<?> future : futures) {
            future.get(); // Ожидаем завершения всех задач
        }

        assertEquals(5, sharedResource.get(), "Ресурс должен быть обновлён пять раз без гонок");
    }
}
