package mpdam.iset.guessnumberproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import mpdam.iset.guessnumberproject.ui.theme.Purple200
import mpdam.iset.guessnumberproject.ui.theme.Teal200


@Composable
fun winOrLoseDiaog(
    text:String,
    buttonText:String,
    mysteryNumber: Int,
    image: Painter,
resetGame:()-> Unit
    ) {
    Dialog(onDismissRequest = { /*TODO*/ }) {


        Column(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Teal200),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,

                )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "The mystery number is $mysteryNumber",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif

            )

            Image(

                painter = image,
                contentDescription = "Icon"
            )

            Button(
                onClick = resetGame,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple200,
                    contentColor = Color.White
                )

            ) {
                Text(text = buttonText, fontSize = 18.sp)
            }
        }
    }
}

@Preview
@Composable
fun WinDialogPrev() {
    winOrLoseDiaog(
        text="Congratulations \n You won" ,
        buttonText = "Play Again" ,
        mysteryNumber = 32 ,
        image = painterResource(R.drawable.ic_baseline_emoji_events_24) ,
        resetGame = {}
    )
}


@Preview
@Composable
fun LoseDialogPrev() {
    winOrLoseDiaog(
        text="Better luck next time" ,
        buttonText = "Try Again" ,
        mysteryNumber = 32 ,
        image = painterResource(R.drawable.ic_baseline_mood_bad_24),
        resetGame = {}
    )
}