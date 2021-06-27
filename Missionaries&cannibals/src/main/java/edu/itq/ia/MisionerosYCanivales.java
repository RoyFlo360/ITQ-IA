package edu.itq.ia;

import java.util.LinkedList;

import java.util.Vector;

public class MisionerosYCanivales 
{
   static Estado estado_inicial = new Estado(3,3,'L',0,0);
   public static void main(String[] args) 
   {
      Node node = solucion( estado_inicial );
      System.out.println();
      if (node == null)
         System.out.println("No existen soluciones.");
      else 
      {
         System.out.println("La solucion es:\n");
         node.printBackTrace();
      }
      System.out.println();
   }
   
   static class Estado 
   {
      int ml, cl;  // Misioneros y Canibales en la izquierda
      int mr, cr;  // Misioneros y Canibales en la derecha
      char bote;   // Posicion del bote, 'L' or 'R' L = Izquierda y R = Derecha
      public Estado(int ml, int cl, char bote, int mr, int cr) 
      {
         this.ml = ml;
         this.cl = cl;
         this.bote = bote;
         this.mr = mr;
         this.cr = cr;
      }
      public boolean meta_final() 
      {
         return ml == 0 && cl == 0;
      }
      public String toString() 
      {
         return "(" + ml + " " + cl + " " + bote + " " + mr + " " + cr + ")";
      }
      public boolean equals(Object obj) 
      {
         if ( ! (obj instanceof Estado) )
            return false;
         Estado s = (Estado)obj;
         return (s.ml == ml && s.cl == cl && s.bote == bote
                       && s.cr == cr && s.mr == mr);
      }
      public Vector<EstadoActionPair> successor_function() 
      {
         Vector<EstadoActionPair> v = new Vector<EstadoActionPair>();
         if (bote == 'L') 
         {
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml-2,cl,'R',mr+2,cr),
                          new Action("Dos Misioneros cruzan de izquierda a derecha.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml,cl-2,'R',mr,cr+2),
                          new Action("Dos Canibales cruzan de izquierda a derecha.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml-1,cl-1,'R',mr+1,cr+1),
                          new Action("Un Misionero y un Canibal cruzan de izquierda a derecha.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml-1,cl,'R',mr+1,cr),
                          new Action("Un Misionero cruza de izquierda a derecha.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml,cl-1,'R',mr,cr+1),
                          new Action("Un Canibal cruza de izquierda a derecha.")));
         }
         else 
         {
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml+2,cl,'L',mr-2,cr),
                          new Action("Dos Misioneros cruzan de derecha a izquierda.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml,cl+2,'L',mr,cr-2),
                          new Action("Dos Canibales cruzan de derecha a izquierda.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml+1,cl+1,'L',mr-1,cr-1),
                          new Action("Un Misionero y un Canibal cruzan de derecha a izquierda.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml+1,cl,'L',mr-1,cr),
                          new Action("Un Misionero cruza de derecha a izquierda.")));
            testAndAdd(v,new EstadoActionPair(
                          new Estado(ml,cl+1,'L',mr,cr-1),
                          new Action("Un Canibal cruza de derecha a izquierda.")));
         }
         return v;
      }
      private void testAndAdd(Vector<EstadoActionPair> v, EstadoActionPair pair) 
      {
         Estado Estado = pair.Estado;
         if (Estado.ml >= 0 && Estado.mr >= 0 && Estado.cl >= 0 && Estado.cr >= 0
               && (Estado.ml == 0 || Estado.ml >= Estado.cl)
               && (Estado.mr == 0 || Estado.mr >= Estado.cr))
            v.addElement(pair);
      }
   } //Fin de la clase Estado
   

   static class Action 
   {
      String text;
      public Action(String text) 
      {
         this.text = text;
      }
      public String toString() 
      {
         return text;
      }
      public double cost() 
      {
         return 1;
      }
   }

   static class EstadoActionPair 
   {
      public Estado Estado;
      public Action action;
      public EstadoActionPair(Estado Estado, Action action) 
      {
         this.Estado = Estado;
         this.action = action;
      }
   }
   

   static class Node 
   {
      public Estado Estado;
      public Node parent_node;
      public Action action;
      public double path_cost;
      public int depth;
      public Node(Estado Estado) 
      {
         this.Estado = Estado;
         parent_node = null;
         action = new Action("Estado inicial");
         path_cost = 0;
         depth = 0;
      }
      public Node(Estado Estado, Node parent, Action action) 
      {
         this.Estado = Estado;
         this.parent_node = parent;
         this.action = action;
         this.path_cost = action.cost() + parent.path_cost;
         this.depth = 1 + parent.depth;
      }
      public void printBackTrace() 
      {
         if (parent_node != null)
            parent_node.printBackTrace();
         System.out.println("   " + depth + ". " + action + " ---> " + Estado);
      }
   }
   
   
   public static Node solucion(Estado estado_inicial) 
   {
      LinkedList<Node> fringe = new LinkedList<Node>();
      Vector<Estado> visited = new Vector<Estado>();
      fringe.add( new Node(estado_inicial) );
      while ( true ) 
      {
         if (fringe.isEmpty())
            return null;
         Node node = (Node)fringe.removeFirst();  // or change to removeLast
         Vector<EstadoActionPair> successors = node.Estado.successor_function();
         for (int i = 0; i < successors.size(); i++) 
         {
            EstadoActionPair successor = (EstadoActionPair)successors.elementAt(i);
            if ( ! containsEstado(visited,successor.Estado) ) 
            {
               Node newNode = new Node(successor.Estado,node,successor.action);
               if (successor.Estado.meta_final())
                  return newNode;
               fringe.add(newNode);
               visited.add(successor.Estado);
            }
         }
      }
   }
   
   public static boolean containsEstado(Vector<Estado> visitedEstados, Estado Estado) 
   {
      for (int i = 0; i < visitedEstados.size(); i++) 
      {
         if (visitedEstados.elementAt(i).equals(Estado))
            return true;
      }
      return false;
   }

}

