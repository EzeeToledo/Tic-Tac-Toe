package ar.edu.ungs.tateti;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Starter {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        TaTeTi  taTeTi   = new TaTeTi();

        System.out.println("------------------------");
        System.out.println("|       TA-TE-TI       |");
        System.out.println("------------------------");

        do {
            try {
                System.out.println("-----------------------------------");
                System.out.println("               BOARD               ");
                System.out.println("-----------------------------------");
                System.out.printf("TURN: %s\n%n", taTeTi.proximoTurno());

                taTeTi.mostrar();
                System.out.println("-----------------------------------");
                System.out.print("Enter row position: ");
                int rowPosition = scanner.nextInt();

                System.out.print("Enter column position: ");
                int columnPosition = scanner.nextInt();

                taTeTi.jugar(rowPosition, columnPosition);
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("Please enter a number between 1 and 3");
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a number");
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            } finally {
                clearConsole();
            }
        } while (!taTeTi.terminado());

        System.out.println("-----------------------------------");
        if (ensureGameState(taTeTi)) {
            System.out.printf("CONGRATULATIONS: %s\n%n", taTeTi.ganador());
        } else {
            System.out.println("            TIED GAME              ");
        }
        System.out.println("-----------------------------------");

    }

    private static boolean ensureGameState(TaTeTi taTeTi) {
        return taTeTi.ganador() != ' ';
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
