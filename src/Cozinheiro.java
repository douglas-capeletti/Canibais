import java.util.concurrent.Semaphore;

public class Cozinheiro implements Runnable {

    private MutableTable mesa;
    private Semaphore semaforo_mesa;
    private volatile boolean keep_trying;

    Cozinheiro(MutableTable mesa, Semaphore semaforo_mesa) {
        this.mesa = mesa;
        this.semaforo_mesa = semaforo_mesa;
        this.keep_trying = true;
    }

    void setKeep_trying() {

        synchronized (this) {
            this.keep_trying = false;
        }
    }

    @Override
    public void run() {
        try {
            while(this.keep_trying) {
                semaforo_mesa.acquire();
                System.out.println("Pegou a thread");
                mesa.servirPessoas();
                System.out.println("Serviu");
                semaforo_mesa.release();
                System.out.println("Liberou a thread");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}














