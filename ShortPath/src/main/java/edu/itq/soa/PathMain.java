package edu.itq.soa;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex>
{
       public final String name;
       public Edge[] adjacencies;
       public double minDistance = Double.POSITIVE_INFINITY;
       public Vertex previous;
       public Vertex(String argName) { name = argName; }
       public String toString() { return name; }
       public int compareTo(Vertex other)
       {
           return Double.compare(minDistance, other.minDistance);
       }

}


class Edge
{
       public final Vertex target;
       public final double weight;
       public Edge(Vertex argTarget, double argWeight)
       { target = argTarget; weight = argWeight; }
}

public class PathMain
{
       public static void computePaths(Vertex source)
       {
           source.minDistance = 0.;
           PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
       vertexQueue.add(source);

       while (!vertexQueue.isEmpty()) {
           Vertex u = vertexQueue.poll();

               // Visita cada borde
               for (Edge e : u.adjacencies)
               {
                   Vertex v = e.target;
                   double weight = e.weight;
                   double distanceThroughU = u.minDistance + weight;
           if (distanceThroughU < v.minDistance) {
               vertexQueue.remove(v);

               v.minDistance = distanceThroughU ;
               v.previous = u;
               vertexQueue.add(v);
           }
               }
           }
       }

       public static List<Vertex> getShortestPathTo(Vertex target)
       {
           List<Vertex> path = new ArrayList<Vertex>();
           for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
               path.add(vertex);

           Collections.reverse(path);
           return path;
       }

       public static void main(String[] args)
       {
           // Todos los vertices van aqui
           Vertex A = new Vertex("A");
           Vertex B = new Vertex("B");
           Vertex D = new Vertex("D");
           Vertex F = new Vertex("F");
           Vertex K = new Vertex("K");
           Vertex J = new Vertex("J");
           Vertex M = new Vertex("M");
           Vertex O = new Vertex("O");
           Vertex P = new Vertex("P");
           Vertex R = new Vertex("R");
           Vertex Z = new Vertex("Z");

           // Declara bordes y su costo
           A.adjacencies = new Edge[]{ new Edge(M, 8) };
           B.adjacencies = new Edge[]{ new Edge(D, 11) };
           D.adjacencies = new Edge[]{ new Edge(B, 11) };
           F.adjacencies = new Edge[]{ new Edge(K, 23) };
           K.adjacencies = new Edge[]{ new Edge(O, 40) };
           J.adjacencies = new Edge[]{ new Edge(K, 25) };
           M.adjacencies = new Edge[]{ new Edge(R, 8) };
           O.adjacencies = new Edge[]{ new Edge(K, 40) };
           P.adjacencies = new Edge[]{ new Edge(Z, 18) };
           R.adjacencies = new Edge[]{ new Edge(P, 15) };
           Z.adjacencies = new Edge[]{ new Edge(P, 18) };


           computePaths(A); // Ejecuta Main
           System.out.println("Distancia de: " + Z + ": " + Z.minDistance); //Punto al que se quiere llegar
           List<Vertex> path = getShortestPathTo(Z);
           System.out.println("Camino: " + path);
       }
}