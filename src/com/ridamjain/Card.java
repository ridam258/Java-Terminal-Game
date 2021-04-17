package com.ridamjain;

public class Card {
    private int  damage;
    private  String name;
    private  int lifePoints;
    private int id;
    private  int price;

    public Card(String s, int id){
        this.name = s;
        this.damage = Util.CalculateDamage();
        this.lifePoints = Util.getRandomNumber(50,71);
        this.id = id;
        this.price = (int)(damage*3+lifePoints/damage*2-damage)-25;
    }
    public String getName(){
        return this.name;
    }
    public  int getDamage(){
        return  this.damage;
    }
    public int getLifePoints(){
        return  this.lifePoints;
    }
    public int getId(){
        return  this.id;
    }

    public int getPrice() {
        return price;
    }

    public  void showData(){
        System.out.println(this.getId()+" | " + this.getName()+" | D-" + this.getDamage()+" | HP-"+this.getLifePoints()+ " | Rs-" +this.getPrice());
    }
}
