package edu.itq.ia;

import java.util.Scanner;
import java.util.Vector;

public class Filmoteca 
{
	private Vector<Pelicula> peliculas = new Vector<Pelicula>();
	private String titulo;
	private int idAsignadas = 1;
	private final Scanner teclado = new Scanner(System.in);
	private final String[] acciones = new String[]{"Lista", "Insertar", "Modificar", "Eliminar", "Buscar", "Visualizar", "Salir"};
	
	public Filmoteca(String titulo)
	{
		this.titulo = titulo;
	}
	
	// Lista de las películas
	private void lista()
	{
		System.out.println(toString());
	}
	
	// Insertar datos para nueva Pelicula
	public void insertar()
	{
		String titulo;
		Vector <String> actores = new Vector <String>();
		Vector <String> directores = new Vector <String>();
		int dia, mes, anio;

		System.out.println("--[Insertar]-----------");

		while(true)
		{
			System.out.print("Título => ");
			titulo = teclado.nextLine();
			if(!titulo.equals("")) break;
		}

		dia = enteroDeNextLine("Día del estreno");
		mes = enteroDeNextLine("Mes del estreno");
		anio = enteroDeNextLine("Año del estreno");
		
		System.out.println("Directores: ");
		while(true)
		{
			System.out.print("  => ");
			String director = teclado.nextLine();
			
			if(director.equals("")) break;
			
			directores.add(director);
		}
		
		System.out.println("Actores: ");
		while(true)
		{
			System.out.print("  => ");
			String actor = teclado.nextLine();
			
			if(actor.equals("")) break;
			
			actores.add(actor);
		}
		
		insertar(new Pelicula(
			titulo,
			(String[]) actores.toArray(new String[actores.size()]),
			(String[]) directores.toArray(new String[directores.size()]),
			Fecha.nuevaFecha(mes, dia, anio)));
		
		System.out.println("-----------------------\n");
	}
	// Insertar conjunto de peliculas desde Array
	public void insertar(Pelicula[] peliculas) {
		for(Pelicula pelicula : peliculas)
			insertar(pelicula);
	}
	// Insertar objeto Pelicula nuevo
	public void insertar(Pelicula pelicula)
	{
		this.peliculas.add(pelicula);
		this.peliculas.lastElement().setId(idAsignadas++);	
	}
	
	// Modificar Pelicula
	public void modificar(int id)
	{
		Pelicula peliculaMod = null;
		
		for(Pelicula pelicula : this.peliculas)
			if(pelicula.getId() == id)
			{
				peliculaMod = pelicula;
				break;
			}
		
		if(peliculaMod == null)
		{
			System.err.println(
				"--[Modificar]----------\n" +
				"ID: no válida\n" +
				"-----------------------\n");
		}else{
		
			System.out.println("--[Modificar]----------");
			System.out.println("ID: " + peliculaMod.getId());
	
			
			peliculaMod.setTitulo(modificarParametro(
				"Título", peliculaMod.getTitulo()));
			
			for(int i=0; i<peliculaMod.getDirectores().length; i++)
				peliculaMod.setDirectores(i, modificarParametro(
					"Director", peliculaMod.getDirectores(i)));
			
			for(int i=0; i<peliculaMod.getActores().length; i++)
				peliculaMod.setActores(i, modificarParametro(
					"Actor", peliculaMod.getActores(i)));
			
			peliculaMod.setEstreno(modificarParametro(
				peliculaMod.getEstreno()));

			System.out.println("-----------------------\n");
		}
	}
	
	// Método que comprueba si se quiere modificar un elemento (Con Fecha)
	private Fecha modificarParametro(Fecha estreno) {
		int dia, mes, anio;

		dia = modificarParametro("Día del estreno", estreno.getDia());
		mes = modificarParametro("Mes del estreno", estreno.getMes());
		anio = modificarParametro("Año del estreno", estreno.getAnio());
		
		return Fecha.nuevaFecha(mes, dia, anio);
	}
	// Compatibilidad entre el método con String y el int
	private int modificarParametro(String titulo, int valor) {
		return enteroDeNextLine(String.format("%s: %s\n ", titulo, valor), valor);
	}
	// Método que comprueba si se quiere modificar un elemento
	private String modificarParametro(String titulo, String valor) {
		String modificar;

		System.out.println(titulo + ": " + valor);
		System.out.print("  => ");
		modificar = teclado.nextLine();
		
		if(modificar.equals(""))
			return valor;
		
		return modificar;
	}

	// Eliminar una pelicula por la ID
	public void eliminar(int id){
		boolean eliminado = false;
		for(Pelicula pelicula : this.peliculas)
			if(pelicula.getId() == id){
				eliminado = true;
				System.out.println(String.format(
					"--[Eliminar]-----------\n" +
					"ID: %s\n" +
					"Título: %s\n" +
					"-----------------------\n",
					id, pelicula.getTitulo()));
				this.peliculas.remove(pelicula);
				break;
			}
		
		if(!eliminado)
			System.err.println(
				"--[Eliminar]-----------\n" +
				"ID: no válida\n" +
				"-----------------------\n");
	}
	
	// Información de la pelíula por su ID
	public void informacion(int id){
		boolean informado = false;
		for(Pelicula pelicula : this.peliculas)
			if(pelicula.getId() == id){
				System.out.println(pelicula.toString());
				informado = true;
				break;
			}

		if(!informado)
			System.err.println(
				"--[Película]-----------\n" +
				"ID: no válida\n" +
				"-----------------------\n");
	}
	
	// Buscar texto contenido en los títulos
	public void buscar(String busqueda){
		String lista = "";
		
		for(Pelicula pelicula : this.peliculas)
			if(pelicula.getTitulo().toLowerCase().indexOf(busqueda.toLowerCase()) != -1)
				lista += String.format("  [%s]\t%s\n", pelicula.getId(), pelicula.getTitulo());
		
		System.out.println(String.format(
			"--[Busqueda]-----------\n" +
			"Buscando: \"%s\"\n" +
			"Películas:\n%s" +
			"-----------------------\n",
			busqueda, lista));
	}
	
	// Lista de las películas
	public String toString(){
		String lista = "";
		
		for(Pelicula pelicula : this.peliculas){
			lista += String.format("  [%s]\t%s\n", pelicula.getId(), pelicula.getTitulo());
		}

		return String.format(
			"--[Filmoteca]----------\n" +
			"Titulo: %s\n" +
			"Películas:\n%s" + 
			"-----------------------\n",
			this.titulo, lista);
	}


	// Menú de acciones
	public void acciones(){
		int accion = 0;
		boolean ejecutando = true;
		
		while(ejecutando){
			accionesMostrarMenu();
			accion = accionesElegirAccion();
			ejecutando = accionesEjecutarAccion(accion);
			accionesEnterParaContinuar(ejecutando);
		}
	}

	// Muestra el menú
	private void accionesMostrarMenu() {
		String lista = "";
		
		for(int i=0; i<this.acciones.length; i++)
			lista += String.format("  [%s]\t%s\n", (i+1), this.acciones[i]);
		
		System.out.println("--[Acciones]-----------");
		System.out.print(lista);
		System.out.println("-----------------------\n");
	}
	// Elegir accion
	private int accionesElegirAccion() {
		return enteroDeNextLine("Acción", true);
	}
	// Ejecutar accion
	private boolean accionesEjecutarAccion(int accion) {
		switch(accion){
			case 1: lista(); break;
			case 2: insertar(); break;
			case 3: modificar(accionesPedirId()); break;
			case 4: eliminar(accionesPedirId()); break;
			case 5: buscar(accionesPedirBusqueda()); break;
			case 6: informacion(accionesPedirId()); break;
			case 7: return false;
			default: System.out.println("[Acción no válida]\n"); break;
		}
		return true;
	}
	// Requiere un "Enter" Para continuar
	private void accionesEnterParaContinuar(boolean ejecutando) {
		System.out.println(String.format("[Enter para %s]", ejecutando ? "continuar" : "finalizar"));
		teclado.nextLine();
	}
	// Pedir ID de la Pelicula sobre la que se actua
	private int accionesPedirId(){
		return enteroDeNextLine("ID de la película", true);
	}
	// Pedir String para realizar búsqueda
	private String accionesPedirBusqueda(){
		System.out.print("Buscar en títulos => ");
		
		String busqueda = teclado.nextLine();
		System.out.println();
		
		return busqueda;
	}
	
	// Devuelve un entero de un nextLine o vuelve a pedirlo
	private int enteroDeNextLine(String titulo){
		return enteroDeNextLine(titulo, false);
	}
	private int enteroDeNextLine(String titulo, boolean linea){
		while(true){
			int entero;
			
			System.out.print(titulo + " => ");
			
			try{
				entero = Integer.parseInt(teclado.nextLine());
			}catch(Exception e){
				continue;
			}
			
			if(linea) System.out.println();
			
			return entero;
		}
	}
	// Devuelve un entero de un nextLine o devuelve por defecto
	private int enteroDeNextLine(String titulo, int porDefecto){
		while(true){
			int entero;
			
			System.out.print(titulo + " => ");
			
			try{
				entero = Integer.parseInt(teclado.nextLine());
			}catch(Exception e){
				entero = porDefecto;
			}
			
			return entero;
		}
	}
}