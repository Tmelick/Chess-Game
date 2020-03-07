package Code;

public class Bishop extends Piece{

    Bishop(boolean pieceColor){
        super(pieceColor);

    }

    @Override
    public String getName() {
        return "Bishop";
    }

    @Override
    public PossibleMoves[] getMoves() {
        return new PossibleMoves[]{

                PossibleMoves.DOWN_RIGHT,
                PossibleMoves.DOWN_LEFT,
                PossibleMoves.UP_LEFT,
                PossibleMoves.UP_RIGHT,
                PossibleMoves.UP,
                PossibleMoves.DOWN,
                PossibleMoves.LEFT,
                PossibleMoves.RIGHT,


        };
    }

    @Override
    public boolean usesSingleMove() {
        return false;
    }

}
