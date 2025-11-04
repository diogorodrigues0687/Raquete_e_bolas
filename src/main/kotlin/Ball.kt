import pt.isel.canvas.*
import javax.swing.Spring.height

/** Bolas do jogo**/
data class Ball(val x: Int, val y: Int, val radius: Int, val dx: Int, val dy: Int, val color: Int = YELLOW)

/** Função do movimento das bolas na horizontal**/
fun moveX(ball: Ball): Ball {
    val  newXDirection = when {
        ball.x + ball.radius > 400 -> -ball.dx
        ball.x - ball.radius < 0 -> -ball.dx
        else -> ball.dx
    }
    return Ball(ball.x + newXDirection, ball.y, ball.radius, newXDirection, ball.dy, ball.color)
}

/**Função do movimento das bolas na vertical**/
fun moveY(ball: Ball): Ball {
    val  newYDirection = when {
        ball.y + ball.radius > 600 -> -ball.dy
        ball.y - ball.radius < 0 -> -ball.dy
        else -> ball.dy
    }
    return Ball(ball.x, ball.y + newYDirection, ball.radius, ball.dx, newYDirection, ball.color)
}