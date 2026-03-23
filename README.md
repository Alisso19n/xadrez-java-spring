# ♟ Sistema de Jogo de Xadrez

Projeto desenvolvido em **Java + Spring Boot** que implementa um jogo de xadrez funcional com interface web.

O objetivo do projeto é aplicar **programação orientada a objetos** e **padrões de projeto**, organizando o sistema em camadas seguindo boas práticas de desenvolvimento.

---

# 🧠 Padrões de Projeto Utilizados

O projeto utiliza os seguintes padrões:

### Strategy (Comportamental)

Responsável por definir o algoritmo de movimentação de cada peça.

Cada peça possui uma estratégia de movimento específica:

* MovimentoTorre
* MovimentoBispo
* MovimentoCavalo
* MovimentoRainha
* MovimentoRei
* MovimentoPeao

Isso permite alterar ou adicionar novos comportamentos sem modificar outras partes do sistema.

---

### Factory (Criacional)

Utilizado para centralizar a criação das peças.

Classe responsável:

```
FabricaPecas
```

Ela recebe o tipo da peça e a cor e retorna o objeto correto já configurado.

Benefícios:

* evita código duplicado
* centraliza a criação de objetos

---

### Facade (Estrutural)

Implementado no serviço:

```
PartidaService
```

Ele simplifica o acesso às regras do jogo e serve como ponto de entrada para as operações da partida.

---

### MVC (Arquitetural)

A aplicação segue a arquitetura **Model-View-Controller**.

Separação das responsabilidades:

Model:

```
domain.model
```

Controller:

```
web.controller
```

Service:

```
application.service
```

View:

```
index.html
```

---

### DTO (Data Transfer Object)

Utilizado para transportar dados entre backend e frontend.

Exemplos:

* TabuleiroDTO
* PecaDTO
* MovimentoRequestDTO

Isso evita expor diretamente as entidades de domínio.

---

### Singleton (gerenciado pelo Spring)

Os serviços anotados com:

```
@Service
```

são gerenciados pelo container do Spring como **uma única instância**, garantindo controle central da partida.

---

# 🏗 Estrutura do Projeto

```
src
 └─ main
     ├─ java
     │   └─ br.edu.ifbasaj.xadrez
     │       ├─ domain
     │       │   ├─ model
     │       │   ├─ strategy
     │       │   └─ factory
     │       │
     │       ├─ application
     │       │   ├─ service
     │       │   └─ dto
     │       │
     │       └─ web
     │           └─ controller
     │
     └─ resources
         └─ static
             └─ index.html
```

---

# 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot
* Maven
* HTML
* JavaScript
* IntelliJ IDEA
* Git / GitHub

---

# ▶ Como executar o projeto

1. Clonar o repositorio

```
https://github.com/Alisso19n/xadrez-java-spring
```
2. Abrir no IntelliJ IDEA

3. Executar a classe principal:

```
XadrezApplication
```

4. Acessar no navegador:

```
http://localhost:8080
```

---

# 🎯 Funcionalidades

✔ Movimentação das peças
✔ Validação de movimentos
✔ Captura de peças
✔ Detecção de xeque
✔ Interface web do tabuleiro
✔ Reinício de partida

---

# 👨‍💻 Autor

Projeto desenvolvido para estudo de **Padrões de Projeto e Programação Orientada a Objetos**.
