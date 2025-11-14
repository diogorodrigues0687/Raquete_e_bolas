import pt.isel.canvas.*

/**Jogo**/
data class Game(val area: Area, val balls: List<Ball>, val racket: Racket)

/**Função que desenha todos os objetos do jogo **/
fun drawGame(canvas: Canvas, game: Game) {
    canvas.erase()
    canvas.drawImage("WindowsXP.jpg",0,0,width,height) //Background personalizado, comentar esta linha se necessário
    for (b in game.balls)
        drawBall(canvas, b)
    drawRacket(canvas, game.racket)
}
/**Função que atualiza o jogo**/
fun updateGame(game: Game): Game {
    val moveBalls = game.balls.map{ ballDirections(it, game.area, game.racket)}.filter { it.y - it.radius <= game.area.height }
    return game.copy(balls = moveBalls)
}