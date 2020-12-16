package models;

import models.tools.Color;

public interface Edge {

    public Vertex origin();

    public Vertex destination();

    public Color getColor();

    public void setColor(Color color);
}
