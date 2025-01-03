import java.util.ArrayList;
import java.util.List;

public class Conta {

    private static int accountCounter = 1;

    private int numeroConta;
    private Pessoa pessoa;
    private Double saldo = 0.0;
    private String agencia;
    private String senha;
    private List<Transacao> transacoes = new ArrayList<>();


    public Conta(Pessoa pessoa, String agencia, String senha) {
        this.numeroConta = Conta.accountCounter;
        this.pessoa = pessoa;
        this.agencia = agencia;
        this.senha = senha;
        this.updateSaldo();
        Conta.accountCounter += 1;
    }


    public int getNumeroConta() {
        return numeroConta;
    }
    public Pessoa getClient() {
        return pessoa;
    }
    public void setClient(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public void setAgencia(String agencia){
        this.agencia = agencia;
    }
    public String getAgencia(){
        return this.agencia;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha(){
        return this.senha;
    }
    private void updateSaldo() {
        this.saldo = this.getSaldo();
    }

    public String toString() {

        return "\nConta: " + this.getNumeroConta() +
                "\nCliente: " + this.pessoa.getName() +
                "\nCPF: " + this.pessoa.getCpf() +
                "\nEmail: " + this.pessoa.getEmail() +
                "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
                "\n" ;
    }

    public void depositar(Double valor) {
        if(valor > 0) {
            setSaldo(getSaldo() + valor);
            System.out.println("Seu depósito foi realizado com sucesso!");

            transacoes.add(new Transacao("Depósito", valor));
        }else {
            System.out.println("Não foi possível realizar o depósito!");
        }
    }

    public void sacar(Double valor) {
        if(valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque realizado com sucesso!");

            transacoes.add(new Transacao("Saque", valor));
        }else {
            System.out.println("Não foi possível realizar o saque!");
        }
    }

    public void transferencia(Conta contaParaDeposito, Double valor) {
        if(valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            contaParaDeposito.transferenciaReceber(valor);
            System.out.println("Transferência realizada com sucesso!");

            transacoes.add(new Transacao("Transferência", valor));
        }else {
            System.out.println("Não foi possível realizar a tranferência");
        }

    }

    public void transferenciaReceber(Double valor){
        this.saldo = this.saldo+valor;
        transacoes.add(new Transacao("Transferência recebida", valor));
    }

    public void imprimirExtrato() {
        System.out.println("Extrato da conta " + numeroConta);
        System.out.print("\n");
        System.out.println("Nome " + this.pessoa.getName());
        for (Transacao transacao : transacoes) {
            System.out.println(transacao);
        }
    }

}