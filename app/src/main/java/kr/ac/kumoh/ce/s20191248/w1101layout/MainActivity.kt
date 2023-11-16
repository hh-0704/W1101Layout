package kr.ac.kumoh.ce.s20191248.w1101layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20191248.w1101layout.ui.theme.W1101LayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = ViewModelProvider(this)[CounterVIewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            Myapp{
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center) {
                    Clicker()
                    Counter(vm)
                    //Counter()
                }
            }
            //MyLinearLayout()
        }
    }
}

@Composable
fun MyLinearLayout() {
    Column {
        Row {
            Text(text = "안녕하세요",
                modifier = Modifier
                    .background(Color.Yellow)
                    .padding(8.dp))
            Text(text = "컴공",
                modifier = Modifier
                    .background(Color.Cyan)
                    .padding(8.dp))
        }
        Text(text = "금오공과대학교",
            modifier = Modifier
                .background(Color.Magenta)
                .padding(8.dp))
    }
}

@Composable
fun Myapp(content: @Composable () -> Unit) {
    W1101LayoutTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun Clicker() {
    //var txtString = "눌러주세요"
    //var txtString by remember{mutableStateOf("눌러주세요")}
    var (txtString, setTxtString) = remember {
        mutableStateOf("눌러주세요")
    }

    Column (modifier = Modifier
        //.fillMaxSize()
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = txtString,
            fontSize = 70.sp,
        )
        Button(modifier = Modifier.fillMaxWidth()
            ,onClick = { setTxtString("눌렸습니다") }) {
            Text("눌러봐")
        }
    }
}

@Composable
fun Counter(vIewModel: CounterVIewModel) {
    //var count = 0
    /*val (count, setCount) = rememberSaveable {
        mutableStateOf((0))
    }*/
    val count by vIewModel.count.observeAsState(0)

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$count", fontSize = 70.sp,)
        Row {
            Button(modifier = Modifier.weight(1f),
                onClick = {
                    //count++
                    //setCount(count + 1)
                    vIewModel.onAdd()
                }) {
                Text("증가")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(modifier = Modifier.weight(1f), 
                onClick = {
                    //if(count > 0) setCount(count - 1)
                    vIewModel.onSub()
                }) {
                Text(text = "감소")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    W1101LayoutTheme {
        Greeting("Android")
    }
}