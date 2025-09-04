package br.com.ada.ecommerce.model;

import java.util.concurrent.atomic.AtomicLong;

public class Cliente {
    private static final AtomicLong ID_COUNTER = new AtomicLong();

    private int id;
    private String nome;
    private String documento; // Documento de identificação (ex: CPF)
    private String email; // E-mail de contato

    public Cliente(int id, String nome, String documento) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
    }

    // Construtor adicional sem e-mail: gera ID automaticamente
    public Cliente(String nome, String documento) {
        this.id = (int) ID_COUNTER.incrementAndGet();
        this.nome = nome;
        this.documento = documento;
        this.email = null;
    }

    // Construtor compatível com o Main: nome, documento (CPF) e e-mail
    public Cliente(String nome, String documento, String email) {
        this.id = (int) ID_COUNTER.incrementAndGet();
        this.nome = nome;
        this.documento = documento;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    // Getter compatível com chamadas existentes no projeto
    public String getDocumento() {
        return documento;
    }

    public String getEmail() { return email; }

    // Getter alternativo mantido por compatibilidade
    public String getDocumentoIdentificacao() {
        return documento;
    }

    @Override
    public String toString() {
        return nome + " (" + documento + ")";
    }
}

