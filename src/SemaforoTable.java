
class SemaforoTable {

    private MutableTable mesa;
    private volatile boolean c1;
    private volatile boolean c2;
    private volatile int turn;

    SemaforoTable(MutableTable mesa, boolean c1, boolean c2, int turn) {
        this.mesa = mesa;
        this.c1 = c1;
        this.c2 = c2;
        this.turn = turn;
        this.acquire();

k    }

    void tentarComer() {
        if (!mesa.temComida()) {
            System.out.println("Cozinheiro liberado");
            this.release();
            while (!mesa.temComida()) {}
            this.acquire();
            System.out.println("Cozinheiro bloqueado");
        }

        mesa.comerPessoas();

    }

    void acquire() {
        this.c1 = true;
        this.turn = 2;
        while (this.c2 && this.turn == 2) {}
    }

    void release() {
        this.c1 = false;
    }
}
