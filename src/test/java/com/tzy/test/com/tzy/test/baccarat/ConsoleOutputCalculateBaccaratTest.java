package com.tzy.test.com.tzy.test.baccarat;

import com.tzy.baccarat.BaccaratDealer;
import com.tzy.baccarat.BaccaratScore;
import com.tzy.baccarat.BaccaratWinnings;
import com.tzy.cards.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutputCalculateBaccaratTest {

    private static final int NUMBER_OF_PLAYERS = 5;
    private static final int NUMBER_OF_ITERATIONS = 1000000;
    private static Dealer dealer;
    private static BaccaratScore baccaratScore;
    private static List<Player> players;
    private static Player player;

    @BeforeClass
    public static void initilize(){
        dealer = new BaccaratDealer();
        baccaratScore = new BaccaratScore();
        players = new ArrayList<>();

        for (int i = 0; i< NUMBER_OF_PLAYERS; i++)
            players.add(new Player());

        player = players.get(2);
    }

    @Test
    public void testChanceOfNatural(){
        int natural8 = 0;
        int natural9 = 0;
        int naturalCount = 0;
        int naturalAndDouble = 0;

        for (int i = 0; i< NUMBER_OF_ITERATIONS; i++) {
            DeckOfCards deck = new DeckOfCards();
            dealer.deal(deck, players);
            BaccaratWinnings winnings = baccaratScore.calculateScore(player.getHand());
            if (winnings.isNatural()) {
                naturalCount++;
                if (winnings.getScore()==8)
                    natural8++;
                else
                    natural9++;

                if (winnings.getMultiplier()==2)
                    naturalAndDouble++;
            }
            player.emptyHand();
        }
        System.out.println("Natural Percentage : " + naturalCount*100.0/ NUMBER_OF_ITERATIONS +"%");
        System.out.println("Natural 8 Percentage : " + natural8*100.0/ NUMBER_OF_ITERATIONS +"%");
        System.out.println("Natural 9 Percentage : " + natural9*100.0/ NUMBER_OF_ITERATIONS +"%");
        System.out.println("Natural And Double Percentage : " + naturalAndDouble*100.0/ NUMBER_OF_ITERATIONS +"%");
    }

    @Test
    public void testShouldTakeCard(){
        final int cardValue = 4;

        int takeThirdCard = 0;
        int improveCount = 0;
        int worsenCount = 0;

        for (int i = 0; i< NUMBER_OF_ITERATIONS; i++){
            DeckOfCards deck = new DeckOfCards();
            dealer.deal(deck, players);
            BaccaratWinnings winnings = baccaratScore.calculateScore(player.getHand());
            int currentScore = winnings.getScore();
            if (currentScore==cardValue) {
                takeThirdCard++;
                player.receiveCard(deck.deal());
                winnings = baccaratScore.calculateScore(player.getHand());
                if (winnings.getScore() > currentScore)
                    improveCount++;
                else if (winnings.getScore() < currentScore)
                    worsenCount++;
            }
            player.emptyHand();
        }
        System.out.println("Improve Percentage : " + improveCount*100.0/ takeThirdCard +"%");
        System.out.println("Worsen Percentage : " + worsenCount*100.0/ takeThirdCard +"%");
    }

    @Test
    public void testChanceOfThreePicture(){
        int takeThirdCard = 0;
        int gettingThirdPicture = 0;

        for (int i = 0; i< NUMBER_OF_ITERATIONS; i++){
            DeckOfCards deck = new DeckOfCards();
            dealer.deal(deck, players);
            if (hasTwoPictureCards(player.getHand())) {
                takeThirdCard++;
                player.receiveCard(deck.deal());
                BaccaratWinnings winnings = baccaratScore.calculateScore(player.getHand());
                if (winnings.getScore() == 10)
                    gettingThirdPicture++;
            }
            player.emptyHand();
        }
        System.out.println("Getting Third Picture : " + gettingThirdPicture*100.0/ takeThirdCard +"%");
    }

    private boolean hasTwoPictureCards(List<Card> hand) {
        for (Card c: hand){
            if (!(c.getRank()== Rank.JACK||c.getRank()== Rank.QUEEN||c.getRank()== Rank.KING))
                return false;
        }
        return true;
    }
}
