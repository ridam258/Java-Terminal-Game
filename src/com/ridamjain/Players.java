package com.ridamjain;

import java.util.*;

public class Players {
    private String name;
    private int coins;
    private double health;
    private ArrayList<Card> playerCards;
    private ArrayList<Card> usedCards;
    private boolean fullStats;
    public Players(String name){
        this.name = name;
        this.coins = 300;
        this.health = 100;
        this.playerCards= new ArrayList<>();
        this.usedCards = new ArrayList<>();
    }
    public Card getCardWithId(int id){
        for (Card playerCard : playerCards) {
            if (id == playerCard.getId()) {
                return playerCard;
            }
        }
        return null;
    }
    public boolean addCard(int cardId){
        List<Card> players = Heroes.getHeroes();
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getId()==cardId){
                if(coins>=players.get(i).getPrice()){
                    playerCards.add(players.get(i));
                    this.coins-=players.get(i).getPrice();
                    return true;
                }else {
                    if(!this.name.toUpperCase().equals("COM")){
                        System.out.println("NOT ENOUGH COINS TRY ANOTHER CARD");
                    }
                    return false;
                }
            }
        }
        if(!this.name.toUpperCase().equals("COM")){
            System.out.println("NO CARD AVAILABLE WITH THIS ID CHOOSE AGAIN");
        }
        return false;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getCoins(){
        return  this.coins;
    }
    public double getHealth() {
        return this.health;
    }
    public void currentStat(boolean fullStats){
        if(fullStats){
            System.out.println(this.name.toUpperCase());
            System.out.print("\uD83D\uDCB0 "+this.coins);
            System.out.println(" \uD83C\uDF94 "+this.health);
            if(this.name=="Player"){
                System.out.println("CHOSEN DECK");
                for (int i = 0; i < playerCards.size(); i++) {
                    playerCards.get(i).showData();
                }
            }

        }
        else {
            System.out.println(this.name.toUpperCase());
            System.out.println("\uD83D\uDCB0 "+this.coins);
            if(this.name=="Player"){
                System.out.println("CHOSEN DECK");
                for (int i = 0; i < playerCards.size(); i++) {
                    playerCards.get(i).showData();
                }
            }
        }

    }
    public void updateStats(int coin, int health, Card selectedCard){
        this.coins+=coin;
        this.health-=health;
        playerCards.remove(selectedCard);
        usedCards.add(selectedCard);

    }
    public void resourceStats(){
        System.out.print("\t\t"+this.name.toUpperCase()+" \uD83D\uDCB0 "+this.coins + " \uD83C\uDF94 " +this.health +"\n");

    }
    public boolean cardRefill(){
        int size = usedCards.size();
        if(playerCards.size()==0){
            for (int i = 0; i < size; i++) {
                    if(coins>=usedCards.get(0).getPrice()){
                        playerCards.add(usedCards.get(0));
                        this.coins-=usedCards.get(0).getPrice();
                        usedCards.remove(usedCards.get(0));
                    }
            }
            if(playerCards.size()==0){
                return true;
            }
        }
        return false;
    }
}
