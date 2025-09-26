package br.com.ada.ecommerce.view;

import java.util.Scanner;

public class MenuView {
    private final Scanner scanner = new Scanner(System.in);

    public int escolherTipoDeNotificacao() {
        System.out.println("Escolha o tipo de notificação:");
        System.out.println("1 - E-mail");
        System.out.println("2 - SMS");
        System.out.print("Opção: ");
        return scanner.nextInt();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirResumo(String titulo, String resumo) {
        System.out.println(titulo);
        System.out.println(resumo);
    }

    public void fecharScanner() {
        scanner.close();
    }
}