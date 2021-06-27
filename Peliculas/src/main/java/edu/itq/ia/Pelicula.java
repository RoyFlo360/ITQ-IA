package edu.itq.ia;

public class Pelicula 
{
	private int id;
	private String titulo;
	private String[] actores, directores;
	private Fecha estreno;
	
	public Pelicula(String titulo, String[] actores, String[] directores, Fecha estreno){
		this.titulo = titulo;
		this.actores = actores;
		this.directores = directores;
		this.estreno = estreno;
	}

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getTitulo() 
	{
		return titulo;
	}
	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}
	public String[] getActores() 
	{
		return actores;
	}
	public String getActores(int index) 
	{
		return actores[index];
	}
	public void setActores(String[] actores) 
	{
		this.actores = actores;
	}
	public void setActores(int index, String actor) 
	{
		this.actores[index] = actor;
	}
	public String[] getDirectores() 
	{
		return directores;
	}
	public String getDirectores(int index) 
	{
		return directores[index];
	}
	public void setDirectores(String[] directores) 
	{
		this.directores = directores;
	}
	public void setDirectores(int index, String director) 
	{
		this.directores[index] = director;
	}
	public Fecha getEstreno() 
	{
		return estreno;
	}
	public void setEstreno(Fecha estreno) 
	{
		this.estreno = estreno;
	}

	public String toString()
	{
		String	listaActores = "",
				listaDirectores = "";
		
		for (String actor : this.actores) 
		{
			listaActores += "   " + actor + "\n";
		}
		for (String director : this.directores) 
		{
			listaDirectores += "   " + director + "\n";
		}
		
		return String.format(
			"--[Película]-----------\n" +
			"Título: %s\n" +
			"Directores:\n%s" +
			"Actores:\n%s"+
			"Estreno: %s\n" +
			"-----------------------\n",
			this.titulo, listaDirectores, listaActores, estreno.toString());
			
	}
}