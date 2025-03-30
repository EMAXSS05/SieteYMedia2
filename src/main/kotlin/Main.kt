import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import sieteymedia.SieteYMedia
import recursos.Carta

@Composable
@Preview
fun App() {
    val juego = remember { SieteYMedia() }
    var cartasJugador by remember { mutableStateOf(listOf<Carta>()) }
    var cartasBanca by remember { mutableStateOf(listOf<Carta>()) }
    var puntajeJugador by remember { mutableStateOf(0.0) }
    var puntajeBanca by remember { mutableStateOf(0.0) }
    var juegoTerminado by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Siete y Media", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                juego.nuevaPartida()
                cartasJugador = listOf()
                cartasBanca = listOf()
                puntajeJugador = 0.0
                puntajeBanca = 0.0
                juegoTerminado = false
                resultado = ""
            }) {
                Text("Nueva Partida")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {

                if (!juegoTerminado) {
                    juego.pedirCartaJugador()
                    cartasJugador = juego.getCartasJugador().filterNotNull()
                    puntajeJugador = juego.valorCartasJugador()
                    if (puntajeJugador > 7.5) {
                        juegoTerminado = true
                        resultado = "Te pasaste de 7.5. La banca gana."
                    }
                }
            }, enabled = !juegoTerminado) {
                Text("Pedir Carta")
            }

            Button(onClick = {
                if (!juegoTerminado) {
                    juego.turnoBanca()
                    cartasBanca = juego.getCartasBanca().filterNotNull()
                    puntajeBanca = juego.valorCartasBanca()
                    juegoTerminado = true
                    resultado = when {
                        puntajeBanca > 7.5 -> "La banca se pasó. ¡Ganaste!"
                        puntajeBanca >= puntajeJugador -> "La banca gana."
                        else -> "¡Ganaste!"
                    }
                }
            }, enabled = !juegoTerminado) {
                Text("Plantarse")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tus cartas:")
            LazyColumn {
                items(cartasJugador) { carta ->
                    Text("${carta.getNumero()} de ${carta.getPalo()}")
                }
            }
            Text("Puntaje: $puntajeJugador")

            Spacer(modifier = Modifier.height(16.dp))

            if (juegoTerminado) {
                Text("Cartas de la banca:")
                LazyColumn {
                    items(cartasBanca) { carta ->
                        Text("${carta.getNumero()} de ${carta.getPalo()}")
                    }
                }
                Text("Puntaje banca: $puntajeBanca")
                Text(resultado, style = MaterialTheme.typography.h5)
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}