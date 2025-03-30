package sieteymedia;

import java.util.Scanner;
import recursos.Carta;

public class InterfaceConsole {
    private SieteYMedia juego;
    private Scanner sc;

    public InterfaceConsole() {
        juego = new SieteYMedia();
        sc = new Scanner(System.in);
        presentarJuego();
        jugar();
    }

    public static void main(String[] args) {
        new InterfaceConsole();
    }

    private void presentarJuego() {
        System.out.println("- El usuario es el jugador y el ordenador la banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- Las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println("- Hay dos turnos de juego: el turno del jugador y el turno de la banca.");
        System.out.println("- El jugador puede plantarse en cualquier momento.");
        System.out.println("- Si la suma de los valores de las cartas sacadas es superior a 7 y medio, pierde.");
        System.out.println("- La banca juega hasta empatar o superar al jugador sin pasarse de 7.5.");
        System.out.println("\n¡Empecemos!\n");
    }

    private void jugar() {
        turnoJugador();
        turnoBanca();
        System.out.println("Adiós");
    }

    private void turnoJugador() {
        char opc = 'C';
        System.out.println("Como mínimo recibes una carta...");
        while (juego.valorCartasJugador() < 7.5 && opc == 'C') {
            juego.pedirCartaJugador();
            mostrarCartas(juego.getCartasJugador());
            System.out.println("\n\tValor de cartas: " + juego.valorCartasJugador());
            if (juego.valorCartasJugador() < 7.5) {
                System.out.println("\n¿Pides [C]arta o te [P]lantas?");
                opc = sc.next().trim().toUpperCase().charAt(0);
            }
        }
    }

    private void turnoBanca() {
        if (juego.valorCartasJugador() > 7.5) {
            System.out.println("Te has pasado, gana la banca");
            return;
        }
        System.out.println("\n\nTurno de banca ...");
        juego.turnoBanca();
        mostrarCartas(juego.getCartasBanca());
        System.out.println("\nValor de las cartas de la banca: " + juego.valorCartasBanca());
        if (juego.valorCartasBanca() > 7.5) {
            System.out.println("La banca se pasó, ganas tú!");
        } else {
            System.out.println("Gana la banca");
        }
    }

    private void mostrarCartas(Carta[] cartas) {
        int i = 0;
        while (i < cartas.length && cartas[i] != null) {
            System.out.print("\t" + cartas[i]);
            i++;
        }
        System.out.println();
    }
}
