# Ada Commerce Project

Este projeto foi desenvolvido como parte da trilha de estudos da **Ada Tech** e tem como objetivo a implementação de um sistema de E-Commerce simplificado, seguindo boas práticas de Programação Orientada a Objetos (POO) e princípios SOLID.

---

## 📌 Visão Geral da Arquitetura
O sistema foi modelado com as seguintes classes principais:

- **Cliente**: Responsável por armazenar e gerenciar os dados dos clientes.
- **Produto**: Representa os itens disponíveis para venda.
- **ItemVenda**: Relaciona um produto a uma venda, permitindo preços diferentes por venda.
- **Venda**: Classe central que gerencia o ciclo de vida do pedido.
- **Notificacao**: Serviço responsável por simular o envio de notificações ao cliente.
- **Database**: Simula um banco de dados em memória, centralizando o armazenamento das entidades.

Essa arquitetura garante **separação de responsabilidades (SRP)** e maior facilidade de manutenção e escalabilidade.

---

## 🏗️ Modelagem das Classes

### 1. Cliente
- Atributos: `id`, `nome`, `email`, `documento`  
- Métodos: `__init__`, `atualizar_dados`

### 2. Produto
- Atributos: `id`, `nome`, `descricao`, `preco_base`  
- Métodos: `__init__`, `atualizar_produto`

### 3. ItemVenda
- Atributos: `produto`, `quantidade`, `preco_venda`  
- Métodos: `calcular_subtotal`

### 4. Venda
- Atributos: `id`, `cliente`, `data_criacao`, `itens`, `status`, `valor_total`  
- Métodos: `adicionar_item`, `remover_item`, `alterar_quantidade_item`, `calcular_total`, `finalizar_pedido`, `processar_pagamento`, `entregar_pedido`

### 5. Notificacao
- Método: `enviar_email(mensagem)` → Simula envio de notificações.

### 6. Database
- Estrutura de armazenamento em dicionário para simulação de banco de dados em memória.

---

## ▶️ Fluxo do Sistema
1. Cadastro de **clientes** e **produtos**.
2. Criação de uma **venda** vinculada a um cliente.
3. Adição de **itens** à venda.
4. **Finalização do pedido**, alterando o status para *Aguardando pagamento*.
5. **Processamento do pagamento**, alterando o status para *Pago*.
6. **Entrega do pedido**, finalizando o ciclo da venda.

---

## 📂 Estrutura de Pastas
```bash
ada-commerce-project/
│-- src/
│   ├── cliente.py
│   ├── produto.py
│   ├── item_venda.py
│   ├── venda.py
│   ├── notificacao.py
│   ├── database.py
│   └── main.py
│
└── README.md
```

---

## 💻 Como Executar
1. Clone este repositório:
```bash
git clone https://github.com/Fsidoro74/ada-commerce-project.git
```

2. Acesse a pasta do projeto:
```bash
cd ada-commerce-project
```

3. Execute o arquivo principal:
```bash
python src/main.py
```

---

## 📧 Notificações
As notificações são simuladas via terminal, exibindo mensagens de e-mail enviadas ao cliente durante o fluxo da venda.

---

## ✅ Conclusão
Este projeto demonstra a aplicação prática dos conceitos de **POO, SOLID e boas práticas de design de software**, além de fornecer uma base extensível para evoluir em futuras implementações.

🔗 Repositório Oficial: [Ada Commerce Project](https://github.com/Fsidoro74/ada-commerce-project)

