package com.tzy.poker;

import com.tzy.cards.Card;
import com.tzy.cards.Player;
import com.tzy.cards.Rank;
import com.tzy.cards.Suit;

import java.util.*;

public interface HandCategorizer {

    default Map<Suit, List<Card>> mapBySuits(final List<Card> cardsToBeMapped) {
        Map<Suit, List<Card>> suitCardMap = new HashMap<>();
        for (Card c: cardsToBeMapped) {
            suitCardMap.computeIfAbsent(c.getSuit(), suit -> new ArrayList<>())
                       .add(c);
        }
        return suitCardMap;
    }

    default Map<Rank, List<Card>> mapByRank(final List<Card> cardsToBeMapped) {
        Map<Rank, List<Card>> rankCardMap = new HashMap<>();
        for (Card c: cardsToBeMapped) {
            rankCardMap.computeIfAbsent(c.getRank(), rank -> new ArrayList<>())
                       .add(c);
        }
        return rankCardMap;
    }

    default boolean hasNumberOfKind(int number, Map<Rank, List<Card>> map){
        for (Rank r: Rank.values())
            if (number==map.getOrDefault(r, Collections.emptyList()).size())
                return true;
        return false;
    }

    default boolean hasFlush(Map<Suit, List<Card>> map){
        for (Suit s: Suit.values())
            if (map.getOrDefault(s, Collections.emptyList()).size() == 5)
                return true;
        return false;
    }

    default boolean hasStraight(Map<Rank, List<Card>> map) {
        int count = 0;
        if (map.getOrDefault(Rank.ACE, Collections.emptyList()).size()>0)
            count++;
        for (Rank r: Rank.values()) {
            if (map.getOrDefault(r, Collections.emptyList()).size()>0)
                count++;
            else
                count = 0;
            if (count==5)
                return true;
        }
        return false;
    }

    boolean categorize(final List<Card> cardsToCategorize);
}
