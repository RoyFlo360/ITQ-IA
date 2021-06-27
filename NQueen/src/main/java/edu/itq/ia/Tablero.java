package edu.itq.ia;

import java.util.Scanner;

@SuppressWarnings("unused")
public class Tablero 
{
    int Tablero[][];
    int reinas;
 
    public Tablero(int reinas) 
    {
        Tablero = new int[20][20];
        this.reinas = reinas;
    }
 
    void displayTablero()
    {
    	int cont = 0;
        int i, j;
        for(i=0; i<reinas; i++)
        {
            for(j=0; j<reinas; j++)
            {
                if(Tablero[i][j] == 1)
                    System.out.print(" * ");
                else
                    System.out.print(" - ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
 
    void reset()
    {
        for(int i=0; i<reinas; i++)
        {
            for(int j=0; j<reinas; j++)
            {
                Tablero[i][j] = 0;
            }
        }
    }
 
    boolean isValidPlace(int row, int col)
    {
        int i,j;
 
        //Revisar horizontales
        for(i=col; i>=0; i--)
        {
            if(Tablero[row][i] == 1)
                return false;
        }
 
        //Revisar diagonal izq
        for(i=row, j=col; i>=0 && j>=0; i--,j--)
        {
            if(Tablero[i][j] == 1)
                return false;
        }
 
        //Revisar diagonal der
        for(i=row, j=col; i<reinas && j>=0; i++,j--)
        {
            if(Tablero[i][j] == 1)
                return false;
        }
 
        return true;
    }
}