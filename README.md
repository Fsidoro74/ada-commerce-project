# 🛒 Ada Commerce Project

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![IDE](https://img.shields.io/badge/IDE-IntelliJ_IDEA-blue)](https://www.jetbrains.com/idea/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![GitHub Repo](https://img.shields.io/badge/GitHub-Fsidoro74-black?logo=github)](https://github.com/Fsidoro74/ada-commerce-project)

Projeto desenvolvido como parte da trilha de estudos da **Ada Tech**.  
O objetivo é implementar um sistema de **E-Commerce simplificado**, seguindo boas práticas de **POO** e princípios **SOLID**.

---

## 🏗️ Arquitetura do Sistema

Principais classes:

- **Cliente**: Gerencia dados dos clientes.
- **Produto**: Representa os itens disponíveis para venda.
- **ItemVenda**: Relaciona produto e venda, permitindo preços diferenciados.
- **Pedido**: Controla o ciclo de vida do pedido.
- **Carrinho**: Adiciona, remove e consolida itens antes da finalização.
- **HistóricoPedidos**: Armazena o histórico de pedidos realizados.

---

## 🚀 Funcionalidades

- Cadastro e listagem de clientes
- Cadastro e listagem de produtos
- Criação de pedidos e adição de itens
- Alteração de quantidade e remoção de itens
- Finalização de pedidos (status “Aguardando pagamento”)
- Pagamento de pedidos (status “Pago”)
- Entrega de pedidos (status “Finalizado”)
- Notificações simuladas via console (e-mail)
- Histórico de pedidos

---

## 🛠️ Tecnologias

- **Java 21** (Preview features habilitadas)
- **IntelliJ IDEA**
- **Git & GitHub**

---

## 📦 Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/Fsidoro74/ada-commerce-project.git
cd ada-commerce-project