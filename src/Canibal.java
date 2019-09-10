import java.util.concurrent.Semaphore;

public class Canibal implements Runnable {

    private MutableTable table;
    private Semaphore poderComer;
    private Semaphore pedirMais;

    public Canibal(MutableTable table, Semaphore poderComer, Semaphore pedirMais) {
        this.table = table;
        this.poderComer = poderComer;
        this.pedirMais = pedirMais;
    }

    @Override
    public void run() {
        try{
            poderComer.acquire();

            if(table.temComida()){
                table.comerPessoas();
            } else {
                pedirMais.release();

            }

            poderComer.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
