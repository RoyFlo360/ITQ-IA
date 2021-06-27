package edu.itq.ia;

public class NumerosPrimos 
{

	public static void main(String[] args) 
	{
// Contador
		int cont = 0;
//Define inicio
		int inicio = 1;
//Define el limite
		int limit = 100;

		System.out.println("Numeros primos entre " + inicio + " y " + limit);

//loop
		for (int i = ++inicio; i < limit; i++) 
		{

			boolean isPrime = true;

//Revisa si el numero es primo
			for (int j = 2; j < i; j++) 
			{

				if (i % j == 0) 
				{
					isPrime = false;
					break;
				}
			}
// Imprime numeros primos
			if (isPrime) 
			{
				System.out.print(i + "\n");
				cont = cont + 1;
			}
		}
//Imprime el total de numeros primos		
		System.out.println("Total de numeros pares: " + cont);
	}

}