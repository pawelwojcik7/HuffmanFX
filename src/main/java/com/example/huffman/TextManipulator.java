package com.example.huffman;


import java.util.*;
import java.util.stream.Collectors;

public class TextManipulator {

    String slowo;
    int dlugosc;
    List<Character> uniqueList;
    List<Character> chars;
    List<Integer> occurrencesList;
    List<Double> charsProbabilityList;

    String[][] tab;
    public TextManipulator(String str) {
        this.slowo = str;
        this.dlugosc = str.length();
        this.chars = convertStringToCharList(str);
        this.uniqueList = uniqueCharsFromList(chars);
        this.occurrencesList=charsOccurrences(chars,uniqueList);
        this.charsProbabilityList= charsProbability(occurrencesList);
        this.tab=makeArray();
        Arrays.sort(tab, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(Double.parseDouble(o1[3])>Double.parseDouble(o2[3])) return 1;
                else return -1;
            }
        });
    }

    public static List<Character> convertStringToCharList(String str) { // konwersja łańcucha znaków na Listę znaków
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }

    public static List<Character> uniqueCharsFromList(List<Character> list) { // wybranie wartości unikalnych z Listy znaków
        List<Character> uniqueList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!uniqueList.contains(list.get(i))) {
                uniqueList.add(list.get(i));
            }
        }
        return uniqueList;
    }

    public static List<Integer> charsOccurrences(List<Character> list, List<Character> uniqueList) { // zliczenie wystąpień poszczególnych znaków w Liście
        List<Integer> occurrencesList = new ArrayList<>();
        for (Character c : uniqueList) {
            occurrencesList.add(Collections.frequency(list, c));
        }
        return occurrencesList;

    }

    public static List<Double> charsProbability(List<Integer> occurrencesList) { // wyliczenie prawdopodobieństw
        List<Double> charsProbabilityList = new ArrayList<>(); // lista prawdopodobienstw
        int suma = occurrencesList.stream().mapToInt(Integer::intValue).sum(); // suma elementow z listy
        charsProbabilityList = occurrencesList.stream().map(e -> (double) Math.round(e*10000000 / suma)/10000000).collect(Collectors.toList());
        return charsProbabilityList;
    }
    public String[][] makeArray()
    {
        String[][] table = new String[this.uniqueList.size()][5];
        for(int i=0;i<this.uniqueList.size();i++)
        {
            table[i][0]= String.valueOf(i);
            table[i][1]=String.valueOf(this.uniqueList.get(i));
            table[i][2]=String.valueOf(this.occurrencesList.get(i));
            table[i][3]=String.valueOf(this.charsProbabilityList.get(i));
            table[i][4]= String.valueOf("0");

        }
        return table;
    }
}
