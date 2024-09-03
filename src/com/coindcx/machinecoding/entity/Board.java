package com.coindcx.machinecoding.entity;

import java.util.HashMap;

public class Board {
    private int size; // 81, 100,..
    private HashMap<Integer, Snake> snakes;
    private HashMap<Integer, Ladder> ladders;
    private HashMap<String, Integer> players;
    private HashMap<String, Integer> playerStatus;
    int currentRank;

    public Board(int size) {
        this.size = size;
        this.currentRank = 1;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.players = new HashMap<>();
        this.playerStatus = new HashMap<>();
    }

    public void addSnake(int start, int end) {
        Snake snake = new Snake(start, end);
        snakes.put(start, snake);
    }

    public void addLadder(int start, int end) {
        Ladder ladder = new Ladder(start, end);
        ladders.put(start, ladder);
    }

    public void addPlayer(Player player) {
        if (!players.containsKey(player.getName())) {
            players.put(player.getName(), 1);
            playerStatus.put(player.getName(), Integer.MAX_VALUE);
        }
    }

    public void updatePLayerPosition(String player, int move) {
        int currentPosition = players.get(player);
        int newPosition = currentPosition + move;

        if (newPosition == size) {
            System.out.println("Player " + player + " won with rank " + currentRank);
            playerStatus.put(player, currentRank);
            currentRank++;
            return;
        }
        if (newPosition > size)
            return;
        if (snakes.containsKey(newPosition)) {
            Snake snake = snakes.get(newPosition);
            newPosition = snake.getEnd();
            System.out.println("Player " + player + " bitten by snake at " + snake.getStart());
        }
        if (ladders.containsKey(newPosition)) {
            Ladder ladder = ladders.get(newPosition);
            newPosition = ladder.getEnd();
            System.out.println("Player " + player + " raised by ladder at " + ladder.getStart());
        }
        System.out.println("Player " + player + " new Position is " + newPosition);
        players.put(player, Math.min(newPosition, size));
    }

    public int getCurrentStatus(String player) {
        return playerStatus.get(player);
    }
}
