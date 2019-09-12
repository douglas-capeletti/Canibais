import java.util.concurrent.Semaphore;

public class Main {
    volatile static boolean c1 = false;
    volatile static boolean c2 = false;
    volatile static int turn = 1;

    public static void main(String[] args) throws InterruptedException {

        int vitimas = Integer.parseInt(args[0]);

        MutableTable mesa = new MutableTable(vitimas);
        SemaforoTable pre_mesa = new SemaforoTable(mesa, c1, c2, turn);

        Semaphore semaforo_canibais = new Semaphore(1);

        Thread canibal_1 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal1"));
        canibal_1.start();

        Thread canibal_2 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal2"));
        canibal_2.start();

        Thread canibal_3 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal3"));
        canibal_3.start();

//        Thread canibal_4 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal4"));
//        canibal_4.start();
//
//        Thread canibal_5 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal5"));
//        canibal_5.start();
//
//        Thread canibal_6 = new Thread(new Canibal(pre_mesa, semaforo_canibais, "canibal6"));
//        canibal_6.start();

        Cozinheiro cozinheiro = new Cozinheiro(mesa, c1, c2, turn);

        Thread cozinheiro_thread = new Thread(cozinheiro);
        cozinheiro_thread.start();


        canibal_1.join();
        canibal_2.join();
        canibal_3.join();
//        canibal_4.join();
//        canibal_5.join();
//        canibal_6.join();
        cozinheiro.setKeep_trying();
        cozinheiro_thread.join();

    }
}
