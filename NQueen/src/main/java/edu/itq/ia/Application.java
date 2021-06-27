package edu.itq.ia;

import java.util.Scanner;

public class Application 
{
    static Scanner in = new Scanner(System.in);
    public static void main(String args[])
    {
        System.out.println("Inserte numero de Reinas");
        int reinas = in.nextInt();
        NQueen nQueen = new NQueen(reinas);
        nQueen.solucionNqueen();
 
    }
}