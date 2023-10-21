package mpdam.iset.guessnumberproject

import android.content.Context
import android.util.Log

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.flow.update


class MainViewModel:ViewModel() {

    private val _state= MutableStateFlow(GuessingGameState())

    val state=_state.asStateFlow()

    fun updateTextField(userNo : String) {
        _state.update { it.copy(userNumber = userNo) }
    }

    fun resetGame() {
        _state.value=GuessingGameState()
    }

    fun onUserInput(userNumber: String, context: Context) {
        val userNumberInInt = try {
            userNumber.toInt()
        }  catch (e:java.lang.Exception) {
            0
        }


        val currentState = state.value
        val newGuessLeft= currentState.noOfGuessLeft - 1

      Log.i("mystery num", currentState.mysteryNumber.toString())
        Log.i("usernumber num", userNumberInInt.toString())

val newGameStage = if ( userNumberInInt == currentState.mysteryNumber )  {
        GameStage.WON
    } else if (newGuessLeft ==0) {
        GameStage.LOSE
    } else {
        GameStage.PLAYING
}

        val newHintDescription = if (userNumberInInt > currentState.mysteryNumber) {
            "Hint \n You are guessing a bigger number than the mystery number."
        } else if (userNumberInInt < currentState.mysteryNumber) {
            "Hint \n You are guessing a smaller number than the mystery number."
        } else {
            ""
        }

        Log.i("hint description",newHintDescription)
        val newGuessNoList = currentState.guessedNumberList.plus(userNumberInInt)
        _state.update {
            it.copy(
                userNumber="" ,
                noOfGuessLeft =  newGuessLeft ,
                guessedNumberList = newGuessNoList ,
                hintDescription = newHintDescription ,
                gameStage = newGameStage
            )
        }


    }


}