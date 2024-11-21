public class PrevisaoConsumo {

    private Long id;
    private Double estimativa;
    private String periodo;

    public PrevisaoConsumo(Long id, Double estimativa, String periodo) {
        this.id = id;
        this.estimativa = estimativa;
        this.periodo = periodo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEstimativa() {
        return estimativa;
    }

    public void setEstimativa(Double estimativa) {
        this.estimativa = estimativa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double calcularPrevisao(double consumoDiario, int diasRestantes) {
        return consumoDiario * diasRestantes;
    }

}
