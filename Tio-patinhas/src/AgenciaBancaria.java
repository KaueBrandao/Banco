import java.util.ArrayList;
import java.util.Scanner;

public class AgenciaBancaria {

    static Scanner s = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        int cont = 1;
        String Senha = "admin";

        while (true) {
            String senha;
            System.out.println("");
            System.out.print("Senha: ");
            senha = s.nextLine();
            if (senha.equals(Senha)) {
                
            
            do{
                System.out.println("\n1 - Criar Conta");
                System.out.println("2 - Depositar");
                System.out.println("3 - Sacar");
                System.out.println("4 - Trasnferir");
                System.out.println("5 - Listar");
                System.out.println("6 - Imprimir extrato");
                System.out.println("7 - Sair");
    
                System.out.print("Opcao: ");
                cont = s.nextInt();;
    
                switch (cont) {
                    case 1:
                        criarConta();
                        break;
    
                    case 2:
                        depositar();
                        break;
    
                    case 3:
                        sacar();
                        break;
    
                    case 4:
                        transferir();
                        break;
    
                    case 5:
                        listarContas();
                        break;
                    case 6:
                        Imprimiextrato();
                        break;
    
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }while(cont!= 7);
        }else{
            continue;
        }

        }

        
    }

    public static void criarConta() {
        String nome, cpf,email, agencia, senha ;

        System.out.print("\nNome: ");
        nome = s.nextLine();
        nome = s.nextLine();

        System.out.print("\nCPF: ");
        cpf = s.nextLine();

        System.out.print("\nEmail: ");
        email = s.nextLine();

        System.out.print("\nNumero da Agencia: ");
        agencia = s.nextLine();

        System.out.print("\nSenha: ");
        senha = s.nextLine();

        Pessoa cliente = new Pessoa(nome, cpf, email);

        Conta conta = new Conta(cliente, agencia, senha);

        contasBancarias.add(conta);
        System.out.println("CONTA CRIADA COM SUCESSO!");
    }

    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if(contasBancarias.size() > 0) {
            for(Conta contaa : contasBancarias) {
                if(contaa.getNumeroConta() == numeroConta) {
                    conta = contaa;
                }
            }
        }
        return conta;
    }

    public static Boolean autenticar(int numeroConta){
        Conta conta = encontrarConta(numeroConta);
        String agencia, senha;

        System.out.print("Numero da Agencia: ");
        agencia = s.nextLine();agencia = s.nextLine();
        System.out.print("Senha: ");
        senha = s.nextLine();
        
        if(senha.equals(conta.getSenha()) && agencia.equals(conta.getAgencia())){
            return true;
        }
        else{
            System.out.print("DADOS INCORRETOS!");
            return false;
        }
    }

    public static void depositar() {
        System.out.print("Número da conta: ");
        int numeroConta = s.nextInt();
        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            if(autenticar(numeroConta)){
                System.out.print("valor do deposito: ");
                Double valorDeposito = s.nextDouble();
                conta.depositar(valorDeposito);
            }
            

            
        }else {
            System.out.println("CONTA INEXISTENTE!");
        }
    }

    public static void sacar() {
        System.out.print("Número da conta: ");
        int numeroConta = s.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            if(autenticar(numeroConta)){
                System.out.print("Valor do Saque: ");
                Double valorSaque = s.nextDouble();
                conta.sacar(valorSaque);
                System.out.println("SAQUE REALIZADO COM SUCESSO!");
            }
    
            
        }else {
            System.out.println("CONTA INEXISTENTE!");
        }
    }

    public static void transferir() {
        System.out.print("Número da conta de transferência: ");
        int numeroContaRemetente = s.nextInt();

        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if(contaRemetente != null) {
            if (autenticar(numeroContaRemetente)) {
                System.out.print("Número conta do Destinatário: ");
                int numeroContaDestinatario = s.nextInt();

                Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

                 if(contaDestinatario != null) {
                System.out.print("Valor da transferência: ");
                    Double valor = s.nextDouble();

                    contaRemetente.transferencia(contaDestinatario, valor);

            }else {
                System.out.println("CONTA DO DEPOSITO INEXISTENTE");
            }
            }
            

        }else {
            System.out.println("CONTA DA TRANSFERENCIA INEXISTENTE");
        }
    }

    public static void listarContas() {
        if(contasBancarias.size() > 0) {
            for(Conta conta: contasBancarias) {
                System.out.println(conta);
            }
        }else {
            System.out.println("SEM CONTAS CADASTRADAS");
        }
    }

    public static void Imprimiextrato(){
        System.out.print("Número da conta: ");
        int numeroConta = s.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            if(autenticar(numeroConta)){
               conta.imprimirExtrato(); 
            }
        } else {
            System.out.println("CONTA INEXISTENTE");
        }
    }
}


