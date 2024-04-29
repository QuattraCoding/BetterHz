package org.example;

import org.example.logistics_packages.FixedHashMap;


import java.util.List;
import java.util.Random;

public class Logistics {

    List<Frequency> frequencies;
    Random random = new Random();
    FixedHashMap fixedHashMap;

    public Logistics(List<Frequency> frequencies){
        frequencies = frequencies;
        fixedHashMap = new FixedHashMap(frequencies);
    }
    public Frequency ChooseFrequency(){

        int i = rollNumber();
        for(int e = 0; 0 <= frequencies.size(); e++ ){
            System.out.println(frequencies.get(i).getFrequency() + " " + frequencies.get(i).getName());
        }
        System.out.println();
        System.out.println();
        System.out.println("frekvens som valdes: " + frequencies.get(i).getFrequency() + " " + frequencies.get(i).getName());
        System.out.println("int som kopplas till frekvens: " + i);
        Frequency f = fixedHashMap.getFrequencyHashMap().get(i);
        fixedHashMap.addPoint(i);
        System.out.println(fixedHashMap.getFrequencyHashMap().get(i));

        return f;
    }

    public int rollNumber(){
        reDefineHashMapAndEverythingAssociated();
        System.out.println("rollnumber process started.");
        int i = random.nextInt(fixedHashMap.getFrequencyHashMap().size());
        return i;


    }
    public void reDefineHashMapAndEverythingAssociated(){
            fixedHashMap.refactorHashMap(frequencies);

    }
}
