package graph;

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
}
