package com.tzy.baccarat;

import com.tzy.cards.Dealer;
import com.tzy.cards.DeckOfCards;
import com.tzy.cards.Player;

import java.util.List;

public class BaccaratDealer implements Dealer {
    @Override
    public void deal(DeckOfCards deck, List<Player> players) {
        deck.shuffle();
        dealCardsToPlayers(deck, players);
    }
    private void dealCardsToPlayers(DeckOfCards deck, List<Player> players) {
        for (int i=0;i<2;i++) {
            for (Player player : players)
                player.receiveCard(deck.deal());
        }
    }
}
