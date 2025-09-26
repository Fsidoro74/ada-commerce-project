package br.com.ada.ecommerce.model;

import java.util.Objects;

public class Cliente {
    private Long id;
    private String nome;
    private String documento;
    private String email;

    public Cliente(Long id, String nome, String documento, String email) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento é obrigatório.");
        }
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDocumento() { return documento; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(documento, cliente.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }
}