package com.tzy.test;

import com.tzy.baccarat.BaccaratScore;
import com.tzy.baccarat.BaccaratWinnings;
import com.tzy.cards.Card;
import com.tzy.cards.Rank;
import com.tzy.cards.Suit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBaccaratScore {

    @Test
    public void testAddScoreMultiplierNatural(){
        List<Card> list = Arrays.asList(new Card(Suit.CLUBS, Rank.SIX), new Card(Suit.CLUBS, Rank.TWO));
        BaccaratScore baccaratScore = new BaccaratScore();
        BaccaratWinnings winnings = baccaratScore.calculateScore(list);
        assertThat(winnings.getMultiplier(),is(2));
        assertThat(winnings.getScore(),is(8));
        assertThat(winnings.isNatural(),is(true));
    }

    @Test
    public void test3PictureCards(){
        List<Card> list = Arrays.asList(new Card(Suit.CLUBS, Rank.JACK), new Card(Suit.CLUBS, Rank.QUEEN), new Card(Suit.DIAMONDS, Rank.QUEEN));
        BaccaratScore baccaratScore = new BaccaratScore();
        BaccaratWinnings winnings = baccaratScore.calculateScore(list);
        assertThat(winnings.getMultiplier(),is(3));
        assertThat(winnings.getScore(),is(10));
        assertThat(winnings.isNatural(),is(false));
    }


}
