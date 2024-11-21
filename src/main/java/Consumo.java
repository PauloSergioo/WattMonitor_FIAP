import java.time.LocalDateTime;

public class Consumo {

    private Long id;
    private LocalDateTime dataHora;
    private Double quantidade;

    public Consumo(Long id, LocalDateTime dataHora, Double quantidade) {
        this.id = id;
        this.dataHora = dataHora;
        this.quantidade = quantidade;
    }

    public Consumo(LocalDateTime dataHora, Double quantidade) {
        this.id = System.currentTimeMillis();
        this.dataHora = dataHora;
        this.quantidade = quantidade;
    }

    public Consumo(Double quantidade) {
        this.id = System.currentTimeMillis();
        this.dataHora = LocalDateTime.now();
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
