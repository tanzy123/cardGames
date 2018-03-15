package com.tzy.test;

import com.tzy.cards.*;
import com.tzy.poker.HandCategorizer;
import com.tzy.poker.PokerDealer;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestDefaultMethodsInHandCategorizer {

    private HandCategorizer defaultHandCategorizer;
    private PokerDealer dealer;
    private DeckOfCards deck;
    private List<Player> players;
    private Map<Suit, List<Card>> mapBySuits;
    private Map<Rank, List<Card>> mapByRank;
    private Player p;

    @Before
    public void setUp(){

        deck = new DeckOfCards();
        deck.shuffle();

        players = new ArrayList<>();
        for (int i=0;i<5;i++)
            players.add(new Player());
        dealer = new PokerDealer();
        dealer.deal(deck, players);


        defaultHandCategorizer = new HandCategorizer() {
            public boolean categorize(List<Card> cardsToCategorize) {
                throw new UnsupportedOperationException();
            }
        };
        p = players.get(0);
        List<Card> cardsToBeMapped = new ArrayList<>(dealer.getCommunityCards());
        cardsToBeMapped.addAll(p.getHand());
        mapBySuits = defaultHandCategorizer.mapBySuits(cardsToBeMapped);
        mapByRank = defaultHandCategorizer.mapByRank(cardsToBeMapped);
    }

    @Test
    public void testCorrectNumberCardsMapped(){
        int countForSuitsMapping = 0;
        int countForRankMapping = 0;
        for (Suit s: Suit.values())
            countForSuitsMapping += mapBySuits.getOrDefault(s, Collections.emptyList()).size();
        for (Rank r: Rank.values())
            countForRankMapping += mapByRank.getOrDefault(r, Collections.emptyList()).size();
        assertThat(dealer.getCommunityCards().size()+p.getHand().size(), is(countForSuitsMapping));
        assertThat(dealer.getCommunityCards().size()+p.getHand().size(), is(countForRankMapping));
    }

    @Test
    public void testCorrectCardsFromMappingSuits(){
        for (Suit s: Suit.values()){
            List<Card> cards = mapBySuits.getOrDefault(s, Collections.emptyList());
            for (Card c: cards)
                assertThat(c.getSuit(),is(s));
        }
    }

    @Test
    public void testCorrectCardsFromMappingRank(){
        for (Rank r: Rank.values()){
            List<Card> cards = mapByRank.getOrDefault(r, Collections.emptyList());
            for (Card c: cards)
                assertThat(c.getRank(),is(r));
        }
    }

    @Test
    public void test3OfAKindTrue(){
        Map<Rank, List<Card>> map = new HashMap<>();
        List<Card> cards = Arrays.asList(new Card(Suit.CLUBS, Rank.ACE),new Card(Suit.HEARTS, Rank.ACE), new Card(Suit.DIAMONDS, Rank.ACE));
        map.put(Rank.ACE, cards);
        assertThat(defaultHandCategorizer.hasNumberOfKind(3, map),is(true));
    }

    @Test
    public void test3OfAKindFail(){
        Map<Rank, List<Card>> map = new HashMap<>();
        List<Card> cards = Arrays.asList(new Card(Suit.DIAMONDS, Rank.ACE));
        map.put(Rank.ACE, cards);
        assertThat(defaultHandCategorizer.hasNumberOfKind(3, map),is(false));
    }

    @Test
    public void testFlushSuccess(){
        Map<Suit, List<Card>> map = new HashMap<>();
        List<Card> cards = Arrays.asList(new Card(Suit.DIAMONDS, Rank.ACE), new Card(Suit.DIAMONDS, Rank.TWO), new Card(Suit.DIAMONDS, Rank.EIGHT),
                new Card(Suit.DIAMONDS, Rank.NINE), new Card(Suit.DIAMONDS, Rank.SEVEN));
        map.put(Suit.DIAMONDS, cards);
        assertThat(defaultHandCategorizer.hasFlush(map),is(true));
    }

    @Test
    public void testFlushFail(){
        Map<Suit, List<Card>> map = new HashMap<>();
        List<Card> cards = Arrays.asList(new Card(Suit.DIAMONDS, Rank.TWO), new Card(Suit.DIAMONDS, Rank.EIGHT),
                new Card(Suit.DIAMONDS, Rank.NINE), new Card(Suit.DIAMONDS, Rank.SEVEN));
        map.put(Suit.DIAMONDS, cards);
        assertThat(defaultHandCategorizer.hasFlush(map),is(false));
    }

    @Test
    public void testStraightFail4Cards(){
        Map<Rank, List<Card>> map = new HashMap<>();
        List<Card> cards = Arrays.asList(new Card(Suit.DIAMONDS, Rank.TWO), new Card(Suit.DIAMONDS, Rank.EIGHT),
                new Card(Suit.DIAMONDS, Rank.NINE), new Card(Suit.DIAMONDS, Rank.SEVEN));
        map.put(Rank.ACE, cards);
        assertThat(defaultHandCategorizer.hasStraight(map),is(false));
    }

    @Test
    public void testStraightSuccessWithAce1234(){
        List<Card> cards = Arrays.asList(new Card(Suit.DIAMONDS, Rank.TWO), new Card(Suit.DIAMONDS, Rank.ACE), new Card(Suit.SPADES, Rank.FIVE),
                new Card(Suit.CLUBS, Rank.THREE), new Card(Suit.SPADES, Rank.FOUR), new Card(Suit.HEARTS, Rank.TEN));
        Map<Rank, List<Card>> map = defaultHandCategorizer.mapByRank(cards);
        assertThat(defaultHandCategorizer.hasStraight(map), is(true));
    }

    @Test
    public void testStraightSuccess(){
        List<Card> cards = Arrays.asList(new Card(Suit.DIAMONDS, Rank.ACE), new Card(Suit.DIAMONDS, Rank.JACK), new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.CLUBS, Rank.SIX), new Card(Suit.SPADES, Rank.KING), new Card(Suit.HEARTS, Rank.QUEEN));
        Map<Rank, List<Card>> map = defaultHandCategorizer.mapByRank(cards);
        assertThat(defaultHandCategorizer.hasStraight(map), is(true));
    }

    @Test
    public void testStraightFailNoStraight(){
        List<Card> cards = Arrays.asList(new Card(Suit.DIAMONDS, Rank.QUEEN), new Card(Suit.DIAMONDS, Rank.JACK), new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.CLUBS, Rank.SIX), new Card(Suit.SPADES, Rank.KING), new Card(Suit.HEARTS, Rank.QUEEN));
        Map<Rank, List<Card>> map = defaultHandCategorizer.mapByRank(cards);
        assertThat(defaultHandCategorizer.hasStraight(map), is(false));
    }

}
