package edu.itq.ia;

import java.io.*;

public class backtrack 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int x, y;
        boolean solucion;
        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(System.in));
        try 
        {
            System.out.println("Posicion inicial del caballo. ");
            System.out.print(" x = ");
            x = Integer.parseInt(entrada.readLine());
            System.out.print(" y = ");
            y = Integer.parseInt(entrada.readLine());
            Caballo miCaballo = new Caballo(x, y);
            solucion = miCaballo.resolverProblema();
            if (solucion) 
            {
                miCaballo.escribirTablero();
            }
        } 
        catch (Exception m) 
        {
            System.out.println("No se pudo probar el algoritmo, " + m);
        }
    }

}