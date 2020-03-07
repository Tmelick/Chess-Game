package Code;

public enum PossibleMoves
{
    RIGHT(1, 0),
    UP (0,1),
    UP_RIGHT(1, 1),
    DOWN_RIGHT(1, -1),
    LEFT(-1, 0),
    UP_LEFT(-1, 1),
    DOWN_LEFT(-1, -1),
    DOWN(0,-1),

    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_RIGHT_UP(2, 1),

    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_DOWN_RIGHT(1, -2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_LEFT_DOWN(-2, -1),



    DOUBLE_UP(0, 2),
    DOUBLE_DOWN(0, -2);



    private int x;
    private int y;


    public int getX(){return this.x;}

    public int getY(){return this.y;}

     PossibleMoves(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
