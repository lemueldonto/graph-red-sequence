package algorithms;

import generator.GraphGenerator;
import graph.Graph;
import graph.GraphBuilder;
import models.IVertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ThirdRedSequenceAlgo {
    public static ArrayList<IVertex> sort(ArrayList<IVertex> list){
        ArrayList<IVertex> tmp = new ArrayList<>(list);
        Comparator<IVertex> comparator;
        comparator = Comparator.comparing(IVertex::finalNumberOfBlueToChange);
        tmp.sort(comparator.reversed());
        return tmp;
    }

    public static IVertex hasNullVertex(ArrayList<IVertex> list){
        Iterator<IVertex> it = list.iterator();
        IVertex next;
        while (it.hasNext()){
            next = it.next();
            if(next.numberOfRedToBlue() == 0 && next.numberOfBlueToRed() == 0) return next;
        }
        return null;
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

    public static int run (Graph graph){
        int i = 0;
        while(hasNullVertex(graph.getAllRedVertices()) != null){
            IVertex vertex = null;
            i++;
            //System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@00 \n");
            vertex = hasNullVertex(graph.getAllRedVertices());
            //System.out.println("ITERATION  N° " + i);
            //System.out.println("SUPPRESSION DU SOMMET : " + vertex);
            graph.removeVertex(vertex);
            //System.out.println(graph);
        }
        while(graph.haveRedVertice()) {
            IVertex vertex = null;
                //System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@00 \n");
                vertex = getFavoriteVertex(graph.getAllRedVertices());
                //System.out.println("ITERATION  N° " + i);
                //System.out.println("SUPPRESSION DU SOMMET : " + vertex);
                graph.removeVertex(vertex);
                //System.out.println(graph);
            i++;
        }
        return i;
    }

    public static void average(){
        ArrayList list = new ArrayList();
        for (int k = 0; k<=10; k++){
            for(int j = 0 ; j<=10; j++){
                int it=0;
                for(int i = 0; i<100; i++){
                    it +=run(GraphGenerator.makeCompleteOrientedSymetricGraph(100,10*k,10*j));
                }
                list.add((double)it/100);
            }
            System.out.println(list);
            list.clear();
        }

    }

    public static void main(String[] args) {
        //System.out.println("-------------------------------\n");
        //System.out.println(GraphBuilder.G1);
        //Graph g = GraphBuilder.G1;
        //run(GraphBuilder.G1);
        average();
    }
}
