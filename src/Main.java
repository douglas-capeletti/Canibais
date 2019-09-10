import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        int vitimas = Integer.parseInt(args[0]);

        MutableTable mesa = new MutableTable(vitimas);
        Semaphore semaforo_mesa = new Semaphore(1);
        SemaforoTable pre_mesa = new SemaforoTable(mesa, semaforo_mesa);

        Cozinheiro cozinheiro = new Cozinheiro(mesa, semaforo_mesa);

        Thread cozinheiro_thread = new Thread(cozinheiro);

        Semaphore semaforo_canibais = new Semaphore(1);

        Thread canibal_1 = new Thread(new Canibal(pre_mesa, semaforo_canibais));
        Thread canibal_2 = new Thread(new Canibal(pre_mesa, semaforo_canibais));
        Thread canibal_3 = new Thread(new Canibal(pre_mesa, semaforo_canibais));

        cozinheiro.setKeep_trying();

    }
}
