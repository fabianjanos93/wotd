package com.codecool.mrguinneapig.wotd.calculator;

import java.util.Arrays;
import java.util.LinkedList;

public class Calculator {

    private String equation;
    private String[] numbers = new String[]{"0","1","2","3","4","5","6","7","8","9"};
    private String[] operators = new String[]{"*","/","+","-"};


    public void setEquation(String equation) {
        this.equation = equation;
    }



    public boolean solvable() {
        String[] butcheredEquation = equation.split("");
        if (Arrays.stream(butcheredEquation).allMatch(element -> contains(element,numbers) || contains(element,operators))) {
            boolean ans = true;
            ans = !(contains(butcheredEquation[0],operators) || contains(butcheredEquation[butcheredEquation.length-1],operators));
            for (int i = 1; i < butcheredEquation.length-1; i++) {
                if(!ans)
                    break;
                if ( contains(butcheredEquation[i],operators)) {
                    ans = (contains(butcheredEquation[i-1],numbers) && contains(butcheredEquation[i+1],numbers));
                }
            }
            return ans;
        }
        return false;
    }

    public boolean contains(String contained, String[] containing){
        for (String element: containing) {
            if(element.equals(contained)){
                return true;
            }
        }
        return false;
    }

    public int solve(){
        if (!solvable())
            return 0;

        LinkedList<String> butcheredEquation = new LinkedList<>();
        butcheredEquation.addAll(Arrays.asList(equation.split("")));
        fromDigitToNumber(butcheredEquation);

        int ans = 0;
        for(String operator: operators) {
            int i = 1;
            while (i < butcheredEquation.size()) {
                if (butcheredEquation.get(i).equals(operator)) {
                    int previousNumber = Integer.parseInt(butcheredEquation.get(i - 1));
                    int nextNumber = Integer.parseInt(butcheredEquation.get(i + 1));
                    switch (operator) {
                        case "*":
                            butcheredEquation.set(i, (Integer.toString(previousNumber * nextNumber)));
                            break;
                        case "/":
                            butcheredEquation.set(i, (Integer.toString(previousNumber / nextNumber)));
                            break;
                        case "+":
                            butcheredEquation.set(i, (Integer.toString(previousNumber + nextNumber)));
                            break;
                        case "-":
                            butcheredEquation.set(i, (Integer.toString(previousNumber - nextNumber)));
                            break;
                    }
                    butcheredEquation.remove(i + 1);
                    butcheredEquation.remove(i - 1);
                } else {
                    i++;
                }
            }
        }
        return Integer.parseInt(butcheredEquation.get(0));
    }

    public void fromDigitToNumber(LinkedList<String> butcheredEquation) {
        int i = 0;
        while(i+1 < butcheredEquation.size()){
            if(contains(butcheredEquation.get(i),numbers)) {
                int j = i + 1;
                while ( i+1 < butcheredEquation.size()){
                    if (!contains(butcheredEquation.get(j),numbers)) {
                        break;
                    }
                    butcheredEquation.set(i, butcheredEquation.get(i)+butcheredEquation.get(j));
                    butcheredEquation.remove(j);
                }
            }
            i++;
        }
    }
}
