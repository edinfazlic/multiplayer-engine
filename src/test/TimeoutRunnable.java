package test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class TimeoutRunnable implements Runnable {

    private BlockingQueue<String> targetQueue;
    private BlockingQueue<String> starterQueue;
    private BlockingQueue<String> resultQueue;

    public TimeoutRunnable(BlockingQueue<String> targetQueue, BlockingQueue<String> starterQueue, BlockingQueue<String> resultQueue) {
        this.targetQueue = targetQueue;
        this.starterQueue = starterQueue;
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {
        try {
            while (!"END".equals(starterQueue.take())) {
                String result = targetQueue.poll(3500, TimeUnit.MILLISECONDS);
                resultQueue.put(null == result ? "TIMEOUTO" : result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
