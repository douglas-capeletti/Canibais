import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    static volatile boolean[] flag = new boolean[2];
    static volatile int turn;
    static volatile int i=0;
    static volatile int j=1;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2){
            System.out.println("Numero incorreto de parametros");
            return;
        }
        int vitimas = Integer.parseInt(args[0]);
        int quant_canibais = Integer.parseInt(args[1]);

        MutableTable mesa = new MutableTable(vitimas);
        SemaforoTable pre_mesa = new SemaforoTable(mesa, flag, turn, i, j);

        Cozinheiro cozinheiro = new Cozinheiro(mesa, flag, turn, i, j);
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
        cozinheiro.setTurn();
        cozinheiro_thread.join();

    }
}
