package ar.edu.ungs.tateti;

public final class TaTeTi {
    private char[][] tablero   = new char[3][3];
    private char     turno     = 'x';
    private boolean  terminado = false;
    private char     ganador   = ' ';

    public TaTeTi() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.tablero[i][j] = ' ';
            }
        }

    }

    public void mostrar() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print((this.tablero[i][j] == ' ' ? '·' : this.tablero[i][j]) + "  ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public char proximoTurno() {
        return this.turno;
    }

    public void jugar(int fila, int columna) {
        if (fila >= 1 && fila <= 3 && columna >= 1 && columna <= 3) {
            if (this.contenido(fila, columna) != ' ') {
                throw new RuntimeException("Error! Se intentó jugar en una posición no vacía. ");
            } else if (this.terminado()) {
                throw new RuntimeException("Error! Se intentó jugar pero el juego ya terminó. ");
            } else {
                this.tablero[fila - 1][columna - 1] = this.turno;
                this.verificarTerminado();
                if (!this.terminado()) {
                    this.turno = (char) (this.turno == 'x' ? 111 : 120);
                }

            }
        } else {
            throw new RuntimeException("Error! Las posición (" + fila + "," + columna + ") está fuera del tablero. ");
        }
    }

    public char contenido(int fila, int columna) {
        return this.tablero[fila - 1][columna - 1];
    }

    public boolean terminado() {
        return this.terminado;
    }

    private void verificarTerminado() {
        int cont;
        for (cont = 0; cont < 3; ++cont) {
            if (this.tablero[cont][0] == this.tablero[cont][1] && this.tablero[cont][1] == this.tablero[cont][2] && this.tablero[cont][2] != ' ') {
                this.ganador   = this.tablero[cont][0];
                this.terminado = true;
            }
        }

        for (cont = 0; cont < 3; ++cont) {
            if (this.tablero[0][cont] == this.tablero[1][cont] && this.tablero[1][cont] == this.tablero[2][cont] && this.tablero[2][cont] != ' ') {
                this.ganador   = this.tablero[0][cont];
                this.terminado = true;
            }
        }

        if (this.tablero[0][0] == this.tablero[1][1] && this.tablero[1][1] == this.tablero[2][2] && this.tablero[2][2] != ' ') {
            this.ganador   = this.tablero[0][0];
            this.terminado = true;
        }

        if (this.tablero[0][2] == this.tablero[1][1] && this.tablero[1][1] == this.tablero[2][0] && this.tablero[2][0] != ' ') {
            this.ganador   = this.tablero[0][2];
            this.terminado = true;
        }

        if (!this.terminado) {
            cont = 0;

            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (this.tablero[i][j] == ' ') {
                        ++cont;
                    }
                }
            }

            if (cont == 0) {
                this.ganador   = ' ';
                this.terminado = true;
            }

        }
    }

    public char ganador() {
        if (!this.terminado()) {
            throw new RuntimeException("Error! Se consultó el ganador de un juego que no está terminado.");
        } else {
            return this.ganador;
        }
    }
}
