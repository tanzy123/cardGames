package com.tzy.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

    private List<Card> cards;

    public DeckOfCards(){
        cards = new ArrayList<>();
        for (Rank r: Rank.values()){
            for (Suit s: Suit.values()){
                cards.add(new Card(s,r));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card deal() {
        Card card = cards.get(cards.size()-1);
        cards.remove(cards.size()-1);
        return card;
    }

    public void burn(){
        cards.remove(cards.size()-1);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
}
