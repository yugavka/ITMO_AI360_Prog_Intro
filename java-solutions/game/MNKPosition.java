package game;

class MNKPosition implements Position {
    private final MNKBoard board;
    
    MNKPosition(MNKBoard board) {
        this.board = board;
    }
    
    @Override
    public Cell getTurn() {
        return board.getTurn();
    }
    
    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }
    
    @Override
    public int getM() {
        return board.getM();
    }
    
    @Override
    public int getN() {
        return board.getN();
    }
    
    @Override
    public int getK() {
        return board.getK();
    }

    @Override
    public int getNumberOfRows() {
        return board.getNumberOfRows();
    }

    @Override
    public int getNumberOfColumns() {
        return board.getNumberOfColumns();
    }
    
    @Override
    public String toString() {
        return board.toString();
    }
}
