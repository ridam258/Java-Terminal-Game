package com.ridamjain;

import java.util.ArrayList;
import java.util.List;

public class Heroes {
    private static final List<Card> heroes =new ArrayList<>();
    public static List<Card> getHeroes(){
      return heroes;
    };
    public static void createHeroes() {
        heroes.add(new Card("Alpha", 1));
        heroes.add(new Card("Beta", 2));
        heroes.add(new Card("Charlie", 3));
        heroes.add(new Card("Delta", 4));
        heroes.add(new Card("Echo", 5));
        heroes.add(new Card("Foxtrot", 6));
        heroes.add(new Card("Golf", 7));
        heroes.add(new Card("Hotel", 8));
        heroes.add(new Card("India", 9));
        heroes.add(new Card("Juliette", 10));
    }
}

