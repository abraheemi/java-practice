package com.abraheem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Expected output based on file.txt:
 *
 * Geeks : 6
 * and : 4
 * to : 4
 * well : 3
 * your : 3
 */
public class MostFrequent {
    File file;
    HashMap<String, Integer> map = new HashMap<>();

    public MostFrequent(String filename){
        file = new File("src\\com\\abraheem\\input\\" + filename);
    }

    public void findMostFrequentWords(int k){
        if(!buildHashMap(k)){
            System.out.println("Could not read file");
            return;
        }
        Map<String, Integer> sortedMap = sortByValues();
        
        sortedMap.forEach((key, value) -> System.out.println(key + ": " + value));


    }

    private boolean buildHashMap(int k) {
        try {
            Scanner scanner = new Scanner(file);
            //HashMap<String, Integer> map = new HashMap<>();
            while (scanner.hasNext()) {
                //System.out.println(scanner.next().toLowerCase());
                String s = scanner.next();
                if (!map.containsKey(s)) {
                    map.put(s, 1);
                } else {
                    map.put(s, map.get(s) + 1);
                }
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    HashMap<String, Integer> sortByValues(){
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        // Put data from sorted list to hashmap
        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            sortedMap.put(aa.getKey(), aa.getValue());
        }
        return sortedMap;
    }

}