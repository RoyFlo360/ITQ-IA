package edu.itq.ia;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe 
{
    private static Scanner in = new Scanner(System.in);
    private static Tablero tablero = new Tablero();
    
    private static boolean gameover = false;
    private static boolean jugador = true;

    public static void main(String[] args)
    {
        System.out.println(tablero);
        while(!gameover)
        {
            Posicion posicion = null;
            if(jugador)
            {
                posicion = hacerMovimiento();
                tablero = new Tablero(tablero, posicion, SignoDeJugador.Cruz);
            }
            else
            {
                tablero = mejorMovimiento(tablero);
            }               
            jugador = !jugador;
            System.out.println(tablero);
            estadoJuego();
        }
    }

    private static Tablero mejorMovimiento(Tablero tablero) 
    {
        ArrayList <Posicion> posiciones = tablero.obtenerPosicionesLibres();
        Tablero mejorHijo = null;
        int anterior = Integer.MIN_VALUE;
        for(Posicion pos : posiciones)
        {
            Tablero hijo = new Tablero(tablero, pos, SignoDeJugador.Circulo);
            int actual = min(hijo);
            //System.out.println("Child Score: " + current);
            if(actual > anterior)
            {
                mejorHijo = hijo;
                anterior = actual;
            }
        }
        return mejorHijo;
    }

    public static int max(Tablero tablero)
    {
        EstadoDelJuego estadoDelJuego = tablero.obtenerEstadoDelJuego();
        if(estadoDelJuego == EstadoDelJuego.CirculoGana)
            return 1;
        else if(estadoDelJuego == EstadoDelJuego.CruzGana)
            return -1;
        else if(estadoDelJuego == EstadoDelJuego.Empate)
            return 0;
        ArrayList<Posicion> posiciones = tablero.obtenerPosicionesLibres();
        int mej = Integer.MIN_VALUE;
        for(Posicion pos : posiciones)
        {
            Tablero tabl = new Tablero(tablero, pos, SignoDeJugador.Circulo);
            int mov = min(tabl);
            if(mov > mej)
                mej = mov;
        }       
        return mej;
    }

    public static int min(Tablero tablero)
    {
        EstadoDelJuego estadoDelJuego = tablero.obtenerEstadoDelJuego();
        if(estadoDelJuego == EstadoDelJuego.CirculoGana)
            return 1;
        else if(estadoDelJuego == EstadoDelJuego.CruzGana)
            return -1;
        else if(estadoDelJuego == EstadoDelJuego.Empate)
            return 0;
        ArrayList<Posicion> posiciones = tablero.obtenerPosicionesLibres();
        int mej = Integer.MAX_VALUE;
        for(Posicion pos : posiciones)
        {
            Tablero tabl = new Tablero(tablero, pos, SignoDeJugador.Cruz);
            int mov = max(tabl);
            if(mov < mej)
                mej = mov;
        }
        return mej;
    }

    private static void estadoJuego()
    {
        EstadoDelJuego estadoJuego = tablero.obtenerEstadoDelJuego();
        gameover = true;
        switch(estadoJuego)
        {
            case CruzGana : 
                System.out.println("¡Ganaste!");
                break;
            case CirculoGana : 
                System.out.println("La computadora gana.");
                break;
            case Empate : 
                System.out.println("Empate...");
                break;
            default : gameover = false;
                break;
        }
    }

    public static Posicion hacerMovimiento()
    {
        Posicion posicion = null;
        while(true)
        {
            System.out.print("Seleccione culumna 0, 1 o 2: ");
            int culumna = obtenerColumna_Fila();
            System.out.print("Seleccione fila 0, 1 o 2: ");
            int fila = obtenerColumna_Fila();
            posicion = new Posicion(culumna, fila);
            if(tablero.estaMarcado(posicion))
                System.out.println("Movimiento invalido.");
            else break;
        }
        return posicion;
    }

    private static int obtenerColumna_Fila()
    {
        int ret = -1;
        while(true)
        {
            try
            {
                ret = Integer.parseInt(in.nextLine());
            } 
            catch (NumberFormatException e)
            {
            	
            }
            if(ret < 0 | ret > 2)
                System.out.print("\nPosicion invalida. No existe en el tablero.");
            else break;
        }
        return ret;
    }
}

final class Posicion 
{
    private final int columna;
    private final int fila;

    public Posicion(int columna, int fila)
    {
        this.columna = columna;
        this.fila = fila;
    }
    public int obtenerFila()
    {
    	return this.fila;
    }
    public int obtenerColumna()
    {
    	return this.columna;
    }
}

enum SignoDeJugador
{
    Cruz, Circulo
}

enum EstadoDelJuego 
{
    Incomplete, CruzGana, CirculoGana, Empate
}

class Tablero 
{
    private char[][] tablero; //e = vacio, x = cruz, o = circulo.

    public Tablero()
    {
        tablero = new char[3][3];
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
                tablero[x][y] = 'e'; //Empezar con tablero vacio
    }

    public Tablero(Tablero from, Posicion posicion, SignoDeJugador signo)
    {
        tablero = new char[3][3];
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
                tablero[x][y] = from.tablero[x][y];
        tablero[posicion.obtenerColumna()][posicion.obtenerFila()] = signo==SignoDeJugador.Cruz ? 'x':'o';
    }

    public ArrayList<Posicion> obtenerPosicionesLibres()
    {
        ArrayList<Posicion> retArr = new ArrayList<Posicion>();     
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
                if(tablero[x][y] == 'e')
                    retArr.add(new Posicion(x, y));
        return retArr;
    }

    public EstadoDelJuego obtenerEstadoDelJuego()
    {    
        if(haGanado('x'))
            return EstadoDelJuego.CruzGana;
        else if(haGanado('o'))
            return EstadoDelJuego.CirculoGana;
        else if(obtenerPosicionesLibres().size() == 0)
            return EstadoDelJuego.Empate;
        else return EstadoDelJuego.Incomplete;
    }

    private boolean haGanado(char signo)
    { 
    	int x,y;

    	//Revisar Diagonales
    	if(tablero[0][0]==signo && tablero[1][1] == signo && tablero [2][2]==signo)
    		return true;
    	if(tablero[0][2]==signo && tablero[1][1] == signo && tablero [2][0]==signo)
    		return true;

    	//Revisar Filas
    	for(x=0;x<3;x++)
    	{
    		for(y=0;y<3;y++)
    			if(tablero[x][y] != signo)
    				break;
    		if(y==3)
    			return true;
    	}

    	//Revisar Columnas
    	for(x=0;x<3;x++)
    	{
    		for(y=0;y<3;y++)
    			if(tablero[y][x] != signo)
    				break;
    		if(y==3)
    			return true;
    	}
       	return false;
    }

    public boolean estaMarcado(Posicion posicion)
    {
        if(tablero[posicion.obtenerColumna()][posicion.obtenerFila()] != 'e')
            return true;
        return false;
    }

    public String toString()
    {
        String retString = "\n";
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                if(tablero[x][y] ==  'x' || tablero[x][y] == 'o')
                    retString += "["+tablero[x][y]+"]";
                else
                    retString += "[ ]";
            }
            retString += "\n";
        }       
        return retString;
    }   

}