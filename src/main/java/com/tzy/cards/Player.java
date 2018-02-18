package com.tzy.cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>();

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void receiveCard(Card card){
        hand.add(card);
    }

    public void emptyHand(){
        hand.clear();
    }
}
