package com.ridamjain;

import java.util.List;
import java.util.Scanner;

public class GameManager {
    private boolean playerLost= false;
    private boolean computerLost= false;
    private Players user;
    private  Players com;
    private  static final Scanner input = new Scanner(System.in);
    public void startGame(){
        Heroes.createHeroes();
        menu();

    }
    private void menu(){
        System.out.println("Select An Option:");
        System.out.println("1. Play Game");
        System.out.println("2. How To Play");
        int menuSelection = input.nextInt();
        if(menuSelection==1){
            playGame();
        }
        else if(menuSelection ==2){
            howToPlay();
        }
        else{
            System.out.println("INVALID CHOICE");
        }
    }
    private void howToPlay(){
        System.out.println("1. Choose Play Game In The Menu Section");
        System.out.println("2.Choose Your Deck With The Id Displayed Above");
        System.out.println("3. When You Are Done Choosing Press 0 And Enter");
        System.out.println("4. To Attack With Your Card Type Card Id From Your Deck Shown Above");
        System.out.println("5. Continue Step 4 Until Game Finishes");
        System.out.println("To Play Press 1 Or To Quit Press 0");
        int inp= input.nextInt();
        if (inp==1){
            playGame();
        }
    }
    private void playGame(){
        com = new Players("Computer");
        user = new Players("Player");
        cardShop();
    }
    private void cardShop(){
        List<Card> heroes = Heroes.getHeroes();
        for (Card hero : heroes) {
            hero.showData();
        }
        boolean doneChoosing;
        doneChoosing = false;
        while(user.getCoins()>0 && !doneChoosing){
            System.out.println("CHOOSE CARDS WITH ID (0 to finish)");
            int cardId = input.nextInt();
            if(cardId==0){
                doneChoosing=true;
                continue;
            }
            user.addCard(cardId);
            user.currentStat(false);
        }
        while(com.getCoins()>0){
            int randomCardId=Util.getRandomNumber(1,heroes.size()+1);
            boolean success = com.addCard(randomCardId);
            if(!success){
                break;
            }
        }
        com.currentStat(false);
        while (user.getHealth()>0&&com.getHealth()>0) {
            playerLost = user.cardRefill();
            computerLost = com.cardRefill();
            boolean over = gameOver();
            if(over){
                return;
            }
            playCards();
        }
    }

    private void playCards() {
        user.currentStat(true);
        System.out.println("Choose A Card To Attack");
        int attackingCard = input.nextInt();
        Card attackCard = user.getCardWithId(attackingCard);
        if(attackCard==null){
            System.out.println("INVALID CARD ID TRY AGAIN");
        }
        else{
            Card computerChosenCard=selectComputerCard();

            attack(attackCard, computerChosenCard);
        }

    }

    private Card selectComputerCard() {
        int randomCardId = Util.getRandomNumber(1,Heroes.getHeroes().size()+1);
        Card attackCard = com.getCardWithId(randomCardId);
        if(attackCard == null){
            return selectComputerCard();
        }
            return attackCard;
    }
    private void attack(Card playerChosenCard, Card computerChosenCard){
        System.out.println("\n\n\n\n");
        System.out.println("****************Attack Started**********");
        System.out.println("\t\t\t"+playerChosenCard.getName()+" vs "+computerChosenCard.getName());
        Util.printer("\t\t\t ⚔⚔⚔⚔⚔⚔⚔⚔⚔\n", 100);
        Util.createDelay(1);
//        int playerDamageTaken = ((int) (((computerChosenCard.getDamage()*3)+computerChosenCard.getLifePoints())/11));
        int playerDamageTaken = ((int) ((computerChosenCard.getDamage()*.8) + (computerChosenCard.getLifePoints()*.6))/5)+Util.getRandomNumber(2,5);
        int computerDamageTaken = ((int) ((playerChosenCard.getDamage()*.8) + (playerChosenCard.getLifePoints()*.6))/5)+Util.getRandomNumber(2,6);
//        int computerDamageTaken = ((int) (((playerChosenCard.getDamage()*3)+playerChosenCard.getLifePoints())/16));
        int playerCoinsGained = (int) (computerDamageTaken*1.5);
        int computerCoinsGained = (int) (playerDamageTaken*1.5);
        user.updateStats(playerCoinsGained, playerDamageTaken, playerChosenCard);
        com.updateStats(computerCoinsGained, computerDamageTaken, computerChosenCard);
        System.out.println("\t\tHealth: -"+playerDamageTaken );
        System.out.println("\t\tCoins: +"+playerCoinsGained );
        System.out.println("\t\tDamage Given: "+computerDamageTaken);
        user.resourceStats();
        com.resourceStats();
        System.out.println("****************Attack Over*************");
        boolean over = gameOver2();
        if(over){
            return;
        }
        System.out.println("\n\n\n\n");

    }

    private boolean gameOver2() {
        if(user.getHealth()<=0&&user.getHealth()<com.getHealth()){
            System.out.println("Bad Luck!!! You Lose!");
            return  true;
        }
        else if(com.getHealth()<=0&&com.getHealth()<user.getHealth()){
            System.out.println("Congrats! You Won");
            return true;
        }
        return false;
    }

    private boolean gameOver(){
        if(playerLost){
            System.out.println("Game Over You Lost");
            return true;
        }
        else if(computerLost){
            System.out.println("Congrats! You Won");
            return true;
        }
        return false;
    }
}
