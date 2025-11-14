import pt.isel.canvas.*
import kotlin.random.Random

const val RACKET_WIDTH = 90
const val RACKET_HEIGHT = 10
const val RADIUS = 7
const val RACKET_POS_X = 155
const val RACKET_POS_Y = 560
const val MAX_RACKET_X = 400
const val MIN_RACKET_X = 0
const val GAME_UPDATE = 10
const val NEW_BALL_RATE = 5000
const val BALL_Y_SPAWN = 600
const val BALL_TEXT_Y = 595
const val BALL_TEXT_SIZE = 26
const val INIT_Y_DIR = -4
const val BALL_COLOR = 0xFF7F00
const val BACKGROUND_COLOR = BLACK
const val RACKET_ELER_COLOR = RED
const val RACKET_EMRM_COLOR = 0xFF7F00
const val RACKET_CENTER = YELLOW

fun main() {
    onStart {
        println("Starting...")
        val area = Area()
        val width = area.width
        val height = area.height
        val window = Canvas(width, height, BACKGROUND_COLOR)
        val windowX = 0..area.width
        val dirX = -6..6
        var game = Game(area = Area(), balls = emptyList(), racket = Racket(RACKET_POS_X, RACKET_POS_Y))
        window.onMouseMove { mouse ->
            game = game.copy(racket = moveRacket(game.racket, mouse.x))
            drawRacket(window, game.racket)
        }
        window.onTimeProgress(NEW_BALL_RATE) {
            val newBall = Ball(windowX.random(), BALL_Y_SPAWN, dirX.random(), INIT_Y_DIR)
            game = game.copy(balls = game.balls + newBall)
        }
        window.onTimeProgress(GAME_UPDATE) {
            game = updateGame(game)
            drawGame(window, game)
            window.drawText(width/2, BALL_TEXT_Y, "${game.balls.size}", 0xFF7F00, BALL_TEXT_SIZE)
        }
    }
    onFinish {
        println("Quitting...")
    }
}