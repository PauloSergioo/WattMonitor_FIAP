public class SugestaoEconomia {

    private Long id;
    private String descricao;

    public SugestaoEconomia(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String sugerirEconomia(double consumoAtual, double limiteMensal) {
        if (consumoAtual > limiteMensal) {
            return "Desligue dispositivos desnecessários para economizar.";
        }
        return "Consumo dentro do limite. Continue economizando!";
    }

}
