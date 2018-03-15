package com.tzy.poker;

import com.tzy.cards.Card;
import com.tzy.cards.CardFactory;
import com.tzy.cards.Rank;
import com.tzy.cards.Suit;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StraightFlushCategorizer implements HandCategorizer {

    private final CardFactory cardFactory;

    public StraightFlushCategorizer(CardFactory cardFactory){
        this.cardFactory = cardFactory;
    };

    @Override
    public boolean categorize(List<Card> cardsToCategorize) {
        Map<Suit,List<Card>> cardsMapBySuits = mapBySuits(cardsToCategorize);
        if (checkIfStraightFlush(cardsMapBySuits))
            return true;
        else
            return false;
    }

    private boolean checkIfStraightFlush(Map<Suit, List<Card>> cardsMapBySuits) {
        for (Suit s : Suit.values()) {
            List<Card> cardsOfSameSuit = cardsMapBySuits.getOrDefault(s, Collections.emptyList());
            if (cardsOfSameSuit.size() >= 5 && checkRunningOrder(cardsOfSameSuit, s))
                return true;
        }
        return false;
    }

    private boolean checkRunningOrder(List<Card> cardsOfSameSuit, final Suit s) {
        int count = 0;
        if (cardsOfSameSuit.contains(cardFactory.getCard(s, Rank.ACE)))
            count++;
        for (Rank r: Rank.values()) {
            if (cardsOfSameSuit.contains(cardFactory.getCard(s, r))) {
                count++;
                if (count == 5)
                    return true;
            }
            else
                count = 0;
        }
        return false;
    }
}
