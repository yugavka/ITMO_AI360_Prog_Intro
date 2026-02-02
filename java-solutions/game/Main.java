package game;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Board board = new MNKBoard(3, 3, 3, false);
        List<Player> players = List.of(
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer(),
            new RandomPlayer()
        );
        Tournament tournament = new Tournament(board, players, true);
        List<Integer> places = tournament.play();
        System.out.println("\nFinal places:");
        List<Integer> indexes = tournament.createIndexList();
        indexes.sort((a, b) -> Integer.compare(places.get(a), places.get(b)));
        for (int index : indexes) {
            System.out.println("Player " + (index + 1) + ": place " + places.get(index));
        }
    }
}