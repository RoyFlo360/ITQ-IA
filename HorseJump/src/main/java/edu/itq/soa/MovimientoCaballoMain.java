package edu.itq.soa;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MovimientoCaballoMain 
{

    static final int N = 8;
    static final int n = (N + 1);
    private int[][] tablero = new int[n][n];
    private boolean exito;
    private int[][]salto = {{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-1},{2,-1}};
    private int x0,y0;
    
    public MovimientoCaballoMain(int x, int y) throws Exception
    {
        if((x<1)||(x>N) || (y<1)||(y>N))
        {
            throw new Exception("Coordenadas fuera de rango");
        }
        x0 = x;
        y0 =y;
        for (int i = 1; i <= N; i++) 
        {
            for (int j = 1; j <= N; j++) 
            {
                tablero[i][j] = 0;
            }
        }
        tablero[x0][y0] = 1;
        exito = false;
    }
    
    public boolean Solucion()
    {
        saltoC(x0,y0,2);
        return exito;
    }
    
    public void saltoC(int x, int y, int i)
    {
        int nx, ny, k =0;
        do{
            k++;
            nx = x + salto[k-1][0];
            ny = y + salto[k-1][1];
            
            if((nx>=1)&&(nx<=N) && (ny>=1) && (ny <=N) &&(tablero[nx][ny]== 0))
            {
                tablero[nx][ny] = i;
                if(i<N*N)
                {
                    saltoC(nx, ny, i+1); 
                    if(!exito)
                    { 
                        tablero[nx][ny] = 0; 
                    }
                }
                else
                {
                    exito = true;
                }
            }
        }while ((k<8) && !exito);
    }
    
    void tablero()
    {
        for (int i = 1; i <= N; i++) 
        {
            for (int j = 1; j <= N; j++) 
            {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception 
    {
       int x,y;
       boolean sol;
       BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
       try
       {
           System.out.println("Posicion inicial del caballo (El tablero es de 8x8)");
           System.out.print("x = ");
           x = Integer.parseInt(entrada.readLine());
           System.out.print("y = ");
           y = Integer.parseInt(entrada.readLine());
           MovimientoCaballoMain caballo = new MovimientoCaballoMain(x,y);
           sol = caballo.Solucion();
           if(sol)
           {
               caballo.tablero();
           }
       } catch(Exception m)
       {
           System.out.println("No se pudo comprobar el algoritmo" + m);
       }
    }
    
}
