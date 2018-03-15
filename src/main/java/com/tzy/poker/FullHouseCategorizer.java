package com.tzy.poker;

import com.tzy.cards.Card;
import com.tzy.cards.CardFactory;
import com.tzy.cards.Rank;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullHouseCategorizer implements HandCategorizer {
    private final CardFactory cardFactory;

    public FullHouseCategorizer(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    @Override
    public boolean categorize(List<Card> cardsToCategorize) {
        Map<Rank,List<Card>> cardsMapByRank = mapByRank(cardsToCategorize);
        return fullHouseCheck(cardsMapByRank);
    }

    private boolean fullHouseCheck(Map<Rank, List<Card>> cardsMapByRank) {
        int pairCount = 0;
        int threeCount = 0;

        for (Rank r: Rank.values()) {
            if (cardsMapByRank.getOrDefault(r, Collections.emptyList()).size() >= 3)
                threeCount++;
            if (cardsMapByRank.getOrDefault(r, Collections.emptyList()).size() >= 2)
                pairCount++;
        }

        if (pairCount >= 2 && threeCount >= 1)
            return true;
        else
            return false;
    }
}
