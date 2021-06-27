package edu.itq.ia;


import java.util.*;

public class Tablero 
{
    private static final int OCUPADO = 1;
    private static final int VACIO = 2;
    private static final int DER = 0;
    private static final int ARRIBA = 1;
    private static final int IZQ = 2;
    private static final int ABAJO = 3;
    private static int [] direcciones = {DER, ARRIBA, IZQ, ABAJO};
       
    int [] [] Tablero = {
        {0, 0, 1, 1, 1, 0, 0},
        {0, 0, 1, 1, 1, 0, 0},
        {1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 2, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1},
        {0, 0, 1, 1, 1, 0, 0},
        {0, 0, 1, 1, 1, 0, 0}
    };
    
    int [] [] numberTablero = {
        {0, 0, 0, 1, 2, 0, 0},
        {0, 0, 3, 4, 5, 0, 0},
        {6, 7, 8, 9, 10, 11, 12},
        {13, 14, 15, 16, 17, 18, 19},
        {20, 21, 22, 23, 24, 25, 26},
        {0, 0, 27, 28, 29, 0, 0},
        {0, 0, 30, 31, 32, 0, 0}
    };
  
    public void clearField(int x, int y) 
    {
        Tablero[x][y] = VACIO;
    }
  
    public void setPeg(int x, int y) 
    {
        Tablero[x][y] = OCUPADO;
    }
            
    public void copy(Tablero source, Tablero target) 
    {
        for (int x = 0; x < 7; x++) 
        {
            for (int y = 0; y < 7; y++) 
            {
                target.Tablero[x][y] = source.Tablero[x][y];
            }
        }
    }
          
    private boolean isValidMove(int x, int y, int newX, int newY) 
    {
        return 0 <= x && x < Tablero.length
        && 0 <= y && y < Tablero[x].length
        && 0 <= newX && newX < Tablero.length
        && 0 <= newY && newY < Tablero[newX].length
        && Tablero[newX][newY] == VACIO
        && Tablero[(x + newX) / 2][(y + newY) / 2] == OCUPADO
        && Tablero[x][y] == OCUPADO;
    }
          
    public boolean salto(int x, int y, int direccion) 
    {
        int newX = getNewX(x, direccion);
        int newY = getNewY(y, direccion);
        if ( isValidMove(x, y, newX, newY)) {
            setPeg(newX, newY);
            clearField(x, y);
            clearField((x + newX) / 2, (y + newY) / 2);
            return true;
        }
        return false;
    }
          
    public void goBack(int x, int y, int direccion) 
    {
        int newX = getNewX(x, direccion);
        int newY = getNewY(y, direccion);
        clearField(newX, newY);
        setPeg(x, y);
        setPeg((x + newX) / 2, (y + newY) / 2);
    }
          
    private int getNewX(int x, int direccion) 
    {
        int newX = x;
        switch (direccion) 
        {
            case DER: newX += 2;
                break;
            case IZQ: newX -= 2;
        }
        return newX;
    }
          
    private int getNewY(int y, int direccion) 
    {
        int newY = y;
        switch (direccion) 
        {
            case ARRIBA: newY -= 2;
                break;
            case ABAJO: newY += 2;
        }
        return newY;
    }
          
    public void print() 
    {
        for (int x = 0; x < Tablero.length; x++) 
        {
            for (int y = 0; y < Tablero[x].length; y++) 
            {
                System.out.print(Tablero[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }          
          
    public boolean estaOCUPADO(int x, int y) 
    {
        return Tablero[x][y] == OCUPADO;
    }
  
    public int [] getdirecciones() {
        return Arrays.copyOf(direcciones, direcciones.length);
    }

}