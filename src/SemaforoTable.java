import java.util.concurrent.Semaphore;

class SemaforoTable{

    private MutableTable mesa;
    private Semaphore semaforo_mesa;

    SemaforoTable(MutableTable mesa, Semaphore semaforo_mesa) {
        this.mesa = mesa;
        this.semaforo_mesa = semaforo_mesa;

        try{
            this.semaforo_mesa.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    void tentarComer(){
        try{

            if (!mesa.temComida()){
                System.out.println("Cozinheiro liberado");
                semaforo_mesa.release();
                while(!mesa.temComida()) {}
                semaforo_mesa.acquire();
                System.out.println("Cozinheiro bloqueado");
            }

            mesa.comerPessoas();



        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
