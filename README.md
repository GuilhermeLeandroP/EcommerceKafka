# E-commerce Microservices

Este repositório contém a arquitetura de microsserviços para um sistema de e-commerce, incluindo serviços como backend, frontend, inventory, banco de dados, Kafka e Zookeeper.

## Serviços Principais

- **frontend**: Interface web do sistema.
- **db**: Banco de dados PostgreSQL.
- **kafka** e **zookeeper**: Infraestrutura de mensageria para comunicação orientada a eventos.
- **inventory**: Microsserviço responsável pelo gerenciamento de estoque.

---

## Sobre o serviço de Inventory

O microsserviço **inventory** é responsável por:

- Consultar e atualizar o estoque de produtos.
- Reservar e liberar estoque para pedidos.
- Publicar eventos Kafka (`stock-reserved`, `stock-insufficient`) para outros serviços.
- Consumir eventos Kafka (`order-created`) para processar reservas de estoque automaticamente.

O serviço de inventory se comunica com outros microsserviços via eventos Kafka, garantindo um fluxo desacoplado e escalável para o gerenciamento de estoque no e-commerce.

---

## Como rodar

Execute `docker-compose up --build` na raiz do projeto para subir todos os serviços com o docker desktop aberto.

---

## Observações

- Cada microsserviço possui seu próprio Dockerfile.
- As configurações de banco de dados e Kafka são feitas via variáveis de ambiente no `docker-compose.yml`.
- Para detalhes de cada serviço, consulte o README dentro da respectiva pasta.