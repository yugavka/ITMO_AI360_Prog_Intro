package game;

public class SequencePlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        for (int r = 0; r < position.getNumberOfRows(); r++) {
            for (int c = 0; c < position.getNumberOfColumns(); c++) {
                Move move = new Move(
                        r,
                        c,
                        position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        return null;
    }
}
