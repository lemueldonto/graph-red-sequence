package graph;

import exception.BadInputGraphException;
import models.IVertex;
import models.tools.Color;

import java.util.Scanner;

public class GraphBuilder {
    public static Graph D1 = graph("A E B D B F C E D F F C F E G A G B G C");
    public static Graph D2 = graph("A C A E B D D F D G E C F B");
    public static Graph D3 = graph("A C B D C E C G D A D F E A F B");

    public static Graph graph(String input) {
        Graph G = new Graph();
        readGraph(G,input);
        return G;
    }

    private static void readGraph(Graph G, String input) {
        Scanner scanner = new Scanner(input);
        readGraph(G,scanner);
        scanner.close();
    }


    private static void readGraph(Graph G, Scanner input) {
        String u, v;
        int cu, cv, c;
        while ( input.hasNext() ) {
            u = input.next();
            if ( input.hasNextInt() )
                cu = input.nextInt();
            else
                throw new BadInputGraphException("");


            if ( input.hasNext() )
                v = input.next();
            else
                throw new BadInputGraphException("");
            if ( input.hasNextInt() )
                cv = input.nextInt();
            else
                throw new BadInputGraphException("");


            if ( input.hasNextInt() )
                c = input.nextInt();
            else
                throw new BadInputGraphException("");
            if (c != 0 && c!= 1) throw new BadInputGraphException("");
            IVertex uu;
            if(cu == 0) {
                 uu = addVertex(G,u,Color.BLUE);
            } else {
                 uu = addVertex(G,u,Color.RED);
            }
            IVertex vv;
            if(cv == 0) {
                vv = addVertex(G,v,Color.BLUE);
            } else {
                vv = addVertex(G,v,Color.RED);
            }
            if(c == 0) G.addEdge(uu, vv, Color.BLUE);
            else G.addEdge(uu, vv, Color.RED);
        }
    }

    private static IVertex addVertex(Graph G, String u, Color c) {
        IVertex v = G.getVertex(u);
        if ( v == null )
            return G.addVertex(u, c);
        return v;
    }
}
