import pt.isel.canvas.*
import kotlin.random.Random

const val RACKET_WIDTH = 90
const val RACKET_HEIGHT = 10
const val RADIUS = 7

fun main() {
    onStart {
        println("Starting...")
        val area = Area()
        val WIDTH = area.width
        val HEIGHT = area.height
        val window = Canvas(WIDTH, HEIGHT, BLACK)
        val windowX = 0..area.width
        val dirX = -6..6
        var game = Game(area = Area(), balls = emptyList(), racket = Racket(x = 155, y = 560))
        window.onMouseMove { mouse ->
            game = game.copy(racket = moveRacket(game.racket, mouse.x))
            drawRacket(window, game.racket)
        }
        window.onTimeProgress(5000) {
            val newBall = Ball(windowX.random(), 595, dirX.random(), -4)
            game = game.copy(balls = game.balls + newBall)
        }
        window.onTimeProgress(10) {
            game = updateGame(game)
            drawGame(window, game)
            window.drawText(WIDTH/2, 300, "Avalia :)", 0xFF7F00, 40)
            window.drawText(WIDTH/2, 595, "${game.balls.size}", 0xFF7F00, 26)
        }

    }
    onFinish {
        println("Quitting...")
    }
}