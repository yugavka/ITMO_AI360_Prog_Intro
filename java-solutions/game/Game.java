package game;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final boolean log;

    public Game(Board board, Player player1, Player player2, boolean log) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.log = log;
    }

    public int play() {
        while (true) {
            int result1 = getResult(1, player1);
            if (result1 != -1) {
                return result1;
            }
            int result2 = getResult(2, player2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int getResult(int number, Player player) {
        Move move;
        try {
            move = player.makeMove(board.getPosition());
        } catch (RuntimeException e) {
            if (log) {
                System.out.println("Player #" + number + " error: " + e.getMessage());
            }
            return 3 - number;
        } catch (Error e) {
            if (log) {
                System.out.println("Player #" + number + " error: " + e.getMessage());
            }
            return 3 - number;
        }

        if (move == null) {
            if (log) {
                System.out.println("Player #" + number + " null move");
            }
            return 3 - number;
        }
        
        if (log) {
            System.out.println("Player #" + number + " move: " + move);
        }
        
        Result result = board.makeMove(move);
        
        if (log) {
            System.out.println(board);
        }
        if (result == Result.WIN) {
            return number;
        } else if (result == Result.LOSE) {
            return 3 - number;
        } else if (result == Result.DRAW) {
            return 0;
        }
        return -1;
    }
}
