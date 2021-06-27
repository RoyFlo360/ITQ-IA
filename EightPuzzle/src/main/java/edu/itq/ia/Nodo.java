package edu.itq.ia;

public class Nodo 
{
	public Nodo padre;
	public int[][] matrix;
	
	// Coordenadas de lugares en blanco
	public int x, y;
	
	// Número de baldosas extraviadas
	public int costo;
	
	// El número de movimientos hasta ahora
	public int nivel;
	
	public Nodo(int[][] matrix, int x, int y, int newX, int newY, int nivel, Nodo padre) 
	{
		this.padre = padre;
		this.matrix = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) 
		{
			this.matrix[i] = matrix[i].clone();
		}
		
		this.matrix[x][y]       = this.matrix[x][y] + this.matrix[newX][newY];
		this.matrix[newX][newY] = this.matrix[x][y] - this.matrix[newX][newY];
		this.matrix[x][y]       = this.matrix[x][y] - this.matrix[newX][newY];
		
		this.costo = Integer.MAX_VALUE;
		this.nivel = nivel;
		this.x = newX;
		this.y = newY;
	}
	
}