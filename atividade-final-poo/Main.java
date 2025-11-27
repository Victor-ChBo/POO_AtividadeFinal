package atividade-final-poo;

do{
    system.out.println("Digite a opção desejada:");
    system.out.println("1-Cadastrar sócio");
    system.out.println("2-Adicionar convidados");
    system.out.println("3-Listar sócios");
    system.out.println("4-Listar convidados");
    system.out.println("5-Remover sócio");
    system.out.println("6-adicionar funcionário");
    system.out.println("7-Listar funcionários");
    system.out.println("8-Remover funcionário");
    system.out.println("0-Sair");
}

switch (opcao) {
    case 1:
        // código para cadastrar sócio
        System.out.println("Cadastro de sócio");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Número do Sócio: ");
        int numeroSocio = Integer.parseInt(sc.nextLine());
        System.out.print("Plano: ");
        String plano = sc.nextLine();

        try{
            Socio novoSocio = new Socio(nome, cpf, email, numeroSocio, plano);
            socios.add(novoSocio);
            system.out.println("Sócio cadastrado com sucesso!");
        } catch (Exception e) {
            system.out.println("Erro ao cadastrar sócio: " + e.getMessage());
        }
        break;
    case 2:
        // código para adicionar convidados
        System.out.println("Adicionar convidado");
        System.out.print("Nome: ");
        String nomeC = sc.nextLine();
        System.out.print("CPF: ");
        String cpfC = sc.nextLine();
        System.out.print("Email: ");
        String emailC = sc.nextLine();
        System.out.print("Data da Visita: ");
        String dataVisita = sc.nextLine();

        try{
            Convidado novoConvidado = new Convidado(nomeC, cpfC, emailC, dataVisita);
            convidados.add(novoConvidado);
            system.out.println("Convidado adicionado com sucesso!");
        } catch (Exception e) {
            system.out.println("Erro ao adicionar convidado: " + e.getMessage());
        }
        break;
    case 3:
        // código para listar sócios
        break;
    case 4:
        // código para listar convidados
        break;
    case 5:
        // código para remover sócio
        break;
    case 6:
        // código para adicionar funcionário
        break;
    case 7:
        // código para listar funcionários
        break;
    case 8:
        // código para remover funcionário
        break;
    case 0:
        system.out.println("Saindo do programa...");
        break;
    default:
        system.out.println("Opção inválida. Tente novamente.");
}
} while (opcao != 0);
sc.close();
}