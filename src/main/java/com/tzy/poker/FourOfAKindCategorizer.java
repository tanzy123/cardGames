package com.tzy.poker;

import com.tzy.cards.Card;
import com.tzy.cards.CardFactory;
import com.tzy.cards.Rank;

import java.util.List;
import java.util.Map;

public class FourOfAKindCategorizer implements HandCategorizer {

    private final CardFactory cardFactory;

    public FourOfAKindCategorizer(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    @Override
    public boolean categorize(List<Card> cardsToCategorize) {
        Map<Rank,List<Card>> cardsMapByRank = mapByRank(cardsToCategorize);
        return hasNumberOfKind(4, cardsMapByRank);
    }
}
