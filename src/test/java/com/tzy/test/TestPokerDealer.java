package com.tzy.test;

import com.tzy.cards.DeckOfCards;
import com.tzy.cards.Player;
import com.tzy.poker.PokerDealer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TestPokerDealer {

    @Test
    public void testDealFivePlayersTwoCardsEach(){
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        List<Player> players = new ArrayList<>();
        for (int i=0;i<5;i++)
            players.add(new Player());
        PokerDealer dealer = new PokerDealer();
        dealer.deal(deck, players);
        for (Player p: players){
            assertThat(p.getHand().size(),is(2));
        }
    }

    @Test
    public void testFlopHasThreeCards_TurnHasOne_RiverHasOne(){
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        List<Player> players = new ArrayList<>();
        for (int i=0;i<5;i++)
            players.add(new Player());
        PokerDealer dealer = new PokerDealer();
        dealer.deal(deck, players);
        assertThat(dealer.getFlop().size(),is(3));
        assertNotNull(dealer.getTurn());
        assertNotNull(dealer.getRiver());
    }
}
