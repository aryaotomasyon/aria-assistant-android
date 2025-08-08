
package com.arya.assistant
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opencsv.CSVReader
import java.io.InputStreamReader
data class Item(val model:String,val price:String,val status:String,val brand:String)
class MainActivity: ComponentActivity(){
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val all = loadAll()
    setContent { MaterialTheme { Main(all) } }
  }
  private fun loadCsv(asset:String, brand:String): List<Item> {
    val out = mutableListOf<Item>()
    assets.open(asset).use { ins ->
      CSVReader(InputStreamReader(ins)).use { r ->
        r.readAll().drop(1).forEach { row -> if(row.size>=3) out.add(Item(row[0],row[1],row[2],brand)) }
      }
    }
    return out
  }
  private fun loadAll() = loadCsv("gmi.csv","GMI")+loadCsv("beka.csv","BEKA")+loadCsv("hansford.csv","HANSFORD")
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable fun Main(all: List<Item>){
  var q by remember{ mutableStateOf("") }
  val list = all.filter{ it.model.contains(q,true) }
  Scaffold(topBar={ SmallTopAppBar(title={ Text("ARIA Assistant") }) }){ pad->
    Column(Modifier.padding(pad).padding(16.dp)){
      OutlinedTextField(value=q,onValueChange={q=it},modifier=Modifier.fillMaxWidth(),placeholder={Text("Enter / وارد کنید / Yazınız")})
      Spacer(Modifier.height(12.dp))
      LazyColumn{ items(list.size){ i-> val it=list[i]; ElevatedCard{ Column(Modifier.padding(12.dp)){ Text("Model: ${it.model}"); Text("Price: ${it.price} EUR"); Text("Status: ${it.status} | ${it.brand}")}} } }
    }
  }
}
