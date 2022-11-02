package baeza.guillermo.practica3_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import baeza.guillermo.practica3_4.Models.CheckInfo
import baeza.guillermo.practica3_4.ui.theme.Practica3_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica3_4Theme {
                MyProgressBar()
            }
        }
    }
}

@Composable
fun MyProgressBar(){
    var showloading by rememberSaveable{mutableStateOf(false)}
    var processStatus by rememberSaveable {mutableStateOf(0f)}

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        if(showloading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 3.dp,
                progress = processStatus
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Green,
                backgroundColor = Color.LightGray,
                progress = processStatus
            )
            Row(
                Modifier.padding(top = 8.dp),
                Arrangement.SpaceAround,
                Alignment.CenterVertically
            ){
                Button(
                    onClick ={
                        if(processStatus > 0f && (processStatus-0.1f) > 0f)
                            processStatus = processStatus - 0.1f
                    }) {
                    Text(text = "Reducir")
                }
                Button(
                    onClick = {
                        if(processStatus < 1f)
                            processStatus = processStatus + 0.1f
                    }
                ) {
                    Text(text = "Incrementar")
                }
            }
        }


        Button(
            modifier = Modifier.padding(top = 100.dp),
            onClick = { showloading = !showloading }
        ) {
            Text(text = "Activar / Desactivar")
        }

    }
}

@Composable
fun MyCheckBox(checkInfo: CheckInfo) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onChange(!checkInfo.selected) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

@Composable
fun CreateCheckBoxes(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onChange = { myNewStatus -> status = myNewStatus}
        )
    }
}
