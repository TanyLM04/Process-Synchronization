import java.util.concurrent.Semaphore;
public class SemaphoreThread extends Thread{
    
    private Semaphore resource;
    private int number;
    public SemaphoreThread(Semaphore resource, int number) {
        this.resource = resource;
        this.number = number;
    }

    @Override
    public void run() {
        try{
            resource.acquire(); // This is the only one that is doing their job well, even though the threads access the semaphore at the same time only one will run
            System.out.println("Thread #" + number +  " acquired semaphore");

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
            resource.release();
        }
    }
}
