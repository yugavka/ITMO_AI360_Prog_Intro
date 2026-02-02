package game;

public class CheaterPlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        Board board = (Board) position;
        board.makeMove(new Move(0, 0, position.getTurn()));
        // board.makeMove(new Move(-1, -1, position.getTurn()));
        return new Move(1, 1, position.getTurn());
    }
}
