public class IfThread extends Thread{
    
    private int number;
    private boolean greenLight;

    public IfThread(int number, boolean greenLight) {
        this.number = number;
        this.greenLight = greenLight;
    }
    
    @Override
    public void run() {
        try{
            if(greenLight) { // This is basically decoration, all threads check the condition at the same time and so they all run the code anyway
            greenLight = false;
            
            // Simulate a process
            byte randNum = (byte)(Math.random() * 10 + 1);
            System.out.println("Thread #" + number +  " set red light, counting to " + randNum);
            
            for(byte i=0; i <= randNum; i++) {
                System.out.println("Thread #" + number + ": " + i);
                sleep(1000);
            }   
            
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread #" + number + " setting green light");
            greenLight = true;
        }
    }
}