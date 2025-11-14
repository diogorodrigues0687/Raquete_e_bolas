import pt.isel.canvas.*

/**Bolas do jogo**/
data class Ball(val x: Int, val y: Int, val dx: Int, val dy: Int, val radius: Int = RADIUS)

/** Função que desenha a bola **/
fun drawBall(canvas: Canvas, ball: Ball) {
    canvas.drawCircle(ball.x, ball.y, ball.radius, BALL_COLOR)
}

/**Função da direção das bolas**/
fun ballDirections(ball: Ball, area: Area, racket: Racket): Ball {
    val newDirectionX = if (ball.x - ball.radius <= 0 || ball.x + ball.radius >= area.width) -ball.dx else ball.dx
    val newDirectionY = if (ball.y - ball.radius <= 0) -ball.dy else ball.dy
    val ballOnRacket = ball.y + ball.radius >= racket.y && ball.y + ball.radius <= racket.y + racket.height && ball.x in racket.x .. racket.x + racket.width
    if (ballOnRacket) {
        return reflectOnRacket(ball, racket)
    }
    val newX = ball.x + newDirectionX
    val newY = ball.y + newDirectionY
    val dxLimit = newDirectionX.coerceIn(MIN_DX, MAX_DX)
    return ball.copy(x = newX, y = newY, dx = dxLimit, dy = newDirectionY)
}
