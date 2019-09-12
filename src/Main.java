import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int vitimas = Integer.parseInt(args[0]);

        MutableTable mesa = new MutableTable(vitimas);
        Semaphore semaforo_mesa = new Semaphore(1);
        SemaforoTable pre_mesa = new SemaforoTable(mesa, semaforo_mesa);

        Cozinheiro cozinheiro = new Cozinheiro(mesa, semaforo_mesa);

        Thread cozinheiro_thread = new Thread(cozinheiro);
        cozinheiro_thread.start();

        Semaphore semaforo_canibais = new Semaphore(2);

        Thread canibal_1 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal1"));
        canibal_1.start();

        Thread canibal_2 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal2"));
        canibal_2.start();

        Thread canibal_3 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal3"));
        canibal_3.start();

        Thread canibal_4 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal4"));
        canibal_4.start();

        Thread canibal_5 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal5"));
        canibal_5.start();

        Thread canibal_6 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal6"));
        canibal_6.start();

        canibal_1.join();
        canibal_2.join();
        canibal_3.join();
        canibal_4.join();
        canibal_5.join();
        canibal_6.join();
        cozinheiro.setKeep_trying();
        cozinheiro_thread.join();
        System.out.println("Cozinheiro morreu");

    }
}
