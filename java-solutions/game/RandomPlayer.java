package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private Random random = new Random();
    
    @Override
    public Move makeMove(Position position) {
        while (true) {
            Move move = new Move(
                    random.nextInt(position.getNumberOfRows()),
                    random.nextInt(position.getNumberOfColumns()),
                    position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
        }

    }
}
