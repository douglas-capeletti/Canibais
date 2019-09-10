import java.util.concurrent.Semaphore;

public class Canibal implements Runnable {

    private Semaphore semaforo_canibais;
    private SemaforoTable mesa;

    Canibal(SemaforoTable mesa, Semaphore semaforo_canibais) {
        this.mesa = mesa;
        this.semaforo_canibais = semaforo_canibais;
    }

    @Override
    public void run() {
        try{
            semaforo_canibais.acquire();

            mesa.tentarComer();

            semaforo_canibais.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
