import java.util.concurrent.Semaphore;

public class Cozinheiro implements Runnable {

    private MutableTable mesa;
    private Semaphore semaforo_mesa;
    private boolean keep_trying;

    Cozinheiro(MutableTable mesa, Semaphore semaforo_mesa) {
        this.mesa = mesa;
        this.semaforo_mesa = semaforo_mesa;
        this.keep_trying = true;
    }

    void setKeep_trying() {
        this.keep_trying = false;
    }

    @Override
    public void run() {
        try {
            while(this.keep_trying) {
                semaforo_mesa.acquire();
                mesa.servirPessoas();
                semaforo_mesa.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
