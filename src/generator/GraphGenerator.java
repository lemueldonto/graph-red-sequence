package generator;
import graph.*;
import models.IVertex;
import models.tools.Color;

import javax.crypto.spec.IvParameterSpec;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GraphGenerator {

    /**
     * Return a complete oriented symetric red-blue graph
     * @param size the size of the graph
     * @param perVertexRed the percentage of chance for each vertex to be red
     * @param perEdgeBlue the percentage of chance for each vertex to be blue
     * @return a graph instance
     */
    public static Graph makeCompleteOrientedSymetricGraph(int size, int perVertexRed, int perEdgeBlue){
        if(size <= 0) throw new IllegalArgumentException("Size must be positive");
        if(perEdgeBlue > 100 || perEdgeBlue < 0) throw new IllegalArgumentException("Edge color percentage must be between 0 and 100");
        if(perVertexRed > 100 || perVertexRed < 0) throw new IllegalArgumentException("Vertex color percentage must be between 0 and 100");

        Graph graph = new Graph();

        for(int i=0; i<size; i++){
            Random rand = new Random();
            Color vertexColor =  rand.nextInt(100) > perVertexRed ? Color.BLUE : Color.RED;

            graph.addVertex(Integer.toString(i), vertexColor);
        }
        Queue<IVertex> vertices = new LinkedList<>(graph.getVertices());

        for(int i =0; i<vertices.size(); i++){
            Vertex tmpVertex = (Vertex) vertices.poll();

            for(IVertex vertex : vertices){
                Random rand = new Random();
                Color EdgeColor =  rand.nextInt(100) > perEdgeBlue ? Color.RED : Color.BLUE;

                graph.addEdge(tmpVertex, vertex, EdgeColor);
            }
            vertices.offer(tmpVertex);
        }

        return graph;
    }
}
