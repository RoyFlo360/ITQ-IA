package edu.itq.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Puzzle 
{
	
	public int dimencion = 3;
	
	int[] fila = { 1, 0, -1, 0 };
	int[] columna = { 0, -1, 0, 1 };
	
	public int calculacosto(int[][] initial, int[][] goal) 
	{
		int count = 0;
		int n = initial.length;
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) 
				{
					count++;
				}
			}
		}
		return count;
	}
	
	public void imprimirMatrix(int[][] matrix) 
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix.length; j++) 
			{
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean estaLibre(int x, int y) 
	{
		return (x >= 0 && x < dimencion && y >= 0 && y < dimencion);
	}
	
	public void imprimirCamino(Nodo root) 
	{
		if (root == null) 
		{
			return;
		}
		imprimirCamino(root.padre);
		imprimirMatrix(root.matrix);
		System.out.println();
	}
	
	public boolean isSolvable(int[][] matrix) 
	{
		int count = 0;
		List<Integer> array = new ArrayList<Integer>();
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix.length; j++) 
			{
				array.add(matrix[i][j]);
			}
		}
		Integer[] otraLista = new Integer[array.size()];
		array.toArray(otraLista);
		
		for (int i = 0; i < otraLista.length - 1; i++) 
		{
			for (int j = i + 1; j < otraLista.length; j++) 
			{
				if (otraLista[i] != 0 && otraLista[j] != 0 && otraLista[i] > otraLista[j]) 
				{
					count++;
				}
			}
		}
		return count % 2 == 0;
	}
	
	public void resolver(int[][] initial, int[][] goal, int x, int y) 
	{
		PriorityQueue<Nodo> pq = new PriorityQueue<Nodo>(1000, (a, b) -> (a.costo + a.nivel) - (b.costo + b.nivel));
		Nodo root = new Nodo(initial, x, y, x, y, 0, null);
		root.costo = calculacosto(initial, goal);
		pq.add(root);
		
		while (!pq.isEmpty()) 
		{
			Nodo min = pq.poll();
			if (min.costo == 0) 
			{
				imprimirCamino(min);
				return;
			}
			
			for (int i = 0; i < 4; i++) 
			{
	            if (estaLibre(min.x + fila[i], min.y + columna[i])) 
	            {
	            	Nodo hijo = new Nodo(min.matrix, min.x, min.y, min.x + fila[i], min.y + columna[i], min.nivel + 1, min);
	            	hijo.costo = calculacosto(hijo.matrix, goal);
	            	pq.add(hijo);
	            }
	        }
		}
	}
	
	public static void main(String[] args) 
	{
		int[][] initial = { {1, 8, 2}, {0, 4, 3}, {7, 6, 5} };
		int[][] goal    = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
		
		// White tile coordinate
		int x = 1, y = 0;
		
		Puzzle puzzle = new Puzzle();
		if (puzzle.isSolvable(initial)) 
		{
			puzzle.resolver(initial, goal, x, y);
		} 
		else 
		{
			System.out.println("The given initial is impossible to resolver");
		}
	}

}