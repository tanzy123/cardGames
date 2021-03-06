package com.tzy.test;

import com.tzy.cards.Card;
import com.tzy.cards.CardFactory;
import com.tzy.poker.HandCategorizer;
import com.tzy.poker.StraightFlushCategorizer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tzy.cards.Rank.*;
import static com.tzy.cards.Suit.*;
import static com.tzy.cards.Suit.CLUBS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestStraightFlushCategorizer {
    private static final Card[] NOT_STRAIGHT_FLUSH_CARDS = new Card[]
            {new Card(DIAMONDS,JACK), new Card(DIAMONDS,ACE), new Card(DIAMONDS,TWO), new Card(SPADES,SEVEN), new Card(SPADES,FIVE),
                    new Card(CLUBS,SIX), new Card(CLUBS,QUEEN)};
    private static final Card[] ROYAL_FLUSH_CARDS = new Card[]
            {new Card(DIAMONDS,JACK), new Card(DIAMONDS,ACE), new Card(DIAMONDS,QUEEN), new Card(DIAMONDS,TEN), new Card(SPADES,FIVE),
                    new Card(CLUBS,SIX), new Card(DIAMONDS,KING)};
    private static final Card[] SAME_SUIT_CARDS = new Card[]
            {new Card(DIAMONDS,JACK), new Card(DIAMONDS,ACE), new Card(DIAMONDS,TWO), new Card(DIAMONDS,NINE), new Card(DIAMONDS,FIVE),
                    new Card(DIAMONDS,SIX), new Card(DIAMONDS,KING)};
    private static final Card[] RUNNING_ORDER_CARDS = new Card[]
            {new Card(CLUBS,JACK), new Card(HEARTS,ACE), new Card(DIAMONDS,TEN), new Card(DIAMONDS,JACK), new Card(SPADES,QUEEN),
                    new Card(HEARTS,SIX), new Card(CLUBS,KING)};
    private static final Card[] STRAIGHT_FLUSH_CARDS = new Card[]
            {new Card(CLUBS,JACK), new Card(CLUBS,TEN), new Card(CLUBS,NINE), new Card(DIAMONDS,JACK), new Card(SPADES,QUEEN),
                    new Card(CLUBS,QUEEN), new Card(CLUBS,EIGHT)};
    private static final Card[] STRAIGHT_FLUSH_ACE2345 = new Card[]
            {new Card(CLUBS,ACE), new Card(CLUBS,THREE), new Card(CLUBS,FIVE), new Card(DIAMONDS,JACK), new Card(SPADES,QUEEN),
                    new Card(CLUBS,TWO), new Card(CLUBS,FOUR)};

    private static final CardFactory CARD_FACTORY = CardFactory.getInstance();
    private HandCategorizer categorizer;
    private List<Card> cards;

    @Before
    public void initialize(){
        categorizer = new StraightFlushCategorizer(CARD_FACTORY);
        cards = new ArrayList<>();
    }

    @Test
    public void CardsThatDoNotFormStraightFlush(){
        cards.addAll(Arrays.asList(NOT_STRAIGHT_FLUSH_CARDS));
        boolean result = categorizer.categorize(cards);
        assertThat(result, is(false));
    }

    @Test
    public void CardsThatFormRoyalFlush(){
        cards.addAll(Arrays.asList(ROYAL_FLUSH_CARDS));
        boolean result = categorizer.categorize(cards);
        assertThat(result, is(true));
    }

    @Test
    public void CardsThatHaveFiveSameSuit(){
        cards.addAll(Arrays.asList(SAME_SUIT_CARDS));
        boolean result = categorizer.categorize(cards);
        assertThat(result, is(false));
    }

    @Test
    public void CardsThatHaveRunningOrderButDifferentSuit(){
        cards.addAll(Arrays.asList(RUNNING_ORDER_CARDS));
        boolean result = categorizer.categorize(cards);
        assertThat(result, is(false));
    }

    @Test
    public void CardsThatHaveStraightFlushButNotRoyal(){
        cards.addAll(Arrays.asList(STRAIGHT_FLUSH_CARDS));
        boolean result = categorizer.categorize(cards);
        assertThat(result, is(true));
    }

    @Test
    public void CardsThatHaveStraightFlushWithACE2345(){
        cards.addAll(Arrays.asList(STRAIGHT_FLUSH_ACE2345));
        boolean result = categorizer.categorize(cards);
        assertThat(result, is(true));
    }
}
