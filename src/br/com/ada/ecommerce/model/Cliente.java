package br.com.ada.ecommerce.model;

import java.util.Objects;

public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String documento; // CPF ou CNPJ

    public Cliente(Long id, String nome, String email, String documento) {
        validarDados(id, nome, email, documento);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    // Setters com validação
    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente deve ser válido.");
        }
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = email;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.trim().length() < 11) {
            throw new IllegalArgumentException("Documento inválido.");
        }
        this.documento = documento;
    }

    // Compatibilidade com código legado
    public String getCpf() {
        return documento;
    }

    // Método digital: retorna resumo do cliente
    public String gerarResumo() {
        return String.format("Cliente #%d - %s | Documento: %s | E-mail: %s",
                id, nome, documento, email);
    }

    // Validação centralizada
    private void validarDados(Long id, String nome, String email, String documento) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        if (documento == null || documento.trim().length() < 11) {
            throw new IllegalArgumentException("Documento inválido.");
        }
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}