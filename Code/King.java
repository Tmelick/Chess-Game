package Code;

public class King extends Piece{
    King(boolean color){
    super(color);
    }

    @Override
    public String getName() {
        return "King";
    }

    @Override
    public PossibleMoves[] getMoves() {
        return new PossibleMoves[]{
                PossibleMoves.UP,
                PossibleMoves.UP_RIGHT,
                PossibleMoves.RIGHT,
                PossibleMoves.DOWN_RIGHT,
                PossibleMoves.DOWN,
                PossibleMoves.DOWN_LEFT,
                PossibleMoves.LEFT,
                PossibleMoves.UP_LEFT
        };
    }

    @Override
    public boolean usesSingleMove() {
        return true;
    }
}
