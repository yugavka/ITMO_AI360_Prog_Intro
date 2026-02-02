package game;

public interface Board {
    Position getPosition();

    Result makeMove(Move move);
}
