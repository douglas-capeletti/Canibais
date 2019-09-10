

public class MutableTable {

    private int pessoas = 0;

    public MutableTable(int pessoas) {
        this.pessoas = pessoas;
    }

    public void comerPessoas() {
        pessoas = pessoas -1;
    }

    public int qtdPessoas() {
        return pessoas;
    }

    public void servirPessoas(int p){
        pessoas += p;
    }

    public boolean temComida(){
        return pessoas > 0;
    }
}
