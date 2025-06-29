import java.util.concurrent.Semaphore;

public class Deadlock extends Thread{
    private Semaphore resourceA;
    private Semaphore resourceB;
    private int number;

    public Deadlock(Semaphore resourceA, Semaphore resourceB, int number) {
        this.resourceA = resourceA;
        this.resourceB = resourceB;
        this.number = number;
    }

    @Override
    public void run() {
        try{
            resourceA.acquire();
            System.out.println("Thread" + number +  " acquired semaphore"); 

            // Both threads should have acquired each other's next semaphore, generating a deadlock

            resourceB.acquire(); // This semaphore will never actually be acquired
            System.out.println("Thread" + number +  " acquired semaphore");

            // Simulate a process
            byte randNum = (byte)(Math.random() * 10 + 1);
            System.out.println("Counting to: " + randNum);

            for(byte i=1; i <= randNum; i++){
                System.out.println("Thread #" + number + ": " + i);
                sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread" + number + " releasing semaphore");
            resourceA.release();
            System.out.println("Thread" + number + " releasing semaphore");
            resourceB.release();
        }
    }
}
