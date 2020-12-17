package algorithms;

import graph.Graph;
import graph.GraphBuilder;
import models.IVertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FirstRedSequenceAlgo {


    public FirstRedSequenceAlgo() { }

    public static ArrayList<IVertex> getSortedList (ArrayList<IVertex> list, int factor) { // triage par ordre dcroissant
        ArrayList<IVertex> tmp=new ArrayList<>(list);
        Comparator<IVertex> comparator;
        if(factor==0)
            comparator =Comparator.comparing(IVertex::getNumberRedAdjacents);
        else if(factor==1)
            comparator =Comparator.comparing(IVertex::numberOfRedToBlue);
        else
            comparator =Comparator.comparing(IVertex::getNumberOfBlueEdge);
        Collections.sort(tmp,comparator.reversed());
        return tmp;
    }

    public static void run (Graph graph){
        int i = 0;
        while(graph.haveRedVertice())
        {
            i++;
            ArrayList<IVertex> sortedRedVertices=getSortedList(graph.getAllRedVertice(),0);
             // Il existe au moins un sommet qui a des sommets adjacents rouges
               sortedRedVertices=getSortedList(sortedRedVertices,1);
                int max = sortedRedVertices.get(0).numberOfRedToBlue(); // trier par le sommet qui change le plus de sommet rouge en bleu
               if(max>0) {
                   graph.removeVertex(sortedRedVertices.get(0));
               }
               else {
                   sortedRedVertices=getSortedList(sortedRedVertices,2);
                   if(sortedRedVertices.get(0).getNumberOfBlueEdge()>0)
                       graph.removeVertex(sortedRedVertices.get(0)); // on supprime celui qui a le plus d'arrete bleu
                   else
                   {
                       sortedRedVertices=getSortedList(sortedRedVertices,0);
                       graph.removeVertex(sortedRedVertices.get(sortedRedVertices.size()-1));
                   }
               }
            System.out.println(" ITERATION  N° "+i);
            System.out.println(graph);
        }
    }
    public static void main(String[] args) {
        System.out.println("-------------------------------\n");
        System.out.println(GraphBuilder.G);
        Graph g = GraphBuilder.G;
        //System.out.println(g.getAllRedVertice());
        //System.out.println(getSortedList(g.getAllRedVertice(),1));
        run(GraphBuilder.G);

    }
}
