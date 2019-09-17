import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 2){
            System.out.println("Numero incorreto de parametros");
            return;
        }
        int vitimas = Integer.parseInt(args[0]);
        int quant_canibais = Integer.parseInt(args[1]);

        MutableTable mesa = new MutableTable(vitimas);
        Semaphore semaforo_mesa = new Semaphore(1);
        SemaforoTable pre_mesa = new SemaforoTable(mesa, semaforo_mesa);

        Cozinheiro cozinheiro = new Cozinheiro(mesa, semaforo_mesa);
        Thread cozinheiro_thread = new Thread(cozinheiro);
        cozinheiro_thread.start();

        Semaphore semaforo_canibais = new Semaphore(1);
        ArrayList<Thread> canibais = new ArrayList<Thread>();
        for (int i = 0; i < quant_canibais; i++){
            Thread canibal = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal_" + i));
            canibais.add(canibal);
        }

        for(Thread canibal: canibais){
            canibal.start();
        }

        for(Thread canibal: canibais){
            canibal.join();
        }
        
        cozinheiro.setKeep_trying();
        semaforo_mesa.release();
        cozinheiro_thread.join();

    }
}
