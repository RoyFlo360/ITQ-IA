package edu.itq.ia;

import java.io.*;
public class TicTacToeFacil 
{
	@SuppressWarnings("unused")
	public static void main(String[] args)throws IOException 
	{
		BufferedReader recibir=new BufferedReader (new InputStreamReader(System.in));
		String juego[][];
		juego = new String[3][3];//matrices
		int n=0;
		int fila;
		int columna;
		for (int a=0; a<=2; a=a+1) 
		{
			for (int b=0; b<=2; b=b+1) 
			{
				juego[a][b]="";
				int dato;
				int dato1;
				int datob;
				int datob2;
			}

		}

		//tablero
		System.out.println("[ "+juego[0][0]+" ]"+"[ "+juego[0][1]+" ]"+"[ "+juego[0][2]+" ]");
		System.out.println("[ "+juego[1][0]+" ]"+"[ "+juego[1][1]+" ]"+"[ "+juego[1][2]+" ]");
		System.out.println("[ "+juego[2][0]+" ]"+"[ "+juego[2][1]+" ]"+"[ "+juego[2][2]+" ]");

		while (n<10) 
		{ 
			//ciclo
			//jugador 'x'
			System.out.println("Seleccione fila 0, 1 o 2: ");
			int dato=Integer.parseInt(recibir.readLine());
			System.out.println("Seleccione columna 0, 1 o 2: ");
			int dato1=Integer.parseInt(recibir.readLine());
			//comprobar si la jugada existe
			while (juego[dato][dato1]== "x" || juego[dato][dato1]== "o" ) 
			{ 
				//comparacion
				System.out.println("A ingresado una jugada en donde YA existia una anteriormente");
				System.out.println("Seleccione fila 0, 1 o 2: ");
				dato=Integer.parseInt(recibir.readLine());
				System.out.println("Seleccione columna 0, 1 o 2: ");
				dato1=Integer.parseInt(recibir.readLine());
			}
			juego[dato][dato1] = "x";
			//jugador X
			//tablero
			System.out.println("[ "+juego[0][0]+" ]"+"[ "+juego[0][1]+" ]"+"[ "+juego[0][2]+" ]");
			System.out.println("[ "+juego[1][0]+" ]"+"[ "+juego[1][1]+" ]"+"[ "+juego[1][2]+" ]");
			System.out.println("[ "+juego[2][0]+" ]"+"[ "+juego[2][1]+" ]"+"[ "+juego[2][2]+" ]");
			n=n+1;


			if (juego[0][0]== "x" && juego[0][1]== "x" && juego [0][2]== "x") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			if (juego[1][0]== "x" && juego[1][1]== "x" && juego [1][2]== "X") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			else if (juego[2][0]== "x" && juego[2][1]== "x" && juego [2][2]== "x") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			if (juego[0][0]== "x" && juego[1][0]== "x" && juego [2][0]== "x") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			if (juego[0][1]== "x" && juego[1][1]== "x" && juego [2][1]== "x") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			if (juego[0][2]== "x" && juego[1][2]== "x" && juego [2][2]== "x") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			//diagonales faltan
			if (juego[0][0]== "x" && juego[1][1]== "x" && juego [2][2]== "x") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			if (juego[0][2]== "x" && juego[1][1]== "x" && juego [2][0]== "x") 
			{
				System.out.println("¡Ganaste!");
				break;
			}
			//condiciones victoria
			if (n==9) 
			{
				break;
			}
			//jugador 0
			System.out.println("Seleccione fila 0, 1 o 2: ");
			int datob;
			datob = (int)(Math.random()*3)+0;
			System.out.println("Seleccione columna 0, 1 o 2: ");
			int datob2;
			datob2 = (int)(Math.random()*3)+0;
			System.out.println("numeros elegidos"+datob+"segundo"+datob2);
			//comprobar si la jugada existe
			while (juego[datob][datob2]== "o" || juego[datob][datob2]== "x" ) 
			{
				System.out.println("Computadora");
				datob = (int)(Math.random()*3)+0;
				datob2 = (int)(Math.random()*3)+0;
				System.out.println("numeros elegidos"+datob+"segundo"+datob2);
			}
			juego[datob][datob2] = "o";
			// jugador O
			//tablero
			System.out.println("[ "+juego[0][0]+" ]"+"[ "+juego[0][1]+" ]"+"[ "+juego[0][2]+" ]");
			System.out.println("[ "+juego[1][0]+" ]"+"[ "+juego[1][1]+" ]"+"[ "+juego[1][2]+" ]");
			System.out.println("[ "+juego[2][0]+" ]"+"[ "+juego[2][1]+" ]"+"[ "+juego[2][2]+" ]");
			n=n+1;//cantidad de jugadas permitidas

			if (juego[0][0]== "o" && juego[0][1]== "o" && juego [0][2]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			if (juego[1][0]== "o" && juego[1][1]== "o" && juego [1][2]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			if (juego[2][0]== "o" && juego[2][1]== "o" && juego [2][2]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			if (juego[0][0]== "o" && juego[1][0]== "o" && juego [2][0]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			if (juego[0][1]== "o" && juego[1][1]== "o" && juego [2][1]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			if (juego[0][2]== "o" && juego[1][2]== "o" && juego [2][2]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			//diagonales faltan
			if (juego[0][0]== "o" && juego[1][1]== "o" && juego [2][2]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			if (juego[0][2]== "o" && juego[1][1]== "o" && juego [2][0]== "o") 
			{
				System.out.println("La Computadora gana.");
				break;
			}
			//condiciones victoria
			if (n==9) 
			{
				break;
			}
		}
	}
}