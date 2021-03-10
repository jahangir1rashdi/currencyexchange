package com.ecb.exchangemarket.model.response;

import java.util.*;

public class Practice {

   // static List<Character> leftBracket = Arrays.asList('[', '{', '(');
    //static List<Character> rightBracket = Arrays.asList(')', '}', ']');
    //Map<String, Integer> leftBracket = Arrays.asList().stream().collect(HashMap::new);


    public static void main(String[] args) {


//        String[] A = {"03210321", "03210321"};
//        String[] B = {"Jahangir", "ink"};
//        System.out.println(solution(A,  B, "03210321"));

//        String S = "00-48 54654 454";

        int y = 2021;
        String startMonth = "June";
        String endMonth = "August";
        String startDay = "Friday";
        System.out.println(solution(y, startMonth, endMonth, startDay));
    }
    static String[] months = {"January", "February", " March", "April", "May", "June", "Jule",
            "August", "September", "October", "November", "December"};
    public static int solution(int Y, String A, String B, String W) {
        Boolean IsLeapYear = Y % 4 == 0;

        A
        return 0;
    }


//    public String solution(String S) {
//        List<Character> specialChar = Arrays.asList(' ', '-');
//        StringBuilder resultSB = new StringBuilder("");
//        int lastSeperator = 0;
//        for (int i = 0; i < S.length(); i++) {
//            if (!specialChar.contains(S.charAt(i))) {
//                resultSB.append(S.charAt(i));
//                if ((i + 4) >= (S.length() - 1)) {
//                    if(  lastSeperator % 2 == 0) {
//                        resultSB.append("-");
//                    }
//
//                    lastSeperator++;
//                } else if (i > 1 && (i + 1) % 3 == 0 && i < (S.length() - 1)) {
//                    resultSB.append("-");
//                }
//            }
//        }
//        return resultSB.toString();
//    }

//    public  String solution(String[] A, String[] B, String P) {
//        // write your code in Java SE 8
//        List<String> names = new ArrayList<>();
//
//        for(int i= 0;i < A.length; i++) {
//            if( B[i].contains(P) ) {
//                names.add(A[i]);
//
//            }
//        }
//        if(names.size() == 0) {
//            return "NO CONTACT";
//        }
//        if(names.size()  == 1) {
//            return names.get(0);
//        }
//        names.sort(Comparator.comparingInt(String::length));
//        return names.get(0);
//
//    }


}

