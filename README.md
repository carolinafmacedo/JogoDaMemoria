# 🎮 Jogo da Memória

# 📜 Descrição
O Jogo da Memória é composto por um tabuleiro que pode ser configurado com diferentes dimensões, como 4x4 (16 cartas) ou 6x6 (36 cartas). O jogador tem um número limitado de tentativas para virar as cartas e encontrar pares ou trios de cartas com números iguais. Desenvolvido como parte da cadeira de PPOO.

# 🧩 Funcionalidades

Modalidade Fácil (4x4 - Pares):

- Tabuleiro 4x4 com 16 cartas.
- O jogador deve virar as cartas uma por vez, tentando encontrar pares com o mesmo valor, são 8 pares no total.
- Cartas iguais permanecem viradas.
- O jogador possui 6 vidas. Caso vire uma carta de par errado, perde uma vida.
- Ao final do jogo, uma mensagem exibe se o jogador venceu ou perdeu.
  
Modalidade Difícil (6x6 - Trios):
- Tabuleiro 6x6 com 36 cartas.
- O jogador deve virar três cartas por vez, tentando encontrar trios de cartas com o mesmo valor, são 12 pares no total.
- Cartas iguais permanecem viradas.
- O jogador possui 10 vidas. Caso vire uma carta de trio errado, perde uma vida.
- Ao final do jogo, uma mensagem exibe se o jogador venceu ou perdeu.


# 🛠️ Tecnologias Utilizadas
Java: Linguagem de programação principal utilizada para o desenvolvimento do jogo.
Swing: Biblioteca para criar a interface gráfica do jogo.
JDK 17 ou superior: Necessário para compilar e executar o projeto.

# 💻 Padrões de Projeto Aplicados
- Observer
- Factory Method
- Command
- MVC Pattern (Model-View-Controller)
