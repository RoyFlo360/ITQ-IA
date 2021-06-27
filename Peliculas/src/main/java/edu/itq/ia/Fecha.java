package edu.itq.ia;

public class Fecha 
{
   private int mes;   // 1-12
   private int dia;   // 1-31 con base en el mes
   private int anio;  // cualquier a�o

   private static final int[] diasPorMes = // d�as en cada mes  
      { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

   // constructor: llama a comprobarMes para confirmar el valor apropiado para el mes; 
   // llama a comprobarDia para confirmar el valor apropiado para el d�a
   public Fecha( int elMes, int elDia, int elAnio )
   {
      mes = comprobarMes( elMes ); // valida el mes
      anio = elAnio; // pudo validar el a�o
      dia = comprobarDia( elDia ); // valida el d�a

      //System.out.printf("[Constructor de objeto Fecha para la fecha %s]\n", this );
   } // fin del constructor de Fecha

   // m�todo utilitario para confirmar el valor apropiado del mes
   private int comprobarMes( int mesPrueba )
   {
      if ( mesPrueba > 0 && mesPrueba <= 12 ) // valida el mes
         return mesPrueba;
      else // mes es inv�lido
         throw new IllegalArgumentException ( "el mes debe ser 1 a 12"); 
   } // fin del m�todo comprobarMes

   // m�todo utilitario para confirmar el valor apropiado del d�a, con base en el mes y el a�o
   private int comprobarDia( int diaPrueba )
   {
      // comprueba si el d�a est� dentro del rango para el mes
      if ( diaPrueba > 0 && diaPrueba <= diasPorMes[ mes ] )
         return diaPrueba;

      // comprueba si es a�o bisiesto
      if ( mes == 2 && diaPrueba == 29 && ( anio % 400 == 0 || 
           ( anio % 4 == 0 && anio % 100 != 0 ) ) )
         return diaPrueba;

      throw new IllegalArgumentException(
         "dia fuera de rango para el mes y anio especificados" );
   } // fin del m�todo comprobarDia

   // devuelve un objeto String de la forma mes/dia/anio
   public String toString()
   { 
      return String.format( "%d/%d/%d", mes, dia, anio ); 
   } // fin del m�todo toString
   
   public void aumentarDia()
   {
	   int diaDelMes = diasPorMes[this.mes];
	   
	   this.dia++;
	   
	   this.mes += this.dia / (diaDelMes + 1);
	   if( this.dia == (diaDelMes + 1)) this.dia = 1;
	   
	   this.anio += this.mes / diasPorMes.length;
	   
	   if(this.mes == diasPorMes.length) this.mes = 1;   
   }

	public int getMes() 
	{
		return mes;
	}
	
	public int getDia() 
	{
		return dia;
	}
	
	public int getAnio() 
	{
		return anio;
	}
	
	public static Fecha nuevaFecha(int elMes, int elDia, int elAnio)
	{
		Fecha devolver;
		try{
			
			devolver = new Fecha(elMes, elDia, elAnio);
		}
		catch(Exception e)
		{
			//System.out.println("Fecha no v�lida: [" + e.getMessage() + "]");
			return new Fecha(1,1,1970);
		}
		return devolver;
	}
} // fin de la clase Fecha