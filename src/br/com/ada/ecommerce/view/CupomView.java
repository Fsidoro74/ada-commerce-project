package br.com.ada.ecommerce.view;

import br.com.ada.ecommerce.model.Cupom;
import br.com.ada.ecommerce.repository.CupomRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class CupomView {

    private final Scanner scanner;
    private final CupomRepository cupomRepository;

    public CupomView(Scanner scanner, CupomRepository cupomRepository) {
        this.scanner = scanner;
        this.cupomRepository = cupomRepository;
    }

    public void listarCupons() {
        System.out.println("\n🎟️ Cupons disponíveis:");
        for (Cupom cupom : cupomRepository.listarTodos()) {
            String tipo = cupom.isPercentual() ? "Percentual" : "Fixo";
            String status = cupom.estaValido() ? "Válido" : "Expirado ou utilizado";
            System.out.printf("Código: %s | Tipo: %s | Desconto: %.2f | Validade: %s | Status: %s\n",
                    cupom.getCodigo(), tipo, cupom.getValorDesconto(), cupom.getValidade(), status);
        }
    }

    public void atualizarCupom() {
        System.out.print("Digite o código do cupom que deseja atualizar: ");
        String codigo = scanner.nextLine();
        Optional<Cupom> cupomOpt = cupomRepository.buscarPorCodigo(codigo);

        if (cupomOpt.isEmpty()) {
            System.out.println("❌ Cupom não encontrado.");
            return;
        }

        Cupom cupom = cupomOpt.get();

        System.out.printf("Cupom atual: %.2f (%s), validade: %s\n",
                cupom.getValorDesconto(),
                cupom.isPercentual() ? "percentual" : "fixo",
                cupom.getValidade());

        System.out.print("Novo valor de desconto: ");
        double novoValor = scanner.nextDouble();

        System.out.print("É percentual? (true/false): ");
        boolean novoTipo = scanner.nextBoolean();

        System.out.print("Dias até nova validade: ");
        int dias = scanner.nextInt();
        scanner.nextLine();

        cupom.setValorDesconto(novoValor);
        cupom.setPercentual(novoTipo);
        cupom.setValidade(LocalDate.now().plusDays(dias));

        System.out.println("✅ Cupom atualizado com sucesso.");
    }
}