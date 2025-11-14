import pt.isel.canvas.*

/**Raquete do jogo**/
data class Racket(val x: Int, val y: Int, val width: Int = RACKET_WIDTH, val height: Int = RACKET_HEIGHT)

/**Função que desenha a raquete**/
fun drawRacket(canvas: Canvas, racket: Racket) {
    canvas.drawRect(racket.x, racket.y, racket.width, racket.height, 0xFF7F00)
}

/**Função que faz com que a raquete acompanhe o rato no eixo dos X**/
fun moveRacket(racket: Racket, cursor: Int): Racket =
    racket.copy(x = cursor - racket.width / 2)

/**Função que modifica dx da bola conforme a colisão com as partes da raquete**/
fun RacketParts(ball: Ball, racket: Racket): Int {
    val pos = ball.x - racket.x
    val leftExtreme = 0..10
    val leftMid = 10 ..25
    val center = 25..65
    val rightMid = 65..80
    val rightExtreme = 80..90

    return when (pos) {
        in leftExtreme -> (ball.dx - 3).coerceIn(-6, 6)
        in leftMid     -> (ball.dx - 1).coerceIn(-6, 6)
        in center      ->  ball.dx
        in rightMid    -> (ball.dx + 1).coerceIn(-6, 6)
        in rightExtreme-> (ball.dx + 3).coerceIn(-6, 6)
        else -> ball.dx
    }
}

/**Função da raquete refletora**/
fun reflectOnRacket(ball: Ball, racket: Racket): Ball =
    ball.copy(dy = -ball.dy, dx = RacketParts(ball, racket), y = racket.y - ball.radius - 1,)

