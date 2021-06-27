package edu.itq.ia;

public class Main 
{

    private Tablero Tablero = new Tablero();
    int sol=Heuristic(Tablero);
    private Tablero [] solution= new Tablero[sol+1];
    private int [] direcciones = Tablero.getdirecciones();
          
    public Main() {
        for (int i = 0; i < solution.length; i++) 
        {
            solution[i] = new Tablero();
        }
    }
      
    public static void main(String[] args) 
    {
        Main solitaer = new Main();
        long t1 = System.currentTimeMillis();
        if (solitaer.finalSol(1)) 
        {
            System.out.println("Solución encontrada en: " + (System.currentTimeMillis() - t1) + " [ms]");
            solitaer.solvePuzzle();
        } else {
            System.out.println("No se encontro solición");
        }
        solitaer.printPath();
    }
  
    public boolean finalSol(int step) 
    {
        for (int x = 0; step <= sol && x < 7; x++) 
        {
            for (int y = 0; y < 7; y++) 
            {
                for (int direccion : direcciones) 
                {
                    if (Tablero.salto(x, y, direccion)) 
                    {
                       	Tablero.copy(Tablero, solution[step]);
                   	if (!((Heuristic(Tablero)==0) && Tablero.estaOCUPADO(3, 3)))
                   	{
                            if ( finalSol(step + 1)) 
                            {
                                return true;
                            } 
                            else 
                            {
                                Tablero.goBack(x, y, direccion);
                            }
                        } 
                   		else 
                   		{
                            return true;
                   		}
                    }
                }
            }                      
        }                  
        return false;
    }
  
    public int Heuristic(Tablero Tablero) {//calculate Heuistic Cost
        int cost = 0;
        for (int x = 0; x < 7; x++) 
        {
            for (int y = 0; y < 7; y++) 
            {
            	if(Tablero.Tablero[x][y]==1)
            	{
                    cost++;
                }
            }
        }
        return cost-1;
    }
          
    int a, b, count;
    public void printPath()
    {
        Tablero[] pathTablero=solution;
        for(int i=0;i<pathTablero.length-1;i++)
        {
            count=0;
            b=-1;
            a=0;
            for (int x = 0; x < 7; x++) 
            {
                for (int y = 0; y < 7; y++) 
                {
                    if(pathTablero[i].Tablero[x][y]==1&&pathTablero[i+1].Tablero[x][y]==2)
                    {
            		if(x-2>=0)
            		{
               		    if(pathTablero[i].Tablero[x-2][y]!=pathTablero[i+1].Tablero[x-2][y])
               		    {
               		    	b=Tablero.numberTablero[x-2][y];
               		    }
            		}
            		
            		if(x+2<=6)
            		{
            			if(pathTablero[i].Tablero[x+2][y]!=pathTablero[i+1].Tablero[x+2][y])
            			{
            				b=Tablero.numberTablero[x+2][y];
            			}
            		}
        		 
        		if(y-2>=0)
        		{
        			if(pathTablero[i].Tablero[x][y-2]!=pathTablero[i+1].Tablero[x][y-2])
                    {
      			        b=Tablero.numberTablero[x][y-2];
    			    }
        		}
    		
    			if(y+2<=6)
    			{
    				if(pathTablero[i].Tablero[x][y+2]!=pathTablero[i+1].Tablero[x][y+2])
                    {
    					b=Tablero.numberTablero[x][y+2];
                    }
    			}
    			
            	if(b!=-1)
            	{
            		a=Tablero.numberTablero[x][y];
                    count=count+1;
                    break;
            	}
                    }
                    if(count==1)break;
                }  
                if(count==1)break;

            }
            System.out.print("<"+a+","+b+">"+" ");
        }
    }
    
    private void solvePuzzle() 
    {
        for (int i = 0; i < solution.length; i++) 
        {
            solution[i].print();
        }
    }

}