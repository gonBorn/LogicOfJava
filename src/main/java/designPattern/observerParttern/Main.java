package designPattern.observerParttern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 5).forEach(i -> {
            executorService.execute(new GameRunnable());
        });
        executorService.shutdown();
    }
}
