package baeza.guillermo.practica3_4.Models

data class CheckInfo (
    val title : String,
    var selected: Boolean = false,
    var onChange: (Boolean) -> Unit
)