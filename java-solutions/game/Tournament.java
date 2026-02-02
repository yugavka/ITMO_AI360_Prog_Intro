package game;

import java.util.*;

public class Tournament {
    private final Board board;
    private final ArrayList<Player> players;
    private final boolean log;
    private final Random random;
    
    public Tournament(Board board, List<Player> players, boolean log) {
        this.board = board;
        this.players = new ArrayList<>(players);
        this.log = log;
        this.random = new Random();
    }
    
    public List<Integer> play() {
        List<TournamentPlayer> tournamentPlayers = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            tournamentPlayers.add(new TournamentPlayer(i, players.get(i)));
        }
        List<TournamentPlayer> upperBracket = new ArrayList<>(tournamentPlayers);
        List<TournamentPlayer> lowerBracket = new ArrayList<>(tournamentPlayers);
        int round = 1;
        System.out.println("\nRound " + round + " ");
        List<TournamentPlayer> winners = playRound(upperBracket, round, true);
        lowerBracket.removeAll(winners);
        upperBracket = winners;
        round++;
        while (!(upperBracket.size() == 1 && lowerBracket.size() >= 2)
                && (upperBracket.size() > 1 || lowerBracket.size() > 1)) {
            List<TournamentPlayer> upperLosers = new ArrayList<>();
            if (upperBracket.size() > 1) {
                System.out.println("\nRound " + round + " (Upper bracket)");
                List<TournamentPlayer> upperWinners = playRound(upperBracket, round, true);
                upperLosers = new ArrayList<>(upperBracket);
                upperLosers.removeAll(upperWinners);
                upperBracket = upperWinners;
            }
            if (lowerBracket.size() > 1) {
                System.out.println("\nRound " + round + " (Lower bracket)");
                List<TournamentPlayer> lowerWinners = playRound(lowerBracket, round, false);
                lowerBracket = lowerWinners;
            }
            for (TournamentPlayer loser : upperLosers) {
                if (!loser.isEliminated()) {
                    lowerBracket.add(loser);
                } else {
                    loser.setRoundOfElimination(round);
                }
            }
            round++;
        }

        if (upperBracket.size() == 1 && lowerBracket.size() >= 2) {
            TournamentPlayer upperWinner = upperBracket.get(0);
            System.out.println("\nLower bracket elimination");
            while (lowerBracket.size() > 1) {
                System.out.println("\nRound " + round + " ");
                List<TournamentPlayer> lowerWinners = playRound(lowerBracket, round, false);
                List<TournamentPlayer> lowerLosers = new ArrayList<>(lowerBracket);
                lowerLosers.removeAll(lowerWinners);
                if (lowerBracket.size() == 2 && lowerLosers.size() == 1) {
                    TournamentPlayer thirdPlace = lowerLosers.get(0);
                    thirdPlace.setRoundOfElimination(round);
                } else {
                    for (TournamentPlayer loser : lowerLosers) {
                        loser.setRoundOfElimination(round);
                    }
                }
                lowerBracket = lowerWinners;
                round++;
            }
            TournamentPlayer lowerWinner = lowerBracket.get(0);
            System.out.println("\nFinal");
            FinalResult(upperWinner, lowerWinner, round);
        }
        return finalPlaces(tournamentPlayers);
    }
    
    private List<TournamentPlayer> playRound(List<TournamentPlayer> bracket, int round, boolean isUpperBracket) {
        List<TournamentPlayer> players = new ArrayList<>(bracket);
        Collections.shuffle(players, random);
        List<TournamentPlayer> nextRound = new ArrayList<>();
        int numberOfPairs = (int) Math.pow(2, Math.floor(Math.log(players.size()) / Math.log(2)));
        int autoNextRound = players.size() - numberOfPairs;
        for (int i = 0; i < autoNextRound; i++) {
            nextRound.add(players.get(i));
        }
        List<TournamentPlayer> roundPlayers = new ArrayList<>();
        for (int i = autoNextRound; i < players.size(); i++) {
            roundPlayers.add(players.get(i));
        }
        for (int i = 0; i < roundPlayers.size(); i += 2) {
            TournamentPlayer player1 = roundPlayers.get(i);
            TournamentPlayer player2 = roundPlayers.get(i + 1);            
            TournamentPlayer winner = playMatch(player1, player2, round);
            TournamentPlayer loser = (winner == player1) ? player2 : player1;
            nextRound.add(winner);
            if (isUpperBracket) {
                loser.addLoss();
            } else {
                loser.addLoss();
                loser.setRoundOfElimination(round);
                System.out.println("Player " + (loser.getIndex() + 1) + " is eliminated");
            }
        }
        return nextRound;
    }
    
    private void FinalResult(TournamentPlayer upperWinner, TournamentPlayer lowerWinner, int round) {
        TournamentPlayer finalWinner = playMatch(upperWinner, lowerWinner, round);
        TournamentPlayer finalLoser = (finalWinner == upperWinner) ? lowerWinner : upperWinner;
        finalLoser.addLoss();
        finalLoser.setRoundOfElimination(round);
        System.out.println("Player " + (finalWinner.getIndex() + 1) + " is the champion");
    }
    
    private TournamentPlayer playMatch(TournamentPlayer player1, TournamentPlayer player2, int round) {
        boolean swap = random.nextBoolean();
        TournamentPlayer firstPlayer = player1;
        TournamentPlayer secondPlayer = player2;
        System.out.println("\nPlayer " + (player1.getIndex() + 1) + " vs Player " + (player2.getIndex() + 1));
        if (swap) {
            System.out.println("Symbols: Player " + (player1.getIndex() + 1) + " plays O, Player "
                    + (player2.getIndex() + 1) + " plays X");
        } else {
            System.out.println("Symbols: Player " + (player1.getIndex() + 1) + " plays X, Player "
                    + (player2.getIndex() + 1) + " plays O");
        }
        while (true) {
            Board board = createBoard();
            Game game = new Game(board, firstPlayer.getPlayer(), secondPlayer.getPlayer(), log);
            int result = game.play();
            if (result == 1) {
                System.out.println("Player " + (firstPlayer.getIndex() + 1) + " won");
                return firstPlayer;
            } else if (result == 2) {
                System.out.println("Player " + (secondPlayer.getIndex() + 1) + " won");
                return secondPlayer;
            }
            System.out.println("Draw");
        }
    }
    
    private Board createBoard() {
        MNKBoard mnkBoard = (MNKBoard) board;
        int m = mnkBoard.getM();
        int n = mnkBoard.getN();
        int k = mnkBoard.getK();
        boolean isRhombus = mnkBoard.isRhombus();
        return new MNKBoard(m, n, k, isRhombus);
    }
    
    private List<Integer> finalPlaces(List<TournamentPlayer> tournamentPlayers) {
        List<TournamentPlayer> sortedPlayers = new ArrayList<>(tournamentPlayers);
        sortedPlayers.sort((a, b) -> {
            int lossA = a.getLosses();
            int lossB = b.getLosses();
            if (lossA != lossB) {
                return Integer.compare(lossA, lossB);
            }
            int roundA = a.getRoundOfElimination();
            int roundB = b.getRoundOfElimination();
            return Integer.compare(roundA, roundB);
        });
        List<Integer> places = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            places.add(0);
        }
        int currentPlace = 1;
        int currentLosses = -1;
        int currentEliminationRound = -1;
        int playersAtCurrentPlace = 0;
        for (TournamentPlayer tournamentPlayer : sortedPlayers) {
            int playerLosses = tournamentPlayer.getLosses();
            int eliminationRound = tournamentPlayer.getRoundOfElimination();
            if (playerLosses != currentLosses || eliminationRound != currentEliminationRound) {
                currentPlace += playersAtCurrentPlace;
                currentLosses = playerLosses;
                currentEliminationRound = eliminationRound;
                playersAtCurrentPlace = 0;
            }
            places.set(tournamentPlayer.getIndex(), currentPlace);
            playersAtCurrentPlace++;
        }
        return places;
    }

    public List<Integer> createIndexList() {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            indexes.add(i);
        }
        return indexes;
    }
}
