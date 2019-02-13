package com.bbd.pingpong;

import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RomanConverter {
    private static final Map<String, Integer> conversions = new HashMap<>();
    private static final Map<String, Integer> pairs = new HashMap<>();

    static {
        conversions.put("I", 1);
        conversions.put("V", 5);
        conversions.put("X", 10);
        conversions.put("L", 50);
        conversions.put("C", 100);
        conversions.put("D", 500);
        conversions.put("M", 1000);

        pairs.put("IV", 4);
        pairs.put("IX", 9);
        pairs.put("XL", 40);
        pairs.put("XC", 90);
        pairs.put("CD", 400);
        pairs.put("CM", 900);
    }


    private static boolean isValidCharacter(String c){
        return conversions.containsKey(c);
    }

    public static Pair<String> convertPair(Pair<String> pair) {
        int firstNumber =conversions.get(pair.getFst());
        if (pair.getSnd().isEmpty()) {
            pair.setSkipNext(false);
            pair.setPairValue(firstNumber);
            return pair;
        } else {
            int secondNumber = conversions.get(pair.getSnd());
            if (firstNumber >= secondNumber) {
                pair.setSkipNext(false);
                pair.setPairValue(firstNumber);
                return pair;
            } else {
                String pairString = pair.getFst() + "" + pair.getSnd();
                if (pairs.containsKey(pairString)) {
                    pair.setSkipNext(true);
                    pair.setPairValue(pairs.get(pairString));
                    return pair;
                } else {
                    throw new IllegalArgumentException("Invalid input order");
                }
            }
        }
    }

    public static int convert(String romanInput) {
        String uppercaseOnlyRoman = romanInput.toUpperCase();

        if (!Arrays.stream(uppercaseOnlyRoman.split("")).allMatch(RomanConverter::isValidCharacter)){
            throw new IllegalArgumentException("Invalid input");
        }

        List<String> first = Arrays.stream(uppercaseOnlyRoman.split("")).collect(Collectors.toList());
        first.add("");


        List<Pair> pairs = IntStream.range(0, first.size() - 1).mapToObj(i -> new Pair(first.get(i), first.get(i + 1))).collect(Collectors.toList());

        if(checkForDuplicateSubtractions(pairs)){
            throw new IllegalArgumentException("???");
        }

        final boolean[] skipNext = {false};
        int sum = pairs.stream()
                .mapToInt(p-> {
                    if(skipNext[0]){
                        skipNext[0] =false;
                        return 0;
                    } else {
                        Pair<String> result = RomanConverter.convertPair(p);
                        skipNext[0] = result.isSkipNext();
                        return result.getPairValue();
                    }

                })
                .sum();
        return sum;
    }

    private static boolean checkForDuplicateSubtractions(List<Pair> pairsString) {
        for (Pair p : pairsString) {
            if (pairs.containsKey(pairsString)) {

            }
        }
        for(Pair p : testPair){
            LinkedList<Pair> theRestOfThePairs = new LinkedList<>(pairsString);
            theRestOfThePairs.remove(p);
            for(Pair p1 : theRestOfThePairs){
                if(p.equals(p1)){
                    return true;
                }
            }
        }
        // if the list has duplicate subtractions return true
                // find all subtractions in the list
                // check for duplicate
        // else return false
    }

    private static class Pair<T> {
        private final T fst;
        private final T snd;
        private boolean skipNext=false;
        private int pairValue = 0;

        public Pair(T fst, T snd) {
            this.fst = fst;
            this.snd = snd;
        }

        public T getFst() {
            return fst;
        }

        public T getSnd() {
            return snd;
        }

        public boolean isSkipNext() {
            return skipNext;
        }

        public void setSkipNext(boolean skipNext) {
            this.skipNext = skipNext;
        }

        public int getPairValue() {
            return pairValue;
        }

        public void setPairValue(int pairValue) {
            this.pairValue = pairValue;
        }
    }
}