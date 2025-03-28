# 🎮 Jogo da Memória

# 📜 Descrição
O jogo é composto por um tabuleiro 4x4, e o jogador tem um número limitado de tentativas para virar as cartas e encontrar os pares de cartas com números iguais. Esse projeto foi desenvolvido como parte da cadeira de PPOO.

# 🧩 Funcionalidades
- Tabuleiro 4x4 com 16 cartas.
- Cartas embaralhadas e distribuídas aleatoriamente.
- O jogador deve virar as cartas uma por vez, tentando encontrar pares.
- Pares iguais permanecem virados.
- O jogador possui 6 vidas para encontrar os pares, caso encontre um par errado, ele perderá uma vida.
- Após o fim do jogo, uma mensagem exibe se o jogador ganhou ou perdeu.


# 🛠️ Tecnologias Utilizadas
- Java: Linguagem de programação principal utilizada para o desenvolvimento do jogo.
- Swing: Biblioteca para criar a interface gráfica do jogo.
- JDK 17 ou superior: Necessário para compilar e executar o projeto.

# 💻 Padrões de Projeto Aplicados
1. Observer Pattern 🧐
O Observer Pattern foi utilizado para notificar o controlador sempre que o estado do tabuleiro mudar (ex: quando uma carta é virada). A classe GameBoard atua como o Subject (Sujeito), enquanto a classe MemoryGameController é o Observer (Observador).

2. Factory Pattern 🏭
O Factory Pattern foi utilizado para a criação das cartas, centralizando a lógica de criação na classe CardFactory. Isso permite que a criação das cartas seja modular e facilmente extensível.

3. Singleton Pattern (Padrão Singleton) 🔒
O Singleton garante que o jogo tenha uma única instância de GameBoard e seja acessível globalmente.

4. Command Pattern (Padrão de Comando) 📜
O Command Pattern foi aplicado para encapsular ações como "virar uma carta".
