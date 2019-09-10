import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        int vitimas = Integer.parseInt(args[0]);

        MutableTable table = new MutableTable(vitimas);
        Semaphore podeComer = new Semaphore(1);
        Semaphore pedirMais = new Semaphore(0);
        Semaphore temComida = new Semaphore(1);


        Thread canibal_one = new Thread(new Canibal(table, podeComer, pedirMais, temComida));
        canibal_one.start();

        Thread canibal_two = new Thread(new Canibal(table, podeComer, pedirMais, temComida));
        canibal_two.start();

        Thread canibal_three = new Thread(new Canibal(table, podeComer, pedirMais, temComida));
        canibal_three.start();


    }
}
