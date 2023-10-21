package mpdam.iset.guessnumberproject
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import mpdam.iset.guessnumberproject.ui.theme.Purple200
import mpdam.iset.guessnumberproject.ui.theme.Teal200

@Composable
fun GuessingGameScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    when (state.gameStage) {
        GameStage.PLAYING -> {
ScreenContent(state = state, onValueChange = { viewModel.updateTextField(userNo = it)
} ,
    onEnterButtonClicked = {

        viewModel.onUserInput(state.userNumber,
            context= context
        )
    }

    )

        }
        GameStage.WON -> {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Purple200)
            ) {
                winOrLoseDiaog(
                    text="Congratulations \n You won" ,
                    buttonText = "Play Again" ,
                    mysteryNumber = state.mysteryNumber ,
                    image = painterResource(R.drawable.ic_baseline_emoji_events_24) ,
                    resetGame = {viewModel.resetGame()}
                )
            }
        }
        GameStage.LOSE -> {
            Column(
            modifier= Modifier
                .fillMaxSize()
                .background(Purple200)
        ) {
                winOrLoseDiaog(
                    text="Better luck next time" ,
                    buttonText = "Try Again" ,
                    mysteryNumber = state.mysteryNumber ,
                    image = painterResource(R.drawable.ic_baseline_mood_bad_24),
                    resetGame = {viewModel.resetGame()}
                )
        }
        }

    }

}

@Composable
fun ScreenContent(
    state:GuessingGameState ,
    onValueChange:(String)-> Unit ,
    onEnterButtonClicked :()->Unit
) {

    val focusRequester= remember { FocusRequester() }
    LaunchedEffect(key1 = Unit ) {
        delay (500)
        focusRequester.requestFocus()
    }
    Column (
        modifier= Modifier
            .fillMaxSize()
            .background(Purple200)
            .padding(20.dp)

    ) {

        Text (

            text= buildAnnotatedString {
                append("No of guess left : ")
                withStyle(style = SpanStyle( color= Color.Black)) {
                    append("${state.noOfGuessLeft}")
                }
            } ,

            color = Teal200 ,
            fontSize= 18.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text (
            text="Historcal" ,
            color=Teal200 ,
            fontSize = 30.sp ,
            textAlign=TextAlign.Center ,
            fontStyle = FontStyle.Italic ,
            modifier = Modifier.padding(end= 20.dp)

        )
        Row(modifier = Modifier

            .height(250.dp) ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            state.guessedNumberList.forEach {number ->

                Text (
                    text="$number" ,
                    color=Teal200 ,
                    fontSize = 30.sp ,
                    modifier = Modifier.padding(end= 20.dp)

                )

            }
        }

        Text (
            text="${state.hintDescription}" ,
            color= Color.Black ,
            fontSize = 25.sp ,
            textAlign=TextAlign.Center ,
            lineHeight = 30.sp ,
            fontStyle = FontStyle.Italic ,

            modifier = Modifier.fillMaxWidth() ,
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .focusRequester(focusRequester),
            value =state.userNumber ,
            onValueChange = onValueChange ,
            textStyle= TextStyle(
                textAlign = TextAlign.Center ,
                fontSize = 48.sp ,
            ) ,
            colors= TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor=Color.White ,
                focusedBorderColor = Color.Transparent ,


                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number ,
                imeAction = ImeAction.Done
            ) ,
            keyboardActions = KeyboardActions(
                onDone = { onEnterButtonClicked() }
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .align(Alignment.End)
                .padding(40.dp),
            onClick = {
              onEnterButtonClicked()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Teal200 ,
                contentColor = Color.Black,
            )
        ) {
            Text(text="Enter", fontSize = 18.sp)

        }


    }
}



@Preview
@Composable

fun Preview() {

  //  GuessingGameScreen()
}