package br.com.ada.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong; // Para gerar IDs únicos

public class Pedido {
    private static final AtomicLong ID_COUNTER = new AtomicLong(); // Contador para IDs únicos

    private long id;
    private Cliente cliente;
    private List<ItemVenda> itens; // Representa os produtos com suas quantidades e preços de venda específicos do pedido
    private LocalDateTime dataCriacao;
    private StatusPedido status;

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Um pedido deve estar associado a um cliente.");
        }
        this.id = ID_COUNTER.incrementAndGet(); // Gera um ID único para cada pedido
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusPedido.ABERTO;
    }

    /**
     * Adiciona um produto ao pedido como um ItemVenda.
     * @param produto O produto a ser adicionado.
     * @param quantidade A quantidade do produto.
     * @throws IllegalArgumentException se a quantidade for menor ou igual a zero.
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do produto deve ser maior que zero.");
        }

        // Verifica se o item já existe no pedido para atualizar a quantidade
        for (ItemVenda item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        // Se não existir, adiciona um novo ItemVenda
        this.itens.add(new ItemVenda(produto, quantidade, produto.getPrecoVenda()));
    }

    /**
     * Remove um produto do pedido.
     * @param produto O produto a ser removido.
     * @throws IllegalArgumentException se o produto não for encontrado no pedido.
     */
    public void removerProduto(Produto produto) {
        boolean removido = itens.removeIf(item -> item.getProduto().getId() == produto.getId());
        if (!removido) {
            throw new IllegalArgumentException("Produto " + produto.getNome() + " não encontrado no pedido.");
        }
    }

    /**
     * Calcula o valor total do pedido.
     * @return O valor total do pedido.
     */
    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoVenda() * item.getQuantidade())
                .sum();
    }

    /**
     * Exibe um resumo detalhado do pedido no console.
     */
    public void exibirResumo() {
        System.out.println("--- Resumo do Pedido #" + this.id + " ---");
        System.out.println("Cliente: " + this.cliente.getNome() + " (" + this.cliente.getDocumento() + ")");
        System.out.println("Data de Criação: " + this.dataCriacao);
        System.out.println("Status: " + this.status);
        System.out.println("Itens:");
        if (itens.isEmpty()) {
            System.out.println("  Nenhum item no pedido.");
        } else {
            for (ItemVenda item : itens) {
                System.out.println("  - " + item.getProduto().getNome() + " (x" + item.getQuantidade() + ") - R$ " + String.format("%.2f", item.getPrecoVenda()) + " = R$ " + String.format("%.2f", (item.getPrecoVenda() * item.getQuantidade())));
            }
        }
        System.out.println("Total do Pedido: R$ " + String.format("%.2f", calcularTotal()));
        System.out.println("---------------------------------");
    }

    // --- Getters e Setters ---

    public long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Retorna uma lista não modificável para proteger o estado interno
    public List<ItemVenda> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}