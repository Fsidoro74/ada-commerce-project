

# 🛒 Ada Commerce Project

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![IDE](https://img.shields.io/badge/IDE-IntelliJ_IDEA-blue)](https://www.jetbrains.com/idea/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![GitHub Repo](https://img.shields.io/badge/GitHub-Fsidoro74-black?logo=github)](https://github.com/Fsidoro74/ada-commerce-project)

Este projeto foi desenvolvido como parte da trilha de estudos da **Ada Tech** e tem como objetivo a implementação de um sistema de E-Commerce simplificado, seguindo boas práticas de **Programação Orientada a Objetos (POO)** e princípios **SOLID**.

---

## 🏗️ Visão Geral da Arquitetura

O sistema foi modelado com as seguintes classes principais:

- **Cliente**: Responsável por armazenar e gerenciar os dados dos clientes.
- **Produto**: Representa os itens disponíveis para venda.
- **ItemVenda**: Relaciona um produto a uma venda, permitindo preços diferenciados.
- **Venda**: Classe central que gerencia o ciclo de vida do pedido.
- **Carrinho**: Responsável por adicionar, remover e consolidar os itens antes da finalização da compra.
- **HistóricoPedidos**: Armazena o histórico de pedidos realizados.

---

## 🚀 Funcionalidades

- Cadastro e gerenciamento de clientes
- Cadastro de produtos
- Adição e remoção de itens no carrinho
- Finalização de vendas com geração de pedidos
- Consulta de histórico de compras

---

## 🛠️ Tecnologias Utilizadas

- **Java 21 (Preview features habilitadas)**
- **IntelliJ IDEA**
- **Git & GitHub**

---

## 📦 Como Executar o Projeto

1. Clone este repositório:
   ```
   git clone https://github.com/Fsidoro74/ada-commerce-project.git
   ```

<<<<<<< HEAD
2. Compile e rode com recursos de preview habilitados.

### Opção A) Terminal (Windows PowerShell)

- Compilar (gera classes em `out`):
  ```
  javac --enable-preview --release 21 -d out -cp src src\br\com\ada\ecommerce\**\*.java src\Main.java
  ```

- Executar a aplicação:
  ```
  java --enable-preview -cp out br.com.ada.ecommerce.main.Main
  ```

Observações:
- Requer JDK 21 instalado e configurado no PATH.
- O comando usa curingas do Windows para compilar todas as classes do pacote `br.com.ada.ecommerce` e também `src\Main.java` se necessário.

### Opção B) IntelliJ IDEA

1. Vá em File > Project Structure > Project:
   - Project SDK: selecione JDK 21
   - Language level: 21 (Preview features)
2. Vá em Run > Edit Configurations, escolha a configuração de execução do Main e, em VM options, adicione:
   ```
   --enable-preview
   ```

Pronto! Agora o projeto utiliza e continua habilitado para recursos em preview do Java 21.
=======
2. Compile e execute pela IDE (classe Main) ou via linha de comando (Java 17).

### Como rodar testes automatizados (sem Maven/Gradle)

Foi incluído um test runner simples em Java puro:
- Classe: `br.com.ada.ecommerce.tests.TestRunner`
- Veja também: `src/br/com/ada/ecommerce/README_TESTS.md`

Passos:
1) Compile as classes do projeto (src).
2) Execute a classe `br.com.ada.ecommerce.tests.TestRunner`.
3) Verifique no console as linhas `[TEST_PASS]` e o resumo `[TEST_SUMMARY]`.

>>>>>>> 716dcb39db47a89030607c596407e4da9f215ebe
