package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board {
    private final int[] dRow = {0, 1, 1, 1};
    private final int[] dCol = {1, 0, 1, -1};
    private final Cell[][] field;
    private Cell turn;
    private final int m, n, k;
    private int emptyCells;
    private final boolean isRhombus;
    private final int numberOfRows;
    private final int numberOfColumns;

    private final static Map<Cell, String> CELL_STRING_MAP = Map.of(
            Cell.X, "X",
            Cell.O, "O",
            Cell.E, " "
    ) ;

    public MNKBoard(int m, int n, int k, boolean isRhombus) {
        if (isRhombus && m != n) {
            throw new IllegalArgumentException("The lehgtns of the sides of the rhombus must be equal");
        }
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("The lengths of the sides of the board must be positive");
        }
        if (k <= 0) {
            throw new IllegalArgumentException("Parameter k must be positive");
        }
        if (k > m || k > n) {
            throw new IllegalArgumentException("Parameter k must be equal to or less than the sides of the board");
        }
        
        this.m = m;
        this.n = n;
        this.k = k;
        this.isRhombus = isRhombus;
        
        if (isRhombus) {
            this.numberOfRows = 2 * m - 1;
            this.numberOfColumns = 2 * m - 1;
        } else {
            this.numberOfRows = m;
            this.numberOfColumns = n;
        }
        field = new Cell[numberOfRows][numberOfColumns];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        int count = 0;
        for (int r = 0; r < numberOfRows; r++) {
            for (int c = 0; c < numberOfColumns; c++) {
                if (isCellPlayable(r, c)) {
                    count++;
                }
            }
        }
        emptyCells = count;
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return new MNKPosition(this);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int row = move.getRow();
        int col = move.getCol();
        field[row][col] = move.getCell();
        emptyCells--;

        if (isWin(row, col, turn)) {
            return Result.WIN;
        }

        if (emptyCells == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private boolean isWin(int row, int col, Cell cell) {
        // NOTE: код всё ещё дублируется
        for (int i = 0; i < dRow.length; i++) {
            int countOfOccupiedCells = 1;
            for (int j = 1; j < k; j++) {
                int curRow = row + j * dRow[i];
                int curCol = col + j * dCol[i];
                if (curRow >= 0 && curRow < numberOfRows && curCol >= 0 && curCol < numberOfColumns &&
                        isCellPlayable(curRow, curCol) && field[curRow][curCol] == cell) {
                    countOfOccupiedCells++;
                } else {
                    break;
                }
            }
            for (int j = 1; j < k; j++) {
                int curRow = row - j * dRow[i];
                int curCol = col - j * dCol[i];
                if (curRow >= 0 && curRow < numberOfRows && curCol >= 0 && curCol < numberOfColumns &&
                        isCellPlayable(curRow, curCol) && field[curRow][curCol] == cell) {
                    countOfOccupiedCells++;
                } else {
                    break;
                }
            }
            if (countOfOccupiedCells >= k) {
                return true;
            }
        }
        return false;
    }

    private boolean isCellPlayable(int row, int col) {
        if (row < 0 || row >= numberOfRows || col < 0 || col >= numberOfColumns) {
            return false;
        }
        if (!isRhombus) {
            return true;
        }
        int centerRow = (numberOfRows - 1) / 2;
        int centerCol = (numberOfColumns - 1) / 2;
        int rowDistance = Math.abs(row - centerRow);
        int columnDistamce = Math.abs(col - centerCol);
        return rowDistance + columnDistamce < m;
    }

    Cell getTurn() {
        return turn;
    }

    boolean isValid(Move move) {
        if (move == null) {
            return false;
        }
        int row = move.getRow();
        int col = move.getCol();
        return 0 <= row && row < numberOfRows
                && 0 <= col && col < numberOfColumns
                && isCellPlayable(row, col)
                && field[row][col] == Cell.E
                && move.getCell() == turn;
    }

    int getM() {
        return m;
    }

    int getN() {
        return n;
    }

    int getK() {
        return k;
    }

    boolean isRhombus() {
        return isRhombus;
    }

    int getNumberOfRows() {
        return numberOfRows;
    }

    int getNumberOfColumns() {
        return numberOfColumns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int rowWidth = String.valueOf(numberOfRows).length();
        int colWidth = String.valueOf(numberOfColumns).length();
        for (int i = 0; i < rowWidth + 1; i++) {
            sb.append(" ");
        }
        for (int c = 1; c <= numberOfColumns; c++) {
            String colNum = String.valueOf(c);
            for (int i = colNum.length(); i < colWidth + 1; i++) {
                sb.append(" ");
            }
            sb.append(colNum);
            sb.append("");
        }
        sb.append("\n");
        for (int i = 0; i < rowWidth + 1; i++) {
            sb.append(" ");
        }
        sb.append("+");
        for (int c = 0; c < numberOfColumns; c++) {
            for (int i = 0; i < colWidth; i++) {
                sb.append("-");
            }
            sb.append("-");
        }
        sb.append("\n");
        for (int r = 0; r < numberOfRows; r++) {
            String rowNum = String.valueOf(r + 1);
            sb.append(rowNum);
            for (int i = rowNum.length(); i < rowWidth; i++) {
                sb.append(" ");
            }
            sb.append(" |");
            for (int c = 0; c < numberOfColumns; c++) {
                String cell = isCellPlayable(r, c) 
                    ? CELL_STRING_MAP.get(field[r][c]) 
                    : ".";
                for (int i = cell.length(); i < colWidth; i++) {
                    sb.append(" ");
                }
                sb.append(cell);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
