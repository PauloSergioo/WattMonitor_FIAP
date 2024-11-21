public class MetaConsumo {

    private Long id;
    private Double limiteMensal;
    private Integer mesReferencia;

    public MetaConsumo(Long id, Double limiteMensal, Integer mesReferencia) {
        this.id = id;
        this.limiteMensal = limiteMensal;
        this.mesReferencia = mesReferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLimiteMensal() {
        return limiteMensal;
    }

    public void setLimiteMensal(Double limiteMensal) {
        this.limiteMensal = limiteMensal;
    }

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public boolean verificarAlerta(double consumoAtual) {
        return consumoAtual >= (limiteMensal * 0.8);
    }

}
