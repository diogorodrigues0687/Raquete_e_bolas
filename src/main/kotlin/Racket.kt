import pt.isel.canvas.*

/**Raquete do jogo**/
data class Racket(val x: Int, val y: Int, val width: Int = 90, val height: Int = 10)

/**Função que desenha a raquete**/
fun drawRacket(canvas: Canvas, racket: Racket) {
    canvas.drawRect(racket.x, racket.y, racket.width, racket.height, WHITE)
}

/**Função que faz com que a raquete acompanhe o rato no eixo dos X**/
fun moveRacket(racket: Racket, cursor: Int): Racket =
    racket.copy(x = cursor - racket.width / 2)