package com.example.financetracker.ui.screens.components

/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val DividerLengthInDegrees = 1.8f

/**
 * A donut chart that animates when loaded.
 */
@Composable
fun AnimatedCircle(
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {

    /*
    1. AngleOffset is the continuously changing angle.
    2. stroke the line having some width.
    3. currentState is the currentState of the stroke. It has two properties initialState and targetState.
    4. DividerLength in degrees is the white space between the stroke in degrees.
    5. transition is the variable that holds the currentState and the updateTransition is the method to update that transition
    6.
     */

    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }
    val stroke = with(LocalDensity.current) { Stroke(5.dp.toPx())}
    val transition = updateTransition(currentState, label = "")
    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 100,
                durationMillis = 700,
                easing = FastOutSlowInEasing
            )
        }, label = "transition animation"
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            //initial value of the angle for a particular proportion.
            0f
        } else {
            //final value of the angle
            360f
        }
    }

    //start angle also animates
    val shift by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = CubicBezierEasing(0f, 0.75f, 0.35f, 0.85f)
            )
        }, label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            30f
        }
    }

    //Here we build the individual arcs and change it's different values through animateFloat method.
    Canvas(modifier) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.width - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        var startAngle = shift - 90f
        proportions.forEachIndexed { index, proportion ->
            val sweep = proportion * angleOffset
            drawArc(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = sweep - DividerLengthInDegrees,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
            //update the start angle before leaving
            //TODO understand more clearly this.(experimentation)
            startAngle += sweep
        }
    }
}

@Preview
@Composable
fun AnimatedCircleProgressPreview() {
    AnimatedCircle(colors = listOf(), proportions = listOf())
}

private enum class AnimatedCircleProgress { START, END }
