package br.com.ada.ecommerce.service;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.model.StatusPedido;
import br.com.ada.ecommerce.model.Venda;
import br.com.ada.ecommerce.repository.VendaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VendaService {
    private final VendaRepository vendaRepository;
    private final ClienteService clienteService;

    public VendaService(VendaRepository vendaRepository, ClienteService clienteService) {
        this.vendaRepository = vendaRepository;
        this.clienteService = clienteService;
    }

    public Venda criarVenda(String cpfCliente) {
        Cliente cliente = clienteService.buscarPorCpf(cpfCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado com o CPF: " + cpfCliente);
        }

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus(StatusPedido.ABERTO);

        vendaRepository.salvar(venda);
        return venda;
    }

    public List<Venda> buscarVendasPorCpf(String cpf) {
        return vendaRepository.buscarTodos().stream()
                .filter(venda -> venda.getCliente().getCpf().equals(cpf))
                .collect(Collectors.toList());
    }

    public Venda buscarVendaPorId(Long id) {
        return vendaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com o ID: " + id));
    }

    public void atualizarStatusVenda(Long idVenda, StatusPedido novoStatus) {
        Venda venda = buscarVendaPorId(idVenda);
        venda.setStatus(novoStatus);
        vendaRepository.atualizar(venda);
    }

    public List<Venda> listarTodasVendas() {
        return vendaRepository.buscarTodos();
    }
}
