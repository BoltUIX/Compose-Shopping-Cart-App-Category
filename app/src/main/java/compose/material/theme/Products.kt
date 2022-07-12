package compose.material.theme

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class TouchState {
    Touched, NotTouched
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductsCard(products: ProductModel) {

    var currentState: TouchState by remember { mutableStateOf(TouchState.NotTouched) }

    val transition = updateTransition(targetState = currentState, label = "animation")


    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) { state ->
        if (state == TouchState.Touched) {
            1.3f
        } else {
            1f
        }
    }

    val colorAlpha: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) { state ->
        if (state == TouchState.Touched) {
            1f
        } else {
            0.2f
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .pointerInteropFilter {
                    currentState = when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            TouchState.Touched
                        }
                        else -> {
                            TouchState.NotTouched
                        }
                    }
                    true
                },
            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp, topEnd = 5.dp, bottomStart = 5.dp),
            backgroundColor = products.color.copy(alpha = 0.2f) ,
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                products.color.copy(alpha = 0.2f),
                                products.color.copy(alpha = 0.2f),
                                products.color.copy(alpha = colorAlpha),
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 32.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(text = products.title,
                        letterSpacing =1.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))

                    AnimatedVisibility(visible = currentState == TouchState.NotTouched) {
                        Text(
                            text = products.price,
                            letterSpacing =2.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    AnimatedVisibility(visible = currentState == TouchState.Touched) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "Buy Now",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                                letterSpacing =2.sp
                            )
                            Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = null, Modifier.size(20.dp))
                        }
                    }
                }
            }
        }

        Image(
            painter = painterResource(id = products.image),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height((110 * scale).dp)
                .padding(end = 32.dp)
                .align(Alignment.BottomEnd)
        )
    }
}