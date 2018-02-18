package com.tzy;

import com.tzy.cards.DeckOfCards;
import com.tzy.cards.Player;
import com.tzy.poker.PokerDealer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();


        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        PokerDealer dealer = new PokerDealer();
        dealer.deal(deck, players);

        for (int i=0;i<players.size();i++) {
            System.out.println("Player " + (i+1)+" : " + players.get(i).getHand());
        }

        System.out.println(dealer.getFlop());
        System.out.println(dealer.getTurn());
        System.out.println(dealer.getRiver());
    }
}
