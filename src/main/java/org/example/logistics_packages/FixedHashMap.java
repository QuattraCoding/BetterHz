package org.example.logistics_packages;
import org.example.Frequency;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FixedHashMap {
    int countHowManyTimesHasBeenDone = 0;
    int index;
    int timesSampleShouldBePlayed = 5;
    public ArrayList<Integer> timesPlayed = new ArrayList<>();
    public void addPoint(int index){
        timesPlayed.set(index, timesPlayed.get(index) + 1);
    }
    private HashMap<Integer, Frequency> frequencyHashMap = new HashMap<>();
    public HashMap<Integer, Frequency> getFrequencyHashMap() {
        return frequencyHashMap;
    }

    public FixedHashMap(List<Frequency> frequencies){
        int index = 0;
        for (Frequency f : frequencies){
            this.frequencyHashMap.put(index, f);
            timesPlayed.add(0);
            index++;
        }

    }
    public void refactorHashMap(List<Frequency> frequencies) {

        index = 0;
        if (frequencyHashMap.isEmpty()) {
            System.out.println("no frequencies in hashmap.");
        } else {
            try {
                while (index < timesPlayed.size()) {
                    if (index == timesPlayed.size()) {
                        if (timesPlayed.get(index) <= timesSampleShouldBePlayed) {
                        } else if (timesPlayed.get(index) > timesSampleShouldBePlayed) {
                            timesPlayed.remove(index);
                            frequencies.remove(index);
                            frequencyHashMap.remove(index);

                            System.out.println("removed: " + frequencies.get(index).getName() + "and its timesplayed + hashmap instance.");
                        }
                    } else if (index < timesPlayed.size()) {
                        if (timesPlayed.get(index) <= timesSampleShouldBePlayed) {
                            index++;
                        } else if (timesPlayed.get(index) > timesSampleShouldBePlayed) {
                            timesPlayed.remove(index);
                            frequencies.remove(index);
                            frequencyHashMap.remove(index);
                            index++;

                            System.out.println("removed: " + frequencies.get(index).getName() + "and its timesplayed + hashmap instance.");
                        }
                    }
                    countHowManyTimesHasBeenDone++;
                    System.out.println(this.frequencyHashMap.get(index).getName() + " och "+ this.frequencyHashMap.get(index).getFrequency());
                }
                System.out.println("done refactoring timesplayed and Hashmap");
                System.out.println();

            } catch (RuntimeException e) {
                System.out.println("turbogay");
                System.out.println();
            }
        }
    }
}
