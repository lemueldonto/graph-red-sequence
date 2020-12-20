package graph;

import models.IEdge;
import models.IVertex;
import models.tools.Color;

public class Vertex implements IVertex {
    Graph fromGraph;
    String tag;
    Color color;

    Vertex(Graph fromGraph, String tag, Color color){
        this.tag = tag;
        this.fromGraph = fromGraph;
        this.color = color;
    }


    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return tag;
    }

    @Override
    public int compareTo(IVertex iVertex) {
        return 0;
    }
    @Override
    public int getNumberRedAdjacent() {
        int nb=0;
        Iterable<IVertex> adjacent=fromGraph.adjacents(this);
        for(IVertex vertex:adjacent)
        {
            if(vertex.getColor()==Color.RED)
                nb++;
        }
        return nb;
    }

    @Override
    public int getNumberBlueAdjacent() {
        int nb=0;
        Iterable<IVertex> adjacent=fromGraph.adjacents(this);
        for(IVertex vertex:adjacent)
        {
            if(vertex.getColor()==Color.RED)
                nb++;
        }
        return nb;
    }

}
