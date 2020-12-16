package models;

import models.tools.Color;

public interface IEdge {

    public IVertex origin();

    public IVertex destination();

    public Color getColor();

    public void setColor(Color color);
}
