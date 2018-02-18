package com.tzy.baccarat;

import com.tzy.cards.Card;
import com.tzy.cards.Rank;
import com.tzy.cards.Suit;

import java.util.InputMismatchException;
import java.util.List;

public class BaccaratScore {

    public BaccaratWinnings calculateScore(List<Card> list){
        if (list.size()==2)
            return calculateScoreFor2Cards(list);
        else if (list.size()==3)
            return calculateScoreFor3Cards(list);
        else
            throw new InputMismatchException("Baccarat only has 2 or 3 cards per hand!");
    }

    private BaccaratWinnings calculateScoreFor3Cards(List<Card> list) {
        BaccaratWinnings winnings = new BaccaratWinnings();
        if (sameSuit(list) || sameRank(list))
            winnings.setMultiplier(3);
        else
            winnings.setMultiplier(1);

        if (threePictureCards(list)) {
            winnings.setMultiplier(3);
            winnings.setScore(10);
            return winnings;
        }

        int score = calculateScoreByRank(list);
        winnings.setScore(score);

        return winnings;
    }

    private BaccaratWinnings calculateScoreFor2Cards(List<Card> list) {
        BaccaratWinnings winnings = new BaccaratWinnings();
        if (sameSuit(list) || sameRank(list))
            winnings.setMultiplier(2);
        else
            winnings.setMultiplier(1);

        int score = calculateScoreByRank(list);
        winnings.setScore(score);

        if (score == 8 || score == 9)
            winnings.setNatural(true);

        return winnings;
    }

    private int calculateScoreByRank(List<Card> list) {
        int score = 0;
        for (Card c: list)
            score += getScoreFromRank(c);
        return score%10;
    }

    private int getScoreFromRank(Card c) {
        Rank rank = c.getRank();
        if (rank==Rank.JACK || rank==Rank.QUEEN || rank==Rank.KING)
            return 10;
        else if (rank==Rank.ACE)
            return 1;
        else
            return rank.getValue();
    }

    private boolean sameSuit(List<Card> list) {
        Suit s = list.get(0).getSuit();
        for (int i=1;i<list.size();i++){
            if (s != list.get(i).getSuit())
                return false;
        }
        return true;
    }

    private boolean sameRank(List<Card> list) {
        Rank r = list.get(0).getRank();
        for (int i=1;i<list.size();i++){
            if (r != list.get(i).getRank())
                return false;
        }
        return true;
    }

    private boolean threePictureCards(List<Card> list) {
        if (list.size()!=3)
            throw new InputMismatchException("Only 3 cards should use this method!");
        for (Card c: list) {
            if (!(c.getRank()==Rank.JACK || c.getRank()==Rank.QUEEN || c.getRank()==Rank.KING))
                    return false;
        }
        return true;
    }

}
