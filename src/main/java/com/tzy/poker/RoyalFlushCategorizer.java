package com.tzy.poker;

import com.tzy.cards.Card;
import com.tzy.cards.CardFactory;
import com.tzy.cards.Rank;
import com.tzy.cards.Suit;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RoyalFlushCategorizer implements HandCategorizer {

    private final CardFactory cardFactory;

    public RoyalFlushCategorizer(CardFactory cardFactory){
        this.cardFactory = cardFactory;
    };
    
    @Override
    public boolean categorize(List<Card> cardsToCategorize) {
        Map<Suit,List<Card>> cardsMapBySuits = mapBySuits(cardsToCategorize);
        if (checkIfRoyalFlush(cardsMapBySuits))
            return true;
        else
            return false;
    }

    private boolean checkIfRoyalFlush(Map<Suit, List<Card>> cardsMapBySuits) {
        for (Suit s : Suit.values()) {
            List<Card> cardsOfSameSuit = cardsMapBySuits.getOrDefault(s, Collections.emptyList());
            if (cardsOfSameSuit.size() >= 5 && checkRoyalRunningOrder(cardsOfSameSuit, s))
                return true;
        }
        return false;
    }

    private boolean checkRoyalRunningOrder(List<Card> cardsOfSameSuit, final Suit s) {
        if (    cardsOfSameSuit.contains(cardFactory.getCard(s,Rank.TEN)) &&
                cardsOfSameSuit.contains(cardFactory.getCard(s,Rank.JACK)) &&
                cardsOfSameSuit.contains(cardFactory.getCard(s,Rank.QUEEN)) &&
                cardsOfSameSuit.contains(cardFactory.getCard(s,Rank.KING)) &&
                cardsOfSameSuit.contains(cardFactory.getCard(s,Rank.ACE)))
            return true;
        else
            return false;
    }
}
