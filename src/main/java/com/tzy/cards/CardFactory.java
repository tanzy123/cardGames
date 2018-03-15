package com.tzy.cards;

import java.util.HashMap;
import java.util.Map;

public class CardFactory {

    private static CardFactory instance;
    private static final Object lock = new Object();
    private Map<Integer,Card> cardMap = new HashMap<>();

    public static CardFactory getInstance(){
        synchronized (lock) {
            if (instance == null) {
                instance = new CardFactory();
                return instance;
            } else
                return instance;
        }
    }
    private CardFactory(){}

    public Card getCard(Suit s, Rank r){
        int key = generateCardKey(s,r);
        cardMap.putIfAbsent(key, new Card(s, r));
        return cardMap.get(key);
    }

    private int generateCardKey(Suit s, Rank r) {
        int key = 0;
        switch(s){
            case DIAMONDS:
                key += 100;
                break;
            case CLUBS:
                key += 200;
                break;
            case HEARTS:
                key += 300;
                break;
            case SPADES:
                key += 400;
                break;
        }
        key += r.getValue();
        return key;
    }
}
