package br.com.ada.ecommerce.model;

import java.time.LocalDate;

public class Cupom {

    private Long id;
    private String codigo;
    private double valorDesconto;
    private boolean percentual;
    private LocalDate validade;
    private boolean utilizado;

    // Construtor principal
    public Cupom(Long id, String codigo, double valorDesconto, boolean percentual, LocalDate validade) {
        setCodigo(codigo);
        setValorDesconto(valorDesconto);
        setPercentual(percentual);
        setValidade(validade);
        this.id = id;
        this.utilizado = false;
    }

    // Construtor alternativo sem ID
    public Cupom(String codigo, double valorDesconto, boolean percentual, LocalDate validade) {
        this(null, codigo, valorDesconto, percentual, validade);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código é obrigatório.");
        }
        this.codigo = codigo;
    }

    public double getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(double valorDesconto) {
        if (valorDesconto <= 0) {
            throw new IllegalArgumentException("Desconto deve ser maior que zero.");
        }
        this.valorDesconto = valorDesconto;
    }

    public boolean isPercentual() { return percentual; }
    public void setPercentual(boolean percentual) { this.percentual = percentual; }

    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) {
        if (validade == null || validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Validade deve ser uma data futura.");
        }
        this.validade = validade;
    }

    public boolean isUtilizado() { return utilizado; }

    public boolean estaValido() {
        return !utilizado && !LocalDate.now().isAfter(validade);
    }

    public double aplicarDesconto(double valorOriginal) {
        double desconto = percentual ? valorOriginal * (valorDesconto / 100) : valorDesconto;
        return Math.min(desconto, valorOriginal);
    }

    public void marcarComoUtilizado() {
        this.utilizado = true;
    }
}