package models;

import models.tools.Color;

public interface IVertex extends Comparable<IVertex>{

    public String getTag();

    public Color getColor();

    public void setColor(Color color);
    public int getNumberRedAdjacent();
    public int getNumberBlueAdjacent();
}
