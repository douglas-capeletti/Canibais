import java.util.concurrent.Semaphore;

public class Canibal implements Runnable {

    private Semaphore semaforo_canibais;
    private SemaforoTable mesa;
    private String nome;

    Canibal(SemaforoTable mesa, Semaphore semaforo_canibais, String nome) {
        this.mesa = mesa;
        this.semaforo_canibais = semaforo_canibais;
        this.nome = nome;
    }

    @Override
    public void run() {
        try{
            semaforo_canibais.acquire();
            System.out.println(this.nome + " tentando comer.");

            mesa.tentarComer();
            System.out.println(this.nome + " comeu.");
            System.out.println("\n");

            semaforo_canibais.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
