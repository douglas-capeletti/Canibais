
public class Cozinheiro implements Runnable {

    private MutableTable mesa;
    private volatile boolean c1;
    private volatile boolean c2;
    private volatile int turn;
    private volatile boolean keep_trying;

    Cozinheiro(MutableTable mesa, boolean c1, boolean c2, int turn) {
        this.mesa = mesa;
        this.c1 = c1;
        this.c2 = c2;
        this.turn = turn;
        this.keep_trying = true;
    }

    void setKeep_trying() {
        this.keep_trying = false;
    }

    @Override
    public void run() {
        while(this.keep_trying) {
            System.out.println("Pegou a thread");
            this.acquire();
            mesa.servirPessoas();
            this.release();
            System.out.println("Serviu");
            System.out.println("Liberou a thread");
        }
    }

    void acquire(){
        this.c2 = true;
        this.turn = 1;
        while (this.c1 && this.turn == 1) {};
    }

    void release(){
        this.c2 = false;
    }
}
