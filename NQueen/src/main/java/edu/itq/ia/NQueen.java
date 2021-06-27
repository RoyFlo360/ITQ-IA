package edu.itq.ia;

public class NQueen {
	 
    int reinas;
    boolean flag;
    Tablero Tablero;
	private int cont;
 
    public NQueen(int reinas) 
    {
        this.flag = false;
        this.Tablero = new Tablero(reinas);;
        this.reinas = reinas;
    }
 
    void solucionNqueen()
    {
 
        @SuppressWarnings("unused")
		int i;
        //Empezando desde(0,0) del Tablero
        hasSolution(0,0);
 
        if(!flag)
            System.out.println("No Solution");
 
    }
 
    boolean hasSolution(int ctr, int colQueen)
    {
        if(colQueen == reinas)
        {
        	cont = cont + 1;
            flag = true;
            Tablero.displayTablero();
            System.out.print("Numero de solucion: "+cont+"\n\n");
            return false;
        }
		int i;
        for(i=ctr; i<reinas; i++)
        {
 
            //Revisando si es una posicion segura
            if(Tablero.isValidPlace(i,colQueen))
            {
                Tablero.Tablero[i][colQueen] = 1;
                if(hasSolution(0,colQueen+1))
                    return true;
 
                //backtrack
                Tablero.Tablero[i][colQueen] = 0;
            }
 
        }
        return false;
    }
}