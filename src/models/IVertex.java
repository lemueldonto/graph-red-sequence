package models;

import models.tools.Color;

public interface IVertex extends Comparable<IVertex>{

    public String getTag();

    public Color getColor();

    public void setColor(Color color);
    public int getNumberRedAdjacent();
    public int getNumberBlueAdjacent();
    public int numberOfRedToBlue();
    public int numberOfBlueToRed();
    public int finalNumberOfBlueToChange();
}
