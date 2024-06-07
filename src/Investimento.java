class Investimento {
    private int id;
    private String nome;
    private double valor;
    private String data;

    public Investimento(int id, String nome, double valor, String data) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Investimento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", data='" + data + '\'' +
                '}';
    }
}