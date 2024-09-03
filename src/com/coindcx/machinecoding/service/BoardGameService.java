package com.coindcx.machinecoding.service;

import com.coindcx.machinecoding.entity.Board;
import com.coindcx.machinecoding.entity.Player;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.coindcx.machinecoding.service.DiceThrow.getDiceThrow;

public class BoardGameService {
    Board board;
    Queue<Player> players;

    public BoardGameService(Board board) {
        this.board = board;
        this.players = new LinkedList<>();
    }

    public void start(List<Player> playerList) {
        //Add snakes
        board.addSnake(34, 11);
        board.addSnake(65, 26);
        board.addSnake(89, 41);
        board.addSnake(98, 27);
        board.addSnake(45, 36);

        //Add ladders
        board.addLadder(4, 23);
        board.addLadder(32, 59);
        board.addLadder(76, 87);
        board.addLadder(31, 68);
        board.addLadder(20, 42);
        board.addLadder(9, 23);
        for (Player player : playerList) {
            board.addPlayer(player);
            players.add(player);
        }

        while (!players.isEmpty()) {
            Player player = players.poll();
            int diceThrow = getDiceThrow();
            System.out.println("Player " + player + " rolls a dice : " + diceThrow);
            board.updatePLayerPosition(player.getName(), diceThrow);
            if (board.getCurrentStatus(player.getName()) == Integer.MAX_VALUE)
                players.add(player);
        }
    }

    public static void main(String[] args) {
        Board board = new Board(100);

        BoardGameService game = new BoardGameService(board);
        Player aaquib = new Player("Aaquib");
        Player prasant = new Player("Prasant");
        Player aman = new Player("Aman");
        game.start(Arrays.asList(aaquib, prasant, aman));
    }
}
