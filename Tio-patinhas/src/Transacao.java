public class Transacao {
    private String tipo;
    private Double valor;

    public Transacao(String tipo, Double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return tipo + ": " + Utils.doubleToString(valor);
    }
}
