import pt.isel.canvas.*
import kotlin.random.Random

/** Cores adicionais: Laranja: 0xFF7F00, Cinzento: 0x222222**/

/**Variáveis do jogo**/
const val RACKET_WIDTH = 90               //Largura da raquete
const val RACKET_EWIDTH = 10              //Largura das extremidades da raquete
const val RACKET_MWIDTH = 15              //Largura das partes intermédias da raquete
const val RACKET_HEIGHT = 10              //Altura da raquete
const val RACKET_POS_X = 155              //Posição da raquete em X
const val RACKET_POS_Y = 560              //Posição da raquete em Y
const val MAX_RACKET_X = 400              //Máximo onde a raquete pode se deslocar no eixo dos X
const val MIN_RACKET_X = 0                //Mínimo onde a raquete pode se deslocar no eixo dos X
const val GAME_UPDATE = 10                //Taxa de atualização do jogo
const val RADIUS = 7                      //Raio da bola
const val NEW_BALL_RATE = 5000            //Frequência da invocação das bolas
const val BALL_Y_SPAWN = 600              //Posição da invocação das bolas no eixo dos Y
const val BALL_TEXT_Y = 595               //Posição do texto do número de bolas no eixo dos Y
const val BALL_TEXT_SIZE = 26             //Tamanho do texto do número de bolas
const val TEXT_COLOR = 0xFF7F00           //Cor do texto do número de bolas
const val INIT_Y_DIR = -4                 //Direção vertical inicial das bolas
const val BALL_COLOR = 0xFF7F00           //Cor das bolas
const val BACKGROUND_COLOR = 0x222222     //Cor da janela
const val RACKET_ELER_COLOR = RED         //Cor das extremidades da raquete
const val RACKET_EMRM_COLOR = 0xFF7F00    //Cor das partes intermédias da raquete
const val RACKET_CENTER_COLOR = YELLOW    //Cor da parte central da raquete
const val MAX_DX = 6                      //Limite máximo do deslocamento horizontal
const val MIN_DX = -6                     //Limite mínimo do deslocamento horizontal

val area = Area()                         //Area do jogo
val width = area.width                    //Largura do jogo
val height = area.height                  //Altura do jogo

/**Função príncipal**/
fun main() {
    onStart {
        println("Starting...")
        val window = Canvas(width, height, BACKGROUND_COLOR)
        val windowX = RADIUS + 1..area.width - RADIUS //Para evitar que a bola fique presa nas laterais
        val dirX = MIN_DX..MAX_DX
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
            window.drawText(width/2, BALL_TEXT_Y, "${game.balls.size}", TEXT_COLOR, BALL_TEXT_SIZE)
        }
    }
    onFinish {
        println("Quitting...")
    }
}