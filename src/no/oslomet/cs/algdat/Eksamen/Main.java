package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class Main {

    public static void main(String[]args){

        //innledning:
        EksamenSBinTre<String> tre= new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());

        EksamenSBinTre<Character> tre2= new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre2.antall());

        EksamenSBinTre<Integer> tre3= new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre3.antall());
    }
}
