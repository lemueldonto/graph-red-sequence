package algorithms;

import graph.Graph;
import graph.GraphBuilder;

public class FirstRedSequenceAlgo {
    private int k = 0;

    private static void run(Graph G){
        System.out.println(G);
        G.removeVertex(G.getVertex("C"));
        System.out.println(G);
    }


    public static void main(String[] args) {
        System.out.println("-------------------------------\n");
        //System.out.println(GraphBuilder.G);
        run(GraphBuilder.G);
    }
}
