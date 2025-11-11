import pt.isel.canvas.*

/**Jogo**/
data class Game(val area: Area, val balls: List<Ball>, val racket: Racket)

/**Função que desenha todos os objetos do jogo **/
fun drawGame(canvas: Canvas, game: Game) {
    canvas.erase()
    for (b in game.balls)
        canvas.onTimeProgress(5000) {
            drawBall(canvas, b)
        }
    drawRacket(canvas, game.racket)
}
/**Função que atualiza o jogo**/
fun updateGame(game: Game): Game {
    val moveBalls = game.balls.map { ballDirections(it, game.area) }
    return game.copy(balls = moveBalls)
}