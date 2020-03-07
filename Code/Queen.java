package Code;

public class Queen extends Piece{
    Queen(boolean pieceColor){
        super(pieceColor);

    }

    @Override
    public String getName() {
        return "Queen";
    }

    @Override
    public PossibleMoves[] getMoves()  {
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
        return false;
    }
}
