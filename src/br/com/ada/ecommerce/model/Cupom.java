package br.com.ada.ecommerce.model;

import java.time.LocalDate;

public class Cupom {

    private Long id;
    private String codigo;
    private double valorDesconto;
    private boolean percentual;
    private LocalDate validade;
    private boolean utilizado;

    public Cupom(Long id, String codigo, double valorDesconto, boolean percentual, LocalDate validade) {
        this.id = id;
        this.codigo = codigo;
        this.valorDesconto = valorDesconto;
        this.percentual = percentual;
        this.validade = validade;
        this.utilizado = false;
    }

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public double getValorDesconto() { return valorDesconto; }
    public boolean isPercentual() { return percentual; }
    public LocalDate getValidade() { return validade; }
    public boolean isUtilizado() { return utilizado; }

    public void setValorDesconto(double valorDesconto) { this.valorDesconto = valorDesconto; }
    public void setPercentual(boolean percentual) { this.percentual = percentual; }
    public void setValidade(LocalDate validade) { this.validade = validade; }

    public boolean estaValido() {
        return !utilizado && LocalDate.now().isBefore(validade);
    }

    public double aplicarDesconto(double valorOriginal) {
        return percentual ? valorOriginal * (valorDesconto / 100) : valorDesconto;
    }

    public void marcarComoUtilizado() {
        this.utilizado = true;
    }
}