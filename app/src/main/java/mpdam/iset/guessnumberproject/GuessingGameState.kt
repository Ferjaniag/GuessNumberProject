package mpdam.iset.guessnumberproject

data class GuessingGameState (

    val userNumber:String = "" ,
    val noOfGuessLeft :  Int = 5 ,
    val guessedNumberList : List<Int> = emptyList() ,
val mysteryNumber: Int = (1..99).random() ,
    val hintDescription: String = "Guess \n the mystery number \n between 0 and 100" ,
    val gameStage:GameStage=GameStage.PLAYING


)

enum class GameStage {
    WON,
    LOSE,
    PLAYING
}

enum class GameLevel {
    BEGINNER,
    ADVANCED
}
