import java.util.Scanner;
import java.util.List; 

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        PessoaDAO dao = new PessoaDAO(); 
        
        dao.criarTabelas();

        int opcao = 0;

        do {
            System.out.println("\n--- MENU (BANCO DE DADOS SQLITE) ---");
            System.out.println("1 - Cadastrar sócio");
            System.out.println("2 - Adicionar convidados");
            System.out.println("3 - Listar sócios");
            System.out.println("4 - Listar convidados");
            System.out.println("5 - Remover sócio");
            System.out.println("6 - Adicionar funcionário");
            System.out.println("7 - Listar funcionários");
            System.out.println("8 - Remover funcionário");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção desejada: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1: // CADASTRAR SÓCIO
                    System.out.println("\n--- Cadastro de Sócio ---");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    
                    int numeroSocio = 0;
                    try {
                        System.out.print("Número do Sócio: ");
                        numeroSocio = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido! Definido como 0.");
                    }
                    
                    System.out.print("Plano: ");
                    String plano = sc.nextLine();

                    try {
                        Socio novoSocio = new Socio(nome, cpf, email, numeroSocio, plano);
                        dao.salvarSocio(novoSocio); 
                        System.out.println("Sócio cadastrado com sucesso no banco!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar sócio. CPF duplicado ou erro SQL.");
                    }
                    break;

                case 2: // CADASTRAR CONVIDADO
                    System.out.println("\n--- Adicionar Convidado ---");
                    System.out.print("Nome: ");
                    String nomeC = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfC = sc.nextLine();
                    System.out.print("Email: ");
                    String emailC = sc.nextLine();
                    System.out.print("Data da Visita: ");
                    String dataVisita = sc.nextLine();

                    try {
                        Convidado novoConvidado = new Convidado(nomeC, cpfC, emailC, dataVisita);
                        dao.salvarConvidado(novoConvidado); 
                        System.out.println("Convidado adicionado com sucesso no banco!");
                    } catch (Exception e) {
                        System.out.println("Erro ao adicionar convidado. CPF duplicado ou erro SQL.");
                    }
                    break;

                case 3: // LISTAR SÓCIOS
                    System.out.println("\n--- Lista de Sócios (DO BANCO) ---");
                    List<Socio> socios = dao.listarSocios(); 
                    if (socios.isEmpty()) System.out.println("Nenhum sócio encontrado.");
                    for (Socio s : socios) {
                        System.out.println(s);
                    }
                    break;

                case 4: // LISTAR CONVIDADOS
                    System.out.println("\n--- Lista de Convidados (DO BANCO) ---");
                    List<Convidado> convidados = dao.listarConvidados(); 
                    if (convidados.isEmpty()) System.out.println("Nenhum convidado encontrado.");
                    for (Convidado c : convidados) {
                        System.out.println(c);
                    }
                    break;

                case 5: // REMOVER SÓCIO
                    System.out.print("Digite o CPF do sócio a ser removido: ");
                    String cpfRemover = sc.nextLine();
                    if (dao.removerSocio(cpfRemover)) { 
                        System.out.println("Sócio removido com sucesso.");
                    } else {
                        System.out.println("CPF não encontrado ou erro ao remover.");
                    }
                    break;

                case 6: // ADICIONAR FUNCIONÁRIO
                    System.out.println("\n--- Adicionar Funcionário ---");
                    System.out.print("Nome: ");
                    String nomeF = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfF = sc.nextLine();
                    System.out.print("Email: ");
                    String emailF = sc.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();
                    
                    double salario = 0.0;
                    try {
                        System.out.print("Salário: ");
                        salario = Double.parseDouble(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido! Definido como 0.0");
                    }

                    try {
                        Funcionario novoFunc = new Funcionario(nomeF, cpfF, emailF, cargo, salario);
                        dao.salvarFuncionario(novoFunc); 
                        System.out.println("Funcionário adicionado com sucesso no banco!");
                    } catch (Exception e) {
                        System.out.println("Erro ao adicionar funcionário. CPF duplicado ou erro SQL.");
                    }
                    break;

                case 7: // LISTAR FUNCIONÁRIOS
                    System.out.println("\n--- Lista de Funcionários (DO BANCO) ---");
                    List<Funcionario> funcionarios = dao.listarFuncionarios(); 
                    if (funcionarios.isEmpty()) System.out.println("Nenhum funcionário encontrado.");
                    for (Funcionario f : funcionarios) {
                        System.out.println(f);
                    }
                    break;

                case 8: // REMOVER FUNCIONÁRIO
                    System.out.print("Digite o CPF do funcionário a ser removido: ");
                    String cpfRemoverF = sc.nextLine();
                    if (dao.removerFuncionario(cpfRemoverF)) { 
                        System.out.println("Funcionário removido com sucesso.");
                    } else {
                        System.out.println("CPF não encontrado ou erro ao remover.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        sc.close();
    }
}