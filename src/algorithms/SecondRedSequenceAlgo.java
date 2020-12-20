package algorithms;

import graph.Graph;
import graph.GraphBuilder;
import models.IVertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class SecondRedSequenceAlgo {



    public static ArrayList<IVertex> sort(ArrayList<IVertex> list){
        ArrayList<IVertex> tmp = new ArrayList<>(list);
        Comparator<IVertex> comparator;
        comparator = Comparator.comparing(IVertex::finalNumberOfBlueToChange);
        tmp.sort(comparator.reversed());
        return tmp;
    }

    public static IVertex getFavoriteVertex(ArrayList<IVertex> list){
        list = sort(list);
        Iterator<IVertex> it = list.iterator();
        IVertex last = null; IVertex next; IVertex actu;
        if (it.hasNext()) last = it.next();
        while (it.hasNext()){
            next = it.next();
            if(last.finalNumberOfBlueToChange() == next.finalNumberOfBlueToChange()
                    && last.numberOfRedToBlue() > next.numberOfRedToBlue()){
                actu = last;
                last = next;
                next = actu;
            }
        }
        return list.get(0);
    }

    public static void run (Graph graph){
        int i = 0;
        while(graph.haveRedVertice()) {
            System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@00 \n");
            i++;
            IVertex vertex = getFavoriteVertex(graph.getAllRedVertices());
            System.out.println("ITERATION  N° " + i);
            System.out.println("SUPPRESSION DU SOMMET : " + vertex);
            graph.removeVertex(vertex);
            System.out.println(graph);
        }
    }

    public static void main(String[] args) {
        System.out.println("-------------------------------\n");
        System.out.println(GraphBuilder.G1);
        Graph g = GraphBuilder.G1;
        run(GraphBuilder.G1);

    }
}
