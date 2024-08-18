package com.example.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Samsung()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Samsung() {
    var text by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.black_))
            .fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            // Верхняя строка с RV и Text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, top = 80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RV(24)
                Text(
                    text = ":",
                    style = TextStyle(color = colorResource(id = R.color.white_), fontSize = 80.sp, fontWeight = FontWeight.Light),
                    modifier = Modifier.padding(top = 114.dp)
                )
                RV(60)
            }

            Spacer(modifier = Modifier.height(16.dp))  // Отступ между строкой и картой

            // Карта с текстом и изображением
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.dark_gray))
                        .padding(16.dp)  // Внутренний отступ для Column
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = "Каждый",
                            style = TextStyle(
                                color = colorResource(R.color.white_),
                                fontSize = 20.sp
                            )
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = "Calendar",
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))  // Отступ между строкой и LazyRow

                    LazyRow(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(R.color.dark_gray))
                    ) {
                        itemsIndexed(arrayOf("П", "В", "С", "Ч", "П", "С", "В")) { index, item ->
                            StyledText(text = item)
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))  // Отступ между LazyRow и TextField

                    TextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
                        textStyle = TextStyle(
                            color = colorResource(R.color.white_),
                            fontSize = 18.sp
                        ),
                        placeholder = {
                            Text(
                                text = "Название будильника",
                                style = TextStyle(
                                    color = colorResource(R.color.my),
                                    fontSize = 18.sp
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(R.color.dark_gray)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = colorResource(R.color.dark_gray),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = colorResource(R.color.white_)
                        )
                    )

                    MySwitch(label = "Звук будильника", category = "Homecoming", checked = false)

                    MySwitch(label = "Вибрации", category = "Basic call", checked = false)

                    MySwitch(label = "Пауза", category = "5 минут, Бесконечно", checked = false)

                }
            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth()
            ) {
                Text(
                    text = "Отмена",
                    color = colorResource(id = R.color.blue),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = "Сохранить",
                    color = colorResource(id = R.color.blue),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun MySwitch(
    label: String,
    category: String,
    checked: Boolean,
) {
    var checkedSwitch by remember { mutableStateOf(checked) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.dark_gray))
    ) {

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {  // Добавлено fillMaxWidth для корректного размещения элементов внутри Column
            Text(
                text = label,
                style = TextStyle(color = colorResource(R.color.white_), fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category,
                style = TextStyle(color = colorResource(R.color.blue), fontSize = 14.sp)
            )
        }

        Switch(
            modifier = Modifier.align(Alignment.CenterEnd),
            checked = checkedSwitch,
            onCheckedChange = { checkedSwitch = it },
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = colorResource(R.color.white), // Цвет кнопки при неактивном состоянии
                checkedTrackColor = colorResource(R.color.blue), // Прозрачный цвет полосы для активного
                uncheckedTrackColor = colorResource(R.color.dark_gray) // Прозрачный цвет полосы для неактивного состояния
            )
        )
    }
}


@Composable
fun StyledText(text: String) {
    var isClicked by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp) // Устанавливаем размер Box для круга
            .clip(CircleShape) // Округляем до формы круга
            .background(Color.Transparent) // Прозрачный фон
            .border(
                width = if (isClicked) 2.dp else 0.dp, // Толщина рамки
                color = if (isClicked) colorResource(id = R.color.dark_blue) else Color.Transparent, // Синий цвет рамки
                shape = CircleShape // Форма рамки - круг
            )
            .clickable { isClicked = !isClicked }

    ) {
        Text(
            text = text,
            style = TextStyle(
                color = colorResource(if (isClicked) R.color.blue else R.color.white_), // Синий цвет текста
                fontSize = 16.sp // Размер шрифта
            )
        )
    }
}


@Composable
private fun RV(num: Int) {
    val items = (1..num).toList()  // Создаем список от 1 до num
    val listState = rememberLazyListState()  // Запоминаем состояние списка

    LazyColumn(
        state = listState,  // Передаем состояние списка
        modifier = Modifier.size(height = 300.dp, width = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Устанавливаем большое количество элементов для бесконечного скролла
        items(Int.MAX_VALUE) { index ->
            val item = items[index % items.size]  // Циклический индекс
            Text(
                text = String.format("%02d", item),  // Форматируем число с ведущим нулем
                color = colorResource(id = R.color.white_),
                fontSize = 60.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
    }
    // Начальная прокрутка к середине списка для обеспечения циклического скролла вверх и вниз
    LaunchedEffect(Unit) {
        listState.scrollToItem(Int.MAX_VALUE / 2)  // Прокрутка к середине списка
    }
}


@Composable
private fun House() {
    val counter = remember {
        mutableStateOf(0)
    }
    val color = remember {
        mutableStateOf(Color.Blue)
    }
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = color.value, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                when (++counter.value) {
                    10 -> color.value = Color.Black
                    20 -> color.value = Color.LightGray
                    30 -> color.value = Color.Red
                }
            },
            text = counter.value.toString(),
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )
    }

}

@Composable
private fun Counter() {
    var count by remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Count: $count")
        Button(
            onClick = { count-- },
        ) {
            Text(text = "-")
        }
        Button(onClick = { count++ }) {
            Text(text = "+")
        }
    }

}

@Composable
private fun Sova() {
    Scaffold { paddingValue ->
        Column(
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxSize()
                .padding(top = paddingValue.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.padding(bottom = 80.dp, top = 30.dp),
                text = "REGISTER",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Register(context = "Name")
                Register(context = "Email")
                Register(context = "Password")
            }
            Button(
                onClick = { /* Handle click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "Next",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_sova),
                contentDescription = "Sova",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 60.dp)
            )
            Text(
                modifier = Modifier.padding(bottom = 10.dp, top = 30.dp),
                text = "TRIVIA",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }
}

@Composable
private fun Register(context: String) {
    var text by remember {
        mutableStateOf("")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .background(color = Color.Red),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box() {
            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text(text = context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            )


        }
    }
}

@Composable
private fun ListItem(name: String, prof: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(5.dp),

        ) {
        Box()
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = name
                    )
                    Text(
                        text = prof
                    )
                }

            }
        }
    }
}
