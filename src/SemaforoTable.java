import java.util.concurrent.Semaphore;

class SemaforoTable{

    private MutableTable mesa;
    private volatile boolean flag[];
    private volatile int turn;
    private volatile int i;
    private volatile int j;

    SemaforoTable(MutableTable mesa, boolean[] flag, int turn, int i, int j) {
        this.mesa = mesa;
        this.flag = flag;
        this.turn = turn;
        this.i = i;
        this.j = j;

        acquire();

    }

    void tentarComer(){
        if (!mesa.temComida()){
            System.out.println("Cozinheiro liberado");
            release();
            while(!mesa.temComida()) {}
            acquire();
            System.out.println("Cozinheiro bloqueado");
        }

        mesa.comerPessoas();
    }

    private void acquire(){
        this.flag[this.i] = true;
        this.turn = this.j;
        while(this.flag[this.j] && this.turn == this.j);

    }

    private void release(){
        this.flag[this.i]= false;
    }
}
