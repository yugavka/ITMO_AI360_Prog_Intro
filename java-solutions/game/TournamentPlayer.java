package game;

public class TournamentPlayer {
    private final int index;
    private final Player player;
    private int numberOfLosses;
    private int roundOfElimination;
    
    public TournamentPlayer(int index, Player player) {
        this.index = index;
        this.player = player;
        this.numberOfLosses = 0;
        this.roundOfElimination = Integer.MAX_VALUE;
    }
    
    public int getIndex() {
        return index;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public int getLosses() {
        return numberOfLosses;
    }
    
    public void addLoss() {
        this.numberOfLosses++;
    }
    
    public int getRoundOfElimination() {
        return roundOfElimination;
    }
    
    public void setRoundOfElimination(int round) {
        this.roundOfElimination = round;
    }
    
    public boolean isEliminated() {
        return numberOfLosses >= 2;
    }
}
