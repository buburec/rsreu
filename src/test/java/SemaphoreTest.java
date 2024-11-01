import org.junit.jupiter.api.Test;
import ru.rsreu.parallel.concurrent.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SemaphoreTest {
    @Test
    public void testSemaphoreLimitsAccess() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        AtomicInteger accessCount = new AtomicInteger(0);
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable task = () -> {
            try {
                semaphore.acquire();
                accessCount.incrementAndGet();
                Thread.sleep(500); // Симуляция работы с ресурсом
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        };

        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
        }

        Thread.sleep(100); // Ждём начала выполнения задач
        assertTrue(accessCount.get() <= 2, "Количество доступов не должно превышать лимит семафора");
    }
}
