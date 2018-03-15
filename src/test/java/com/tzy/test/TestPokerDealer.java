package com.tzy.test;

import com.tzy.cards.DeckOfCards;
import com.tzy.cards.Player;
import com.tzy.poker.PokerDealer;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestPokerDealer {

    private DeckOfCards deck;
    private List<Player> players;
    private PokerDealer dealer;

    private void initializePlayersAndCards() {
        deck = new DeckOfCards();
        players = new ArrayList<>();
        dealer = new PokerDealer();
        deck.shuffle();
        for (int i=0;i<5;i++)
            players.add(new Player());

    }

    @Test
    public void testDealFivePlayersTwoCardsEach(){
        initializePlayersAndCards();
        dealer.deal(deck, players);
        for (Player p: players){
            assertThat(p.getHand().size(),is(2));
        }
    }

    @Test
    public void testFlopHasThreeCards_TurnHasOne_RiverHasOne(){
        initializePlayersAndCards();
        dealer.deal(deck, players);
        assertThat(dealer.getFlop().size(),is(3));
        assertNotNull(dealer.getTurn());
        assertNotNull(dealer.getRiver());
    }

    @Test
    public void testBurns3CardsWhenDealingCommunityCards(){
        initializePlayersAndCards();
        DeckOfCards spyDeck = Mockito.spy(new DeckOfCards());
        dealer.deal(spyDeck, players);
        verify(spyDeck, times(3)).burn();
    }

}
