package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.StatusPedido;
import br.com.ada.ecommerce.model.Venda;
import br.com.ada.ecommerce.repository.VendaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteService clienteService;

    public VendaService(VendaRepository vendaRepository, ClienteService clienteService) {
        this.vendaRepository = vendaRepository;
        this.clienteService = clienteService;
    }

    /**
     * Cria uma nova venda para um cliente identificado por documento.
     */
    public Venda criarVenda(String documentoCliente) {
        Optional<Cliente> clienteOpt = clienteService.buscarPorDocumento(documentoCliente);
        Cliente cliente = clienteOpt.orElseThrow(() ->
                new IllegalArgumentException("Cliente não encontrado com o documento: " + documentoCliente));

        Venda venda = new Venda(cliente);
        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus(StatusPedido.ABERTO);

        vendaRepository.salvar(venda);
        return venda;
    }

    /**
     * Busca todas as vendas associadas a um documento de cliente.
     */
    public List<Venda> buscarVendasPorDocumento(String documento) {
        return vendaRepository.buscarTodos().stream()
                .filter(venda -> venda.getCliente().getDocumento().equals(documento))
                .collect(Collectors.toList());
    }

    /**
     * Busca uma venda pelo ID.
     */
    public Venda buscarVendaPorId(Long id) {
        return vendaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com o ID: " + id));
    }

    /**
     * Atualiza o status de uma venda.
     */
    public boolean atualizarStatusVenda(Long idVenda, StatusPedido novoStatus) {
        Optional<Venda> vendaOpt = vendaRepository.buscarPorId(idVenda);
        if (vendaOpt.isEmpty()) {
            return false;
        }

        Venda venda = vendaOpt.get();
        venda.setStatus(novoStatus);
        vendaRepository.atualizar(venda);
        return true;
    }

    /**
     * Lista todas as vendas registradas.
     */
    public List<Venda> listarTodasVendas() {
        return vendaRepository.buscarTodos();
    }
}