package com.example.financetracker.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.financetracker.R
import com.example.financetracker.data.models.Expenditure
import com.example.financetracker.data.models.Income

@ExperimentalAnimationApi
@Composable
fun CreateScreen(
    onBackPressed: () -> Unit,
    onSubmit: (Income) -> Unit,
) {
    Scaffold(
        topBar = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val (backIcon, topTitle) = createRefs()


                //Back Icon
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xffFAF6F6),
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = onBackPressed
                        )
                        .constrainAs(backIcon) {
                            absoluteLeft.linkTo(parent.absoluteLeft)
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(10.dp)
                    )
                }
                Text(
                    text = "Create",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(20.dp)
                        .constrainAs(topTitle) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                )
            }
        }
    ) {

        //state of all three inputs.
        val (textCategory, setTextCategory) = remember { mutableStateOf("") }
        val (textAmount, setTextAmount) = remember { mutableStateOf("") }
        val (inputColor, setInputColor) = remember { mutableStateOf(0L) }

        val submit = {
            onSubmit(Income(textCategory, textAmount, inputColor))
        }


        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .padding(30.dp)
        ) {


            CategoryInputField(
                labelText = "Category",
                editText = textCategory,
                setEditText = setTextCategory
            )

            Spacer(modifier = Modifier.height(30.dp))

            AmountInputField(
                labelText = "Amount",
                editText = textAmount,
                setEditText = setTextAmount
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Color",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 5.dp, bottom = 5.dp)
            )

            Surface(
                color = Color(0xffFAF6F6),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(10.dp)
                ) {

                    for (colorValue in SelectionColors) {
                        Canvas(
                            modifier = Modifier
                                .padding(start = 5.dp, end = 5.dp)
                                .size(40.dp)
                                .align(Alignment.CenterVertically)
                                .clip(CircleShape)
                                .clickable(
                                    onClick = {
                                        setInputColor(colorValue)
                                        Log.d("Color", colorValue.toString())
                                    }
                                )
                        ) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height
                            drawCircle(
                                color = Color(colorValue),
                                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                                radius = size.minDimension / 2
                            )

                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = submit
                    ) {
                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                }


        }


    }


@ExperimentalAnimationApi
@Composable
fun CategoryInputField(
    labelText: String,
    editText: String,
    setEditText: (String) -> Unit,
) {
    val fieldBackgroundColor = Color(0xffFAF6F6)
    val focusedBorderColor = Color(0xff946FFF)
    val unfocusedBorderColor = Color(0xffE5E5E5)

    Row {
        Text(
            text = labelText,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 5.dp, bottom = 5.dp)
        )
    }

    OutlinedTextField(
        value = editText,
        onValueChange = {
            setEditText(it)
        },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = fieldBackgroundColor,
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = fieldBackgroundColor, shape = RoundedCornerShape(15.dp)),

    )
}

@ExperimentalAnimationApi
@Composable
fun AmountInputField(
    labelText: String,
    editText: String,
    setEditText: (String) -> Unit,
) {
    val fieldBackgroundColor = Color(0xffFAF6F6)
    val focusedBorderColor = Color(0xff946FFF)
    val unfocusedBorderColor = Color(0xffE5E5E5)

    //local state for type checking.
    val warningTextVisibility = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 5.dp, bottom = 5.dp)
                .weight(1f)
        )

        AnimatedVisibility(
            visible = warningTextVisibility.value,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = "Put a number",
                style = MaterialTheme.typography.body2,
                color = Color.Red,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(start = 5.dp, bottom = 5.dp)
            )
        }
    }

    OutlinedTextField(
        value = editText,
        onValueChange = {
            if (it.toDoubleOrNull() == null && it != "") {
                setEditText(it)
                warningTextVisibility.value = true
            } else {
                setEditText(it)
                warningTextVisibility.value = false
            }
        },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = fieldBackgroundColor,
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = fieldBackgroundColor, shape = RoundedCornerShape(15.dp)),
        placeholder = {
            Text(
                "Rs",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start
            )
        }
    )
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun CreateScreenPreview() {
    CreateScreen(onBackPressed = {

    }) {

    }
}

private val SelectionColors = listOf(
    0xffFF0000,
    0xff6406FC,
    0xff32F952,
    0xffEBFC2F,
    0xffFF00E5,
    0xff393D95,
    0xff32F952
)