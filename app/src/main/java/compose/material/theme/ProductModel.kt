package compose.material.theme

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class ProductModel(
    val title: String,
    val price: String,
    @DrawableRes
    val image: Int,
    val color: Color
)

fun getData(): List<ProductModel> {
    return listOf(
        ProductModel("Beats Solo 1","$223.00", R.drawable.headset4, Color(0xFFFF1A9B)),
        ProductModel("Beats Solo 2","$223.00", R.drawable.headset1, Color(0xFF5C00FF)),
        ProductModel("Beats Solo 3","$223.00", R.drawable.headset2, Color(0xFFFF0000)),
        ProductModel("Beats Solo 4","$223.00", R.drawable.headset3, Color(0xFF447ACE)),
        ProductModel("Beats Solo 5","$223.00", R.drawable.headset4, Color(0xFFFF1A9B)),
        ProductModel("Beats Solo 6","$223.00", R.drawable.headset1, Color(0xFF5C00FF)),
        ProductModel("Beats Solo 7","$223.00", R.drawable.headset2, Color(0xFFFF0000)),
        ProductModel("Beats Solo 8","$223.00", R.drawable.headset3, Color(0xFF447ACE)),
    )
}
