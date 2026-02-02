package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private Scanner in;
    private PrintStream out;

    public HumanPlayer(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public HumanPlayer() {
        this(new Scanner(System.in), System.out);
    }

    @Override
    public Move makeMove(Position position) {
        out.println("Your turn " + position.getTurn());
        out.println("Current position:\n" + position.toString());
        while (true) {
            try {
                out.print("Enter row and column: ");
                String line = in.nextLine().strip();
                String[] parts = line.split("\\s+");
                
                if (parts.length < 2) {
                    out.println("Enter two numbers");
                    continue;
                }
                
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                Move move = new Move(row, col, position.getTurn());
                
                if (!position.isValid(move)) {
                    if (row < 0 || row >= position.getM() || col < 0 || col >= position.getN()) {
                        out.println("Numbers are not valid");
                    } else {
                        out.println("Cell is already occupied");
                    }
                    continue;
                }
                return move;
            } catch (NumberFormatException e) {
                out.println("Enter valid numbers");
            } catch (RuntimeException e) {
                out.println("Invalid input");
            } 
        }
    }
}
