package com.jetpack.productdetaildesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.jetpack.productdetaildesign.ui.theme.PrimaryColor
import com.jetpack.productdetaildesign.ui.theme.ProductDetailDesignTheme
import com.jetpack.productdetaildesign.ui.theme.ProductOptionColor2
import com.jetpack.productdetaildesign.ui.theme.ProductOptionColor3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductDetailDesignTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProductDetailDesign()
                }
            }
        }
    }
}

@Composable
fun ProductDetailDesign() {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                title = {
                    Text(
                        text = ""
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back Arrow",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search Icon",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.cart),
                            contentDescription = "Cart Icon",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = PrimaryColor
            )
        }
    ) {
        HomeContent()
    }
}

@Composable
fun HomeContent(
    product: Product = mProduct
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor),
        constraintSet = ConstraintSet(
            """
                {
                  bgCard: {
                    bottom: ['parent', 'bottom']
                  },
                  productImage: {
                    top: ['bgCard', 'top'],
                    bottom: ['bgCard', 'top'],
                    right: ['bgCard', 'right']
                  },
                  productCategory: {
                    top: ['parent', 'top'],
                    left: ['parent', 'left']
                  },
                  productTitle: {
                    top: ['productCategory', 'bottom'],
                    left: ['parent', 'left']
                  },
                  productPrice: {
                    bottom: ['bgCard', 'top'],
                    left: ['parent', 'left']
                  },
                  productPriceLabel: {
                    bottom: ['productPrice', 'top'],
                    left: ['parent', 'left']
                  }
                }
            """.trimIndent()
        )
    ) {
        Card(
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            elevation = 0.dp,
            modifier = Modifier
                .layoutId("bgCard")
                .height(444.dp)
                .fillMaxWidth()
        ) {
            ConstraintLayout(
                constraintSet = ConstraintSet(
                    """
                        {
                          txtColor: {
                            top: ['parent', 'top'],
                            left: ['parent', 'left']
                          },
                          txtSize: {
                            top: ['txtColor', 'top'],
                            left: ['txtColor', 'right']
                          },
                          txtSizeValue: {
                            top: ['txtSize', 'bottom'],
                            left: ['txtSize', 'left']
                          },
                          txtDescription: {
                            top: ['txtSizeValue', 'bottom'],
                            left: ['parent', 'left'],
                            right: ['parent', 'right']
                          },
                          btnMinus: {
                            top: ['txtDescription', 'bottom'],
                            left: ['parent', 'left']
                          },
                          txtQtd: {
                            top: ['btnMinus', 'top'],
                            bottom: ['btnMinus', 'bottom'],
                            left: ['btnMinus', 'right']
                          },
                          btnPlus: {
                            top: ['txtDescription', 'bottom'],
                            left: ['txtQtd', 'right']
                          },
                          btnHeart: {
                            top: ['btnPlus', 'top'],
                            bottom: ['btnPlus', 'bottom'],
                            right: ['parent', 'right']
                          },
                          btnAddtoCart: {
                            top: ['btnMinus', 'bottom'],
                            left: ['parent', 'left']
                          },
                          btnBuy: {
                            top: ['btnMinus', 'bottom'],
                            left: ['btnAddtoCart', 'right'],
                            right: ['parent', 'right']
                          },
                          color1: {
                            top: ['txtColor', 'bottom'],
                            left: ['parent', 'left']
                          },
                          color2: {
                            top: ['txtColor', 'bottom'],
                            left: ['color1', 'right']
                          },
                          color3: {
                            top: ['txtColor', 'bottom'],
                            left: ['color2', 'right']
                          }
                        }
                    """.trimIndent()
                ),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                CardContent(product)
            }
        }

        Text(
            text = "Aristocratic Hand Bag",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .layoutId("productCategory")
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        Text(
            text = product.title,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .layoutId("productTitle")
                .padding(start = 16.dp)
        )

        Image(
            painter = painterResource(id = product.img),
            contentDescription = "Product Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .layoutId("productImage")
                .padding(start = 200.dp)
                .width(237.dp)
                .height(261.dp)
                .padding(end = 32.dp, bottom = 62.dp)
        )

        Text(
            text = "Price",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .layoutId("productPriceLabel")
                .padding(start = 16.dp)
        )

        Text(
            text = product.price,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .layoutId("productPrice")
                .padding(start = 16.dp, bottom = 8.dp, top = 8.dp)
        )
    }
}

@Composable
fun CardContent(product: Product) {
    Text(
        text = "Color",
        color = Color.Gray,
        modifier = Modifier
            .layoutId("txtColor")
            .padding(top = 64.dp)
    )

    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .layoutId("color1")
            .padding(top = 8.dp)
            .width(15.dp)
            .height(15.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {

    }

    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .layoutId("color2")
            .padding(top = 8.dp, start = 20.dp)
            .width(15.dp)
            .height(15.dp),
        colors = ButtonDefaults.buttonColors(ProductOptionColor2),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {

    }

    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .layoutId("color3")
            .padding(top = 8.dp, start = 40.dp)
            .width(15.dp)
            .height(15.dp),
        colors = ButtonDefaults.buttonColors(ProductOptionColor3),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {

    }

    Text(
        text = "Size",
        modifier = Modifier
            .layoutId("txtSize")
            .padding(top = 64.dp, start = 90.dp),
        color = Color.Gray
    )

    Text(
        text = product.size,
        modifier = Modifier
            .layoutId("txtSizeValue")
            .padding(start = 90.dp, top = 8.dp, bottom = 16.dp),
        color = Color.DarkGray
    )

    Text(
        text = product.description,
        modifier = Modifier
            .layoutId("txtDescription"),
        color = Color.Gray
    )

    OutlinedButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .layoutId("btnMinus")
            .padding(top = 32.dp, end = 16.dp)
    ) {
        Text(
            text = "-",
            fontSize = 23.sp,
            color = Color.Black
        )
    }

    Text(
        text = "01",
        fontSize = 23.sp,
        color = Color.DarkGray,
        modifier = Modifier
            .layoutId("txtQtd")
            .padding(top = 32.dp, start = 80.dp)
    )

    OutlinedButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .layoutId("btnPlus")
            .padding(top = 32.dp, start = 120.dp)
    ) {
        Text(
            text = "+",
            fontSize = 23.sp,
            color = Color.Black
        )
    }

    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = colorResource(id = R.color.heart_bg_color),
        modifier = Modifier
            .layoutId("btnHeart")
            .padding(top = 32.dp, start = 320.dp),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = "Heart",
            modifier = Modifier.size(20.dp)
        )
    }

    OutlinedButton(
        onClick = { /*TODO*/ },
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(id = R.color.primary_color)
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .layoutId("btnAddtoCart")
            .padding(top = 32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.add_to_cart),
            contentDescription = "Add Cart",
            modifier = Modifier.size(25.dp)
        )
    }

    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .layoutId("btnBuy")
            .padding(top = 32.dp, start = 90.dp)
            .width(275.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = "BUY NOW",
            fontSize = 23.sp,
            color = Color.White
        )
    }
}






















