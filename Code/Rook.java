package Code;

public class Rook extends Piece {

    Rook(boolean pieceColor){
        super(pieceColor);
    }

    @Override
    public String getName() {
        return "Rook";
    }

    @Override
    public PossibleMoves[] getMoves()  {
        return new PossibleMoves[]{
                PossibleMoves.UP,
                PossibleMoves.RIGHT,
                PossibleMoves.DOWN,
                PossibleMoves.LEFT,
                PossibleMoves.UP_RIGHT,
                PossibleMoves.UP_LEFT,
                PossibleMoves.DOWN_RIGHT,
                PossibleMoves.DOWN_LEFT
        };
    }

    @Override
    public boolean usesSingleMove() {
        return false;
    }
}
