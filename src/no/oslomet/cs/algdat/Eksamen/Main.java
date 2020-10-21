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


        //test for oppgave 1:

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre4= new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi:a){

            tre4.leggInn(verdi);
        }
        System.out.println(tre.antall()); //utskrift 10

    }
}
