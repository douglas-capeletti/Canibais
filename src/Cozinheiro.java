import java.util.concurrent.Semaphore;

public class Cozinheiro implements Runnable {

    private MutableTable table;
    private Semaphore mutex;

    @Override
    public void run() {

        try{
            mutex.acquire();

            table.servirPessoas(10);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
