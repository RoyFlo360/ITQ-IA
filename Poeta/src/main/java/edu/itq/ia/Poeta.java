package edu.itq.ia;

import java.io.*;
import java.util.*;

public class Poeta 
{
    public static List<String> l = new ArrayList<String>();
	private static BufferedReader bf;
    
    public static String leeTexto(String d)
    {
        String t =" ";
        try{
            bf = new BufferedReader(new FileReader(d));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null) 
            {
            temp = temp + bfRead + '$';
            }
            t =temp;
        }
        catch (Exception ex) 
        {
            System.err.println("No existe el archivo");	
        }
        return t;
    }
    public static void main(String[] args) 
    {
        String archivo = leeTexto("C:\\Users\\Rodrigo\\\\ITQ\\IA\\poema.txt"), s;
        int i=-1;
        char c;
        while(i<archivo.length()-1)
        {
            s=" ";
            i++;
            c=archivo.charAt(i);
        while(c!=' '&&c!='$')
        {
            s=s+Character.toString(c);
            i++;
            if(i<archivo.length()-1)
            {
            c=archivo.charAt(i);
            }
            else
            {
                break;
            }
        }
            l.add(s);
        }
        Collections.shuffle(l);
        for(int j=0;j<l.size();j++)
        {                
        	if(j%5==0)
            {
        		System.out.println();
            }
            System.out.print(l.get(j)+" ");
        }
        System.out.println();
    }
    
}