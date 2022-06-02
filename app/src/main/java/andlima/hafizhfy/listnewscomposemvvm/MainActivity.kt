package andlima.hafizhfy.listnewscomposemvvm

import andlima.hafizhfy.listnewscomposemvvm.data.GetAllNewsResponseItem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import andlima.hafizhfy.listnewscomposemvvm.ui.theme.ListNewsComposeMVVMTheme
import andlima.hafizhfy.listnewscomposemvvm.ui.theme.Purple500
import andlima.hafizhfy.listnewscomposemvvm.viewmodel.NewsViewModel
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListNewsComposeMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val newsViewModel= viewModel(modelClass = NewsViewModel::class.java)
                    val dataNews by newsViewModel.dataState.collectAsState()

//                    Spacer(modifier = Modifier.padding(top = 45.dp))
                    LazyColumn {
                        if (dataNews.isEmpty()) {
                            item {

                            }
                        }
                        items(dataNews) {
                            ItemNews(it)
                        }
                    }

                    headerPage()
                }
            }
        }
    }
}

@Composable
fun headerPage() {
    val nContext = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
        ) {
            Text(text = "News", fontWeight = FontWeight.Bold)

//            Card(modifier = Modifier.clickable {
//                nContext.startActivity(Intent(nContext, StaffActivity::class.java))
//            }) {
//                Text(text = "Staff")
//            }

            Text(buildAnnotatedString {
                append("Staff")
            }, Modifier.clickable {
                nContext.startActivity(Intent(nContext, StaffActivity::class.java))
            }.align(Alignment.TopEnd)
            )
//            Image(
//                painter = painterResource(id = andlima.hafizhfy.listnewscomposemvvm.R.drawable.ic_user),
//                contentDescription = "goto_staff",
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .size(30.dp, 30.dp)
//                    .clickable {
//                        nContext.startActivity(Intent(nContext, StaffActivity::class.java))
//                    }
//            )
        }
    }
}

@Composable
fun ItemNews(news: GetAllNewsResponseItem) {
    val nContext = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 50.dp)) {
        Card(
            modifier = Modifier
                .padding(15.dp, 0.dp)
                .fillMaxWidth()
                .clickable {
                    val pindah = Intent(nContext, DetailActivity::class.java)
                    pindah.putExtra("SELECTED_DATA", news)
                    nContext.startActivity(pindah)
                },
            shape = RoundedCornerShape(15.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
//                    painter = painterResource(id = andlima.hafizhfy.listnewscomposemvvm.R.drawable.cat),
                    painter = rememberImagePainter(data = news.image),
                    contentDescription = "thumbnail",
                    Modifier
                        .size(100.dp, 100.dp)
                )

                Column(modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = news.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = news.author)
                    Text(text = news.createdAt)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ListNewsComposeMVVMTheme {
        headerPage()
//        Spacer(modifier = Modifier.padding(top = 50.dp))
        ItemNews(GetAllNewsResponseItem("", "", "", "", "", ""))
    }
}