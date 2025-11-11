import pt.isel.canvas.*
import kotlin.random.Random

fun main() {
    onStart {
        println("Starting...")
        val window = Canvas(400, 600, BLACK)
        val windowX = 0..400
        val dirX = -6..6
        var game = Game(area = Area(), balls = listOf(Ball(windowX.random(), 595, dirX.random(), -4)), racket = Racket(x = 155, y = 560))
        drawGame(window, game)
        window.onMouseMove { mouse ->
            game = game.copy(racket = moveRacket(game.racket, mouse.x))
            drawGame(window, game)
        }
        window.onTimeProgress(10) {
            game = updateGame(game)
            drawGame(window, game)
        }
    }
    onFinish {
        println("Quitting...")
    }
}