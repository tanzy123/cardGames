package com.tzy.poker;

import com.tzy.cards.Card;
import com.tzy.cards.Dealer;
import com.tzy.cards.DeckOfCards;
import com.tzy.cards.Player;

import java.util.ArrayList;
import java.util.List;

public class PokerDealer implements Dealer {

    private List<Card> flop = new ArrayList<>();
    private Card turn;
    private Card river;

    @Override
    public void deal(DeckOfCards deck, List<Player> players) {
        deck.shuffle();
        dealCardsToPlayers(deck, players);
        dealCommunityCards(deck);
    }

    private void dealCardsToPlayers(DeckOfCards deck, List<Player> players) {
        for (int i=0;i<2;i++) {
            for (Player player : players)
                player.receiveCard(deck.deal());
        }
    }

    private void dealCommunityCards(DeckOfCards deck) {
        deck.burn();
        getFlop(deck);
        deck.burn();
        getTurn(deck);
        deck.burn();
        getRiver(deck);
    }

    private void getRiver(DeckOfCards deck) {
        river = deck.deal();
    }

    private void getTurn(DeckOfCards deck) {
        turn = deck.deal();
    }

    private void getFlop(DeckOfCards deck) {
        flop.add(deck.deal());
        flop.add(deck.deal());
        flop.add(deck.deal());
    }

    public List<Card> getFlop() {
        return flop;
    }

    public void setFlop(List<Card> flop) {
        this.flop = flop;
    }

    public Card getTurn() {
        return turn;
    }

    public void setTurn(Card turn) {
        this.turn = turn;
    }

    public Card getRiver() {
        return river;
    }

    public void setRiver(Card river) {
        this.river = river;
    }

    public List<Card> getCommunityCards(){
        List<Card> communityCards = new ArrayList<>(flop);
        communityCards.add(turn);
        communityCards.add(river);
        return communityCards;
    }
}
