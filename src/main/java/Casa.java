import java.util.*;

public class Casa {
    private Long id;
    private String nome;
    private String endereco;
    private List<Tomada> tomadas;

    public Casa(Long id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tomadas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void cadastrarTomada(Scanner scanner) {
        System.out.print("Digite o nome da tomada: ");
        String nome = scanner.nextLine();

        Tomada tomada = new Tomada(nome);
        tomadas.add(tomada);
        System.out.println("Tomada cadastrada com sucesso! ID: " + tomada.getId());
    }

    public void exibirTomadas() {
        if (tomadas.isEmpty()) {
            System.out.println("Nenhuma tomada cadastrada nesta casa.");
            return;
        }

        for (Tomada tomada : tomadas) {
            System.out.println("ID: " + tomada.getId() + " | Nome: " + tomada.getNome());
        }
    }

    public void registrarConsumo(Scanner scanner) {
        System.out.print("Digite a quantidade de consumo (em kWh): ");
        double quantidade = Double.parseDouble(scanner.nextLine());

        Consumo consumo = new Consumo(quantidade);
        System.out.println("Consumo registrado: " + consumo.getQuantidade() + " kWh");
    }

    public List<Tomada> getTomadas() {
        return tomadas;
    }

    public Tomada getTomadaPorId(long id) {
        for (Tomada tomada : tomadas) {
            if (tomada.getId() == id) {
                return tomada;
            }
        }
        return null;
    }
}
