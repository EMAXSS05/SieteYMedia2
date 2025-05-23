package sieteymedia;

import recursos.Baraja;
import recursos.Carta;

public class SieteYMedia {
    private Baraja baraja;
    private Carta[] cartasJugador;
    private Carta[] cartasBanca;

    public SieteYMedia() {
        baraja = new Baraja();
        baraja.barajar();
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
    }

    public void nuevaPartida() {
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
    }

    public void pedirCartaJugador() {
        Carta c = baraja.darCartas(1)[0];
        insertarCartaEnArray(cartasJugador, c);
    }

    public void turnoBanca() {
        while (valorCartas(cartasBanca) < valorCartas(cartasJugador)) {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }
    }

    public double valorCartasJugador() {
        return valorCartas(cartasJugador);
    }

    public double valorCartasBanca() {
        return valorCartas(cartasBanca);
    }

    public Carta[] getCartasJugador() {
        return cartasJugador;
    }

    public Carta[] getCartasBanca() {
        return cartasBanca;
    }

    private double valorCartas(Carta[] cartas) {
        double total = 0.0;
        int i = 0;
        while (i < cartas.length && cartas[i] != null) {
            int val = cartas[i].getNumero();
            total += (val > 7) ? 0.5 : val;
            i++;
        }
        return total;
    }

    private void insertarCartaEnArray(Carta[] cartas, Carta c) {
        int i = 0;
        while (i < cartas.length && cartas[i] != null) {
            i++;
        }
        if (i < cartas.length) {
            cartas[i] = c;
        }
    }
}