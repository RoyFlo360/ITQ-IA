package edu.itq.ia;

import java.io.*;

class LisaChatBot 
{
	// Menú de inicio del programa.
	private static void displayMenu(boolean startup) 
	{
		if (startup) 
		{
			System.out.println("Escribe aqui...");
		}
		System.out.print("> ");
	}

	// Obtiene el total de filas del archivo.
	private static int getLines(String filename) 
	{
		int lines = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
		{
			while (br.readLine() != null)
				lines++;
		} 
		catch (IOException exc) 
		{
			System.out.println("I/O Exception: " + exc);
		}
		return lines;
	}
	
	// Lee la entrada del usuario en la consola.
	private static String getUserInput() 
	{
		String userInput = null;
		try 
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			userInput = br.readLine();
		} catch (IOException exc) 
		{
			System.out.println("I/O Exception: " + exc);
		}
		return userInput;
	}

	// Lee todas las respuestas del archivo txt.
	private static String[] getResponsesArray(String filename, int lines) 
	{
		int lineCount = 0;
		String line;
		String[] responsesArray = new String[lines];
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
		{
			do 
			{
				line = br.readLine();
				if (line != null) 
				{
					responsesArray[lineCount] = line;
					lineCount++;
				}
			} 
			while (line != null);
		} 
		catch (FileNotFoundException exc) 
		{
			System.out.println("FileNotFoundException: " + exc);
		} 
		catch (IOException exc) 
		{
			System.out.println("I/O Exception: " + exc);
		}

		return responsesArray;
	}

	// Obtiene la respuesta de LisaChatbot dada la entrada del usuario.
	private static String getResponse(String[] responses, String userInput) 
	{
		String tag, response;
		String[] array;
		for (String responseLine : responses) 
		{
			if (responseLine != null) 
			{
				array = responseLine.split(" - ");
				tag = array[0];
				response = array[1];
				if (tag.compareToIgnoreCase(userInput) == 0) 
				{
					return response;
				}
			}
		}
		return "Sin respuesta...";
	}

	// Metodos de LisaChatBot.
	public static void main(String args[]) 
	{

		// Archivo de respuestas.
		String userInput, response;
		String filename = "src/responses/java/responses.txt";
		int lines = getLines(filename);
		String[] responsesArray = getResponsesArray(filename, lines);

		// Despliega menú de inicio.
		displayMenu(true);

		// Estructura repetitiva de LisaChatBot.
		do 
		{
			userInput = getUserInput();
			response = getResponse(responsesArray, userInput);
			System.out.println("Chatbot: " + response);
			if (!userInput.equals("Adios")) 
			{
				displayMenu(false);
			}
		} 
		while (!userInput.equals("Adios"));
	}
}