package br.com.ada.ecommerce.view;

import br.com.ada.ecommerce.model.Cliente;
import br.com.ada.ecommerce.repository.ClienteRepository;

import java.util.Scanner;

public class ClienteView {

    private final Scanner scanner;
    private final ClienteRepository clienteRepository;

    public ClienteView(Scanner scanner, ClienteRepository clienteRepository) {
        this.scanner = scanner;
        this.clienteRepository = clienteRepository;
    }

    public void exibirMenu() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n👤 Menu de Clientes:");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Atualizar cliente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> atualizarCliente();
                case 0 -> executando = false;
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    public void cadastrarCliente() {
        System.out.println("📋 Cadastro de Cliente");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Documento (CPF ou RG): ");
        String documento = scanner.nextLine();

        if (documento.isBlank()) {
            System.out.println("❌ Documento obrigatório.");
            return;
        }

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(null, nome, documento, email);
        clienteRepository.salvar(cliente);

        System.out.println("✅ Cliente cadastrado com sucesso.");
    }

    public void listarClientes() {
        System.out.println("\n📋 Lista de Clientes:");
        for (Cliente cliente : clienteRepository.listarTodos()) {
            System.out.printf("ID: %d | Nome: %s | Documento: %s | E-mail: %s\n",
                    cliente.getId(), cliente.getNome(), cliente.getDocumento(), cliente.getEmail());
        }
    }

    public void atualizarCliente() {
        System.out.print("Digite o documento do cliente que deseja atualizar: ");
        String documento = scanner.nextLine();

        Cliente cliente = clienteRepository.buscarPorDocumento(documento);
        if (cliente == null) {
            System.out.println("❌ Cliente não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo e-mail: ");
        String novoEmail = scanner.nextLine();

        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);

        clienteRepository.atualizar(cliente);
        System.out.println("✅ Cliente atualizado com sucesso.");
    }
}