package org.example;

import java.util.List;

public class HzProgram {
    Logistics logistics;

    public HzProgram(List<Frequency> frequencies){
        int index = 0;
    this.logistics = new Logistics(frequencies);
    for(Frequency f : frequencies ){
       Frequency frequency = logistics.ChooseFrequency();
        System.out.println("This frequency is " + frequency.getFrequency() + " hz, and its name is " + frequency.getName());
        index++;
        System.out.println("Try "+index);
    }
    }

}
