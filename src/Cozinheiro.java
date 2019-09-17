import java.util.concurrent.Semaphore;

public class Cozinheiro implements Runnable {

    private MutableTable mesa;
    private volatile boolean keep_trying;
    private volatile boolean flag[];
    private volatile int turn;
    private volatile int i;
    private volatile int j;

    Cozinheiro(MutableTable mesa, boolean[] flag, int turn, int i, int j) {
        this.mesa = mesa;
        this.keep_trying = true;
        this.flag = flag;
        this.turn = turn;
        this.i = i;
        this.j = j;

    }

    void setKeep_trying() {

        synchronized (this) {
            this.keep_trying = false;
        }
    }

    void setTurn(){
        this.turn = j;
    }

    @Override
    public void run() {
        while(this.keep_trying) {
            acquire();
            System.out.println("Pegou a thread");
            mesa.servirPessoas();
            System.out.println("Serviu");
            release();
            System.out.println("Liberou a thread");
        }
    }

    private void acquire(){
        this.flag[this.j] = true;
        this.turn = this.i;
        while(this.flag[this.i] && this.turn == this.i);
    }

    private void release(){
        this.flag[this.j]= false;
    }
}














