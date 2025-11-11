import pt.isel.canvas.*

/**Bolas do jogo**/
data class Ball(val x: Int, val y: Int, val dx: Int, val dy: Int, val radius: Int = 7)

/** Função que desenha a bola **/
fun drawBall(canvas: Canvas, ball: Ball) {
    canvas.drawCircle(ball.x, ball.y, ball.radius, YELLOW)
}

/** Função que move a bola com base na direção do deslocamento **/
fun moveBall(ball: Ball): Ball =
    ball.copy(x = ball.x + ball.dx, y = ball.y + ball.dy)

/**Função da direção das bolas**/
fun ballDirections(ball: Ball, area: Area): Ball {
    var ballX = ball.x + ball.dx
    var ballY = ball.y + ball.dy
    var newDirectionX = ball.dx
    var newDirectionY = ball.dy

    if (ballX - ball.radius <= 0 || ballX + ball.radius >= area.width)
        newDirectionX = -newDirectionX

    if (ballY - ball.radius <= 0)
        newDirectionY = -newDirectionY

    return ball.copy(x = ballX, y = ballY, dx = newDirectionX, dy = newDirectionY)
}