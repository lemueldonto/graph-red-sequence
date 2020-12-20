package algorithms;

import generator.GraphGenerator;
import graph.Graph;
import graph.GraphBuilder;
import models.IVertex;

import java.util.ArrayList;
import java.util.Comparator;

public class FirstRedSequenceAlgo {

    public FirstRedSequenceAlgo() { }

    public static ArrayList<IVertex> sort(ArrayList<IVertex> list) {
        ArrayList<IVertex> tmp = new ArrayList<>(list);
        Comparator<IVertex> comparator;
        comparator = Comparator.comparing(IVertex::numberOfBlueToRed);
        tmp.sort(comparator.reversed());
        return tmp;
    }

    public static IVertex getFavoriteVertex(ArrayList<IVertex> list){
        list = sort(list);
        IVertex favoriteVertex = list.get(0);
        for (IVertex vertex : list) {
            if (favoriteVertex.numberOfRedToBlue() > vertex.numberOfRedToBlue()){
                favoriteVertex = vertex;
            }
        }
        return favoriteVertex;
    }

    public static int run (Graph graph){
        int i = 0;
        while(graph.haveRedVertice()) {
           // System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@00 \n");
            System.out.println(" Running...");
            i++;
            IVertex vertex = getFavoriteVertex(graph.getAllRedVertices());
            //System.out.println("ITERATION  N° " + i);
            //System.out.println("SUPPRESSION DU SOMMET : " + vertex);
            graph.removeVertex(vertex);
            //System.out.println(graph);
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
        System.out.println("-------------------------------\n");
        //System.out.println(GraphBuilder.G1);
        //Graph g = GraphBuilder.G1;
        //int it = run(GraphBuilder.G1);
        average();

    }


}
