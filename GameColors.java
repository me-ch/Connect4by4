//Juan Pablo Carrillo
//jpc15e
import java.awt.*;

public class GameColors {
    public static Color player1Color = Color.RED;
    public static Color player4Color = Color.GREEN;
    public static Color player3Color = Color.YELLOW;
    public static Color player2Color = Color.BLUE;
    public static Color boardColor = Color.LIGHT_GRAY;
    public static Color holeColor = Color.WHITE;

    public static Color getColor(int player)
    {
        switch(player) {
            case 1: return player1Color;
            case 2: return player2Color;
            case 3: return player3Color;
            case 4: return player4Color;
            default: return holeColor;
        }
    }
}
