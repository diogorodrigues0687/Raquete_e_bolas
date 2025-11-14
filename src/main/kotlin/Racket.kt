import pt.isel.canvas.*

/**Raquete do jogo**/
data class Racket(val x: Int, val y: Int, val width: Int = RACKET_WIDTH, val height: Int = RACKET_HEIGHT)

/**Função que desenha a raquete**/
fun drawRacket(canvas: Canvas, racket: Racket) {
    canvas.drawRect(racket.x , racket.y, RACKET_WIDTH, racket.height, RACKET_CENTER_COLOR)
    canvas.drawRect(racket.x, racket.y, RACKET_EWIDTH, racket.height - 5, RACKET_ELER_COLOR)
    canvas.drawRect(racket.x + 10, racket.y, RACKET_MWIDTH, racket.height-5, RACKET_EMRM_COLOR)
    canvas.drawRect(racket.x + 65, racket.y, RACKET_MWIDTH, racket.height-5, RACKET_EMRM_COLOR)
    canvas.drawRect(racket.x + 80, racket.y, RACKET_EWIDTH, racket.height-5, RACKET_ELER_COLOR)
}

/**Função que faz com que a raquete acompanhe o rato no eixo dos X**/
fun moveRacket(racket: Racket, cursor: Int): Racket {
    val cursorX = cursor - racket.width / 2
    val xLimit = cursorX.coerceIn(MIN_RACKET_X, MAX_RACKET_X - racket.width)
    return racket.copy(x = xLimit)
}

/**Função que modifica o deslocamento horizontal da bola conforme a colisão com as partes da raquete**/
fun RacketParts(ball: Ball, racket: Racket): Int {
    val pos = ball.x - racket.x
    val leftExtreme = 0..10
    val leftMid = 10 ..25
    val center = 25..65
    val rightMid = 65..80
    val rightExtreme = 80..90
    return when (pos) {
        in leftExtreme -> (ball.dx - 3).coerceIn(MIN_DX, MAX_DX)
        in leftMid -> (ball.dx - 1).coerceIn(MIN_DX, MAX_DX)
        in center ->  ball.dx
        in rightMid -> (ball.dx + 1).coerceIn(MIN_DX, MAX_DX)
        in rightExtreme -> (ball.dx + 3).coerceIn(MIN_DX, MAX_DX)
        else -> ball.dx
    }
}

/**Função da raquete refletora**/
fun reflectOnRacket(ball: Ball, racket: Racket): Ball =
    ball.copy(dy = -ball.dy, dx = RacketParts(ball, racket), y = racket.y - ball.radius - 1,) //Para evitar que a bola fique presa na raquete

