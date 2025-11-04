import pt.isel.canvas.*

data class Game(val area:Area, val balls:List<Ball>, val racket:Racket)
/**As bolas refletem nas laterais e no topo da área.
A raquete impede que as bolas desapareçam pela base.
Uma nova bola aparece pela base a cada 5 segundos.
O jogo termina quando é fechada a janela ou quando desaparece a última bola
que estava na área.
Cada bola tem um raio de 7 pixels e
move-se a cada 10 milissegundos, de
acordo com o seu vetor de deslocamento.
O deslocamento na horizontal (dx) está
compreendido no intervalo [-6..6] e o
deslocamento na vertical (dy) é -4 quando a
bola se move para cima, ou 4 quando se
move para baixo.
O topo e as laterais provocam reflexões
simétricas nas bolas, sendo invertido o sinal do deslocamento respetivo.
O topo inverte o deslocamento vertical (dy) e as laterais invertem o
deslocamento horizontal (dx).
A largura total da raquete está dividida em três tipos de zonas. A zona central tem 40 pixels de largura e provoca reflexões simétricas.
As zonas dos extremos têm 10 pixels e as reflexões adicionam, ou
subtraem, 3 pixels ao deslocamento horizontal resultante.
As zonas intermédias têm 15 pixels de largura e as reflexões
adicionam, ou subtraem, 1 pixel ao deslocamento horizontal
resultante.
Em qualquer dos casos, o deslocamento horizontal (dx) resultante
deve estar sempre limitado ao intervalo [-6..6]. **/
fun main() {
    println("Starting game...")
    var game = Game(area = Area(400, 600), balls = emptyList(), racket = Racket(40, 10, 15))
    val dirX: IntRange = -6..6
    val dirY: Int = 4
    onStart {
        val window = Canvas(game.area.width, game.area.height, BLACK)
        drawRacket(window, game.racket)
        window.onTimeProgress(5000) {
            game = game.copy(balls = (game.balls + drawBall(window)) as List<Ball>)
        }

    }
    onFinish {
        println("Quitting...")
    }
}

/**Função que desenha as bolas**/
fun drawBall(canvas: Canvas) {
    val x = 0..400
    canvas.drawCircle(x.random(),600,7,YELLOW)
}
/**Função que desenha a raquete**/
fun drawRacket(canvas: Canvas, game: Racket) {
    canvas.drawRect(150, 560, 90, 10, WHITE)
}
