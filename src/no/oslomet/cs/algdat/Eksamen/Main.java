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
        System.out.println("Opgpave 1.");

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre4= new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi:a){

            tre4.leggInn(verdi);
        }
        System.out.println(tre4.antall()); //utskrift 10
        System.out.println(tre4.leggInn(4));
        System.out.println(tre4.antall()); //utskrift 11

        //test for oppgave 2
        System.out.println("Oppgave 2");

        Integer[] b={4,7,2,9,4,10,8,7,4,6};
        EksamenSBinTre<Integer> tre5 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi: b){
            tre5.leggInn(verdi);
        }

        System.out.println(tre5.antall());//10
        System.out.println(tre5.antall(5));//0

        System.out.println(tre5.antall(4));//3
        System.out.println(tre5.antall(7));//2
        System.out.println(tre5.antall(10));//1


    }
}
