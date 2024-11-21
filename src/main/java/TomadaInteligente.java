import java.util.Scanner;

public class TomadaInteligente extends Tomada {
    private String horarioProgramado;

    public TomadaInteligente(String nome) {
        super(nome);
        this.horarioProgramado = null;
    }

    public void programarHorario(String horario) {
        this.horarioProgramado = horario;
        System.out.println("Tomada " + getNome() + " programada para " + horario);
    }

    @Override
    public void ligar() {
        super.ligar();
        if (horarioProgramado != null) {
            System.out.println("Tomada " + getNome() + " ligada automaticamente às " + horarioProgramado);
        }
    }

    @Override
    public void registrarConsumo(Scanner scanner) {
        super.registrarConsumo(scanner);
    }

    public void desligarAutomaticamente() {
        if (horarioProgramado != null) {
            desligar();
            System.out.println("Tomada " + getNome() + " desligada automaticamente.");
        }
    }
}
