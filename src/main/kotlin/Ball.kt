import pt.isel.canvas.*

/**Bolas do jogo**/
data class Ball(val x: Int, val y: Int, val dx: Int, val dy: Int, val radius: Int = 7)

/** Função que desenha a bola **/
fun drawBall(canvas: Canvas, ball: Ball) {
    canvas.drawCircle(ball.x, ball.y, ball.radius, CYAN)
}

/**Função da direção das bolas**/
fun ballDirections(ball: Ball, area: Area): Ball {
        val newDirectionX = if (ball.x - ball.radius <= 0 || ball.x + ball.radius >= area.width) -ball.dx else ball.dx
        val newDirectionY = if (ball.y - ball.radius <= 0) -ball.dy else ball.dy
        return ball.copy(x = ball.x + newDirectionX, y = ball.y + newDirectionY, dx = newDirectionX, dy = newDirectionY)
}
