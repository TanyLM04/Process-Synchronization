import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;

        System.out.print("""
                        Would you like to: 
                            1. Start 4 threads that will all access a single semaphore?
                            2. Start 4 threads that will all be controlled based on a boolean?
                            3. Generate a deadlock situation?
                        """);
        while (choice != 1 && choice != 2 && choice != 3){

            System.out.println("Please type your response as a single number corresponding to your choice.");

            if (scanner.hasNextByte())
            choice = scanner.nextByte();
            else{
                scanner.next(); 
            }
        }
        
        scanner.close();
        
        if (choice == 1)
        semaphoreSolution();
        if (choice == 2)
        ifSolution();
        if (choice == 3)
        deadlockProblem();
    }

    public static void semaphoreSolution() throws InterruptedException{
        Semaphore resource1 = new Semaphore(2);
        SemaphoreThread semaThread1 = new SemaphoreThread(resource1, 1);
        SemaphoreThread semaThread2 = new SemaphoreThread(resource1, 2);
        SemaphoreThread semaThread3 = new SemaphoreThread(resource1, 3);
        SemaphoreThread semaThread4 = new SemaphoreThread(resource1, 4);


        semaThread1.start();
        semaThread2.start();
        semaThread3.start();
        semaThread4.start();
    }

    public static void ifSolution() throws InterruptedException{
        boolean greenLight = true;
        
        IfThread ifThread1 = new IfThread(1, greenLight);
        IfThread ifThread2 = new IfThread(2, greenLight);
        IfThread ifThread3 = new IfThread(3, greenLight);
        IfThread ifThread4 = new IfThread(4, greenLight);


        ifThread1.start();
        ifThread2.start();
        ifThread3.start();
        ifThread4.start();
    }

    public static void deadlockProblem() throws InterruptedException{
        Semaphore resource1 = new Semaphore(1);
        Semaphore resource2 = new Semaphore(1);

        Deadlock semaThread1 = new Deadlock(resource1, resource2, 1);
        Deadlock semaThread2 = new Deadlock(resource2, resource1, 2);

        semaThread1.start();
        semaThread2.start();
    }
}
