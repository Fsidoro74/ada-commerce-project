package br.com.ada.ecommerce.model;

import java.util.Objects;

/**
 * Classe que representa um cliente no sistema de e-commerce.
 */
public class Cliente {
    private int id;
    private String nome;
    private String cpf;

    /**
     * Construtor do Cliente
     * @param id Identificador único do cliente
     * @param nome Nome do cliente
     * @param cpf CPF do cliente
     * @throws IllegalArgumentException se o CPF for inválido
     */
    public Cliente(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        setCpf(cpf);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }

    /**
     * Define o CPF do cliente após validação
     * @param cpf CPF a ser validado e definido
     * @throws IllegalArgumentException se o CPF for inválido
     */
    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }

        // Remove caracteres especiais do CPF
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos");
        }

        if (!validarCPF(cpfLimpo)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        this.cpf = formatarCPF(cpfLimpo);
    }

    /**
     * Valida o CPF utilizando o algoritmo padrão
     * @param cpf CPF a ser validado (apenas números)
     * @return true se o CPF for válido, false caso contrário
     */
    private boolean validarCPF(String cpf) {
        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        // Calcula primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) primeiroDigito = 0;

        // Calcula segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) segundoDigito = 0;

        // Verifica se os dígitos calculados são iguais aos dígitos informados
        return Character.getNumericValue(cpf.charAt(9)) == primeiroDigito &&
               Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
    }

    /**
     * Formata o CPF no padrão XXX.XXX.XXX-XX
     * @param cpf CPF sem formatação
     * @return CPF formatado
     */
    private String formatarCPF(String cpf) {
        return String.format("%s.%s.%s-%s",
            cpf.substring(0, 3),
            cpf.substring(3, 6),
            cpf.substring(6, 9),
            cpf.substring(9));
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
