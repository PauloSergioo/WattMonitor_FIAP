import java.util.*;

public class Main {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final Map<Long, Casa> casas = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        iniciarSistema();
    }

    private static void iniciarSistema() {
        boolean rodando = true;

        while (rodando) {
            exibirMenuPrincipal();
            int escolha = obterEscolha();

            switch (escolha) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarCasa();
                case 3 -> gerenciarTomadas();
                case 4 -> exibirSugestoesEconomia();
                case 5 -> {
                    System.out.println("Saindo do sistema. Obrigado por usar o WattMonitor!");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n==== WattMonitor ====");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Cadastrar Casa");
        System.out.println("3. Gerenciar Tomadas");
        System.out.println("4. Exibir Sugestões de Economia");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int obterEscolha() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
            return -1;
        }
    }


    private static void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        long id = usuarios.size() + 1;
        Usuario usuario = new Usuario(id, nome, email, senha);
        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso! ID: " + usuario.getId());
    }

    private static void cadastrarCasa() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado. Cadastre um usuário antes de criar uma casa.");
            return;
        }

        System.out.println("\n==== Cadastrar Casa ====");
        System.out.print("Digite o nome da casa: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();

        System.out.println("\n==== Usuários Cadastrados ====");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome());
        }

        System.out.print("Digite o ID do usuário responsável por esta casa: ");
        long idUsuario = Long.parseLong(scanner.nextLine());
        Usuario dono = usuarios.stream()
                .filter(u -> u.getId() == idUsuario)
                .findFirst()
                .orElse(null);

        if (dono == null) {
            System.out.println("Usuário não encontrado. Cadastro de casa cancelado.");
            return;
        }

        long id = casas.size() + 1;
        Casa casa = new Casa(id, nome, endereco);
        casas.put(id, casa);

        System.out.println("Casa cadastrada com sucesso! ID: " + casa.getId() + " (Responsável: " + dono.getNome() + ")");
    }

    private static void gerenciarTomadas() {
        if (casas.isEmpty()) {
            System.out.println("\nNão há casas cadastradas. Cadastre uma casa primeiro.\n");
            return;
        }

        System.out.println("\n==== Casas Cadastradas ====");
        for (Casa casa : casas.values()) {
            System.out.println("ID: " + casa.getId() + " | Nome: " + casa.getNome());
        }
        System.out.println();

        System.out.print("Digite o ID da casa que deseja gerenciar as tomadas: ");
        long idCasa = Long.parseLong(scanner.nextLine());

        Casa casa = casas.get(idCasa);

        if (casa == null) {
            System.out.println("\nCasa não encontrada.\n");
            return;
        }

        System.out.println("\n==== Gerenciar Tomadas ====");
        while (true) {
            if (casa.getTomadas().isEmpty()) {
                System.out.println("\nNenhuma tomada cadastrada nesta casa. Cadastre uma antes de gerenciar.");
            } else {
                System.out.println("\nTomadas cadastradas:");
                casa.exibirTomadas();
            }
            System.out.println();

            System.out.println("1. Cadastrar nova tomada");
            System.out.println("2. Gerenciar consumo de uma tomada");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int escolha = obterEscolha();

            switch (escolha) {
                case 1 -> {
                    System.out.println("\n==== Cadastrar Tomada ====");
                    casa.cadastrarTomada(scanner);
                    System.out.println();
                }
                case 2 -> {
                    if (casa.getTomadas().isEmpty()) {
                        System.out.println("\nNenhuma tomada cadastrada nesta casa. Cadastre uma antes de gerenciar.\n");
                    } else {
                        System.out.print("\nDigite o ID da tomada: ");
                        long idTomada = Long.parseLong(scanner.nextLine());
                        Tomada tomada = casa.getTomadaPorId(idTomada);
                        if (tomada != null) {
                            System.out.println("\n==== Registrar Consumo ====");
                            tomada.registrarConsumo(scanner);
                            System.out.println();
                        } else {
                            System.out.println("\nTomada não encontrada.\n");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("\nVoltando ao menu principal...\n");
                    return;
                }
                default -> System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        }
    }

    private static void exibirSugestoesEconomia() {
        if (casas.isEmpty()) {
            System.out.println("Nenhuma casa cadastrada. Cadastre uma casa para acessar sugestões de economia.");
            return;
        }

        System.out.println("\n==== Sugestões de Economia ====");
        System.out.println("1. Relatório de Consumo");
        System.out.println("2. Programar horários de funcionamento");
        System.out.println("3. Dicas de economia");
        System.out.println("4. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        int escolha = obterEscolha();

        switch (escolha) {
            case 1 -> gerarRelatorioConsumo();
            case 2 -> programarHorario();
            case 3 -> exibirDicasEconomia();
            case 4 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void gerarRelatorioConsumo() {
        if (casas.isEmpty()) {
            System.out.println("\nNenhuma casa cadastrada. Cadastre uma casa primeiro.\n");
            return;
        }

        System.out.println("\n==== Relatório de Consumo ====");

        System.out.println("\n==== Casas Cadastradas ====");
        for (Casa casa : casas.values()) {
            System.out.println("ID: " + casa.getId() + " | Nome: " + casa.getNome());
        }

        System.out.print("\nDigite o ID da casa para gerar o relatório: ");
        long idCasa = Long.parseLong(scanner.nextLine());

        Casa casa = casas.get(idCasa);

        if (casa == null) {
            System.out.println("\nCasa não encontrada.\n");
            return;
        }

        if (casa.getTomadas().isEmpty()) {
            System.out.println("\nNenhuma tomada cadastrada nesta casa.");
            System.out.println("1. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int escolha = obterEscolha();
            if (escolha == 1) {
                return;
            } else {
                System.out.println("Opção inválida. Voltando ao menu principal...");
                return;
            }
        }

        System.out.println("\n==== Tomadas Cadastradas ====");
        casa.exibirTomadas();

        System.out.print("\nDigite o ID da tomada para gerar o relatório de consumo: ");
        long idTomada = Long.parseLong(scanner.nextLine());

        Tomada tomada = casa.getTomadaPorId(idTomada);

        if (tomada == null) {
            System.out.println("\nTomada não encontrada.\n");
            return;
        }

        System.out.println("\n==== Relatório de Consumo da Tomada " + tomada.getNome() + " ====");
        double consumoTotal = tomada.calcularConsumoTotal();
        System.out.println("Consumo total da tomada " + tomada.getNome() + ": " + consumoTotal + " kWh");
        System.out.println("\nRelatório gerado com sucesso!\n");
    }

    private static void programarHorario() {
        System.out.println("\n==== Programar Horários ====");

        if (casas.isEmpty()) {
            System.out.println("Nenhuma casa registrada.");
            return;
        }

        System.out.println("\nSelecione uma casa:");
        for (Map.Entry<Long, Casa> entry : casas.entrySet()) {
            Casa casa = entry.getValue();
            System.out.println("ID: " + casa.getId() + " - " + casa.getNome());
        }

        System.out.print("\nDigite o ID da casa: ");
        long idCasa = Long.parseLong(scanner.nextLine());
        Casa casa = casas.get(idCasa);

        if (casa == null) {
            System.out.println("\nCasa não encontrada. Tente novamente.");
            return;
        }

        if (casa.getTomadas().isEmpty()) {
            System.out.println("\nEsta casa não possui tomadas registradas.");
            return;
        }

        System.out.println("\nSelecione uma tomada:");
        for (Tomada tomada : casa.getTomadas()) {
            System.out.println("ID: " + tomada.getId() + " - " + tomada.getNome());
        }

        System.out.print("\nDigite o ID da tomada: ");
        long idTomada = Long.parseLong(scanner.nextLine());
        Tomada tomada = casa.getTomadaPorId(idTomada);

        if (tomada == null) {
            System.out.println("\nTomada não encontrada. Tente novamente.");
            return;
        }

        System.out.print("\nDigite o horário para ligar (HH:mm): ");
        String horarioLigar = scanner.nextLine();
        System.out.print("Digite o horário para desligar (HH:mm): ");
        String horarioDesligar = scanner.nextLine();

        System.out.println("\nVocê está programando a tomada '" + tomada.getNome() + "' na casa '" + casa.getNome() + "'.");
        System.out.println("Horário para LIGAR: " + horarioLigar);
        System.out.println("Horário para DESLIGAR: " + horarioDesligar);
        System.out.print("\nDeseja confirmar a programação? (S/N): ");
        String confirmacao = scanner.nextLine().toUpperCase();

        if (confirmacao.equals("S")) {
            tomada.programarHorario(horarioLigar, horarioDesligar);
            System.out.println("\nProgramação confirmada com sucesso!");
        } else {
            System.out.println("\nProgramação cancelada.");
        }
    }

    private static void exibirDicasEconomia() {
        System.out.println("\n==== Dicas de Economia ====");
        System.out.println("- Desligue as tomadas quando não estiverem em uso.");
        System.out.println("- Programe horários para dispositivos energéticos.");
        System.out.println("- Utilize equipamentos eficientes.");
        System.out.println("- Revise seu consumo regularmente.");
    }
}
