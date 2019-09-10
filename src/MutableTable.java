

class MutableTable {

    private int maxPessoas;
    private int pessoas;

    MutableTable(int pessoas) {
        this.pessoas = pessoas;
        this.maxPessoas = pessoas;
    }

    void comerPessoas() {
        this.pessoas -= 1;
    }

    void servirPessoas(){
        this.pessoas = this.maxPessoas;
    }

    boolean temComida(){
        return this.pessoas > 0;
    }
}
