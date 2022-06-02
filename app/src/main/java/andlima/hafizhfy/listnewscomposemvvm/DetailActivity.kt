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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListNewsComposeMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val selectedData = intent.getParcelableExtra("SELECTED_DATA") as GetAllNewsResponseItem?
                    DetailView(selectedData!!)
                }
            }
        }
    }
}

@Composable
fun DetailView(news: GetAllNewsResponseItem) {
    Column(modifier = Modifier.padding(15.dp)) {
        Image(painter = rememberImagePainter(data = news.image), contentDescription = "thumbnail")
        Text(text = news.title, modifier = Modifier.padding(top = 10.dp))
        Text(text = news.createdAt)
        Text(text = news.author, modifier = Modifier.padding(bottom = 10.dp))
        Text(text = news.description)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    ListNewsComposeMVVMTheme {
        DetailView(GetAllNewsResponseItem("", "", "", "", "", ""))
    }
}