import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tomada {
    private static long contadorId = 1;
    private Long id;
    private String nome;
    private boolean ligada;
    private List<Consumo> consumos;

    public Tomada(String nome) {
        this.id = contadorId++;
        this.nome = nome;
        this.ligada = false;
        this.consumos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isLigada() {
        return ligada;
    }

    public void ligar() {
        if (!ligada) {
            ligada = true;
            System.out.println("Tomada " + nome + " ligada.");
        } else {
            System.out.println("Tomada " + nome + " já está ligada.");
        }
    }

    public void desligar() {
        if (ligada) {
            ligada = false;
            System.out.println("Tomada " + nome + " desligada.");
        } else {
            System.out.println("Tomada " + nome + " já está desligada.");
        }
    }

    public void registrarConsumo(Scanner scanner) {
        try {
            System.out.print("Digite a quantidade de consumo (em kWh) para a tomada " + nome + ": ");
            double quantidade = Double.parseDouble(scanner.nextLine());

            if (quantidade < 0) {
                throw new IllegalArgumentException("O consumo não pode ser negativo.");
            }

            Consumo consumo = new Consumo(quantidade);
            consumos.add(consumo);
            System.out.println("Consumo de " + quantidade + " kWh registrado para a tomada " + nome);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido para consumo. Tente novamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public double calcularConsumoTotal() {
        return consumos.stream().mapToDouble(Consumo::getQuantidade).sum();
    }

    public void programarHorario(String ligar, String desligar) {
        System.out.printf("Tomada '%s' programada para LIGAR às %s e DESLIGAR às %s.\n", nome, ligar, desligar);
    }
}
