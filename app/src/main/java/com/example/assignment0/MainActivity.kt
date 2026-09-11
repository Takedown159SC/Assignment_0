package com.example.assignment0

/*
LIST OF RESOURCES:
https://medium.com/@appdevinsights/what-is-state-hoisting-in-jetpack-compose-88946957584a (state managment)
https://developer.android.com/ (Modifier API, Compose Architecture

 */

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment0.ui.theme.Assignment0Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var counter by remember {mutableIntStateOf(value = 0) }
            //messageState: Yes = 1, 0 = no, else = "Should we go"
            var messageState by remember { mutableIntStateOf(value = 2) }

            Assignment0Theme {
                Box(modifier = Modifier.fillMaxSize()) //main parent composable for refrence
                {
                    /*
                    Handle all modifiers on this level
                     */
                    StudentInfo(
                        textColor = Color.Blue,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = 30.dp)
                    )
                    Main_message(
                        messageState = messageState,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    Buttons(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.Center)
                            .offset(y = 40.dp),

                        //button events:
                        b1Press = {
                            counter++
                            messageState = if (Random.nextFloat() > 0.5) { //50% chance of saying yes
                                1
                            } else{
                                0
                            }
                        },
                        b2Press = {
                            counter++
                            messageState = if (Random.nextFloat() > 0.75) {//25% chance of saying yes
                                1
                            } else{
                                0
                            }
                        },
                        b3Press = {
                            counter++
                            messageState = if (Random.nextFloat() > 0.9) {//10% chance of saying yes
                                1
                            } else{
                                0
                            }
                        },

                    )
                    ClickCounter(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.Center)
                            .offset(y = 80.dp),
                        counter = counter //displays counter
                    )
                }
            }
        }
    }
}

@Composable
fun Main_message(messageState: Int, modifier: Modifier = Modifier){
    var message = "Should we go?"
    when (messageState){
        0 -> message = "No"
        1 -> message = "Yes"
    }
    Text(
        text = message,
        modifier = modifier
    )
}

@Composable
fun Buttons(modifier: Modifier = Modifier,
            b1Press: () -> Unit,
            b2Press: () -> Unit,
            b3Press: () -> Unit
){
    Row(modifier = modifier){// modifiers are defined in parent box

        Button(
            onClick =  {
                b1Press()
            }
        ){
            Text("Ok!")
        }
        Button(
            onClick = {
                b2Press()
            }
        ){
            Text("Meh")
        }
        Button(
            onClick = {
                b3Press()

            }
        ){
            Text("Nah")
        }
    }

}

@Composable
fun ClickCounter(modifier: Modifier = Modifier, counter: Int){
    Row(modifier = modifier) {// modifiers are defined in parent box
        Text(
            text = "Click counter: $counter"
        )
    }
}

@Composable
fun StudentInfo(textColor: Color, modifier: Modifier){
    Row(modifier = modifier) {
        Text(
            color = textColor,
            text = "ID:1743560"
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            color = textColor,
            text = "CCID:templado"
        )
    }
}