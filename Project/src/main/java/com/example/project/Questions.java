package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Grzegorz Poreda
 */


//Trzeba te wszystkie metody poukladac i powsadzac w odrebne metody, pozamieniac zmienne na prywatne itp
public class Questions {
    static public int questionIndex = 0;
    static public ArrayList<String[]> polskiPytania = new ArrayList<>(
        Arrays.asList(
                new String[]{"Stopień wyższy od wyrazu dobry to", "lepszy", "najlepszy", "dobre", "zły"},
                new String[]{"Z ilu sylab składa się wyraz mammografia?", "4", "11", "7", "6"},
                new String[]{"Jak nazywa się część zdania określająca rzeczownik?", "przydawka", "orzeczenie",
                        "okolicznik", "podmiot"},
                new String[]{"Jak brzmi liczba mnoga wyrazu \"kisiel\"?", "Kisiele", "Kiśle", "Kisiels", "Kisiel's"},
                new String[]{"Z ilu głosek składa się wyraz mammografia?", "11", "4", "7", "6"},
                new String[]{"Biernik liczby mnogiej od wyrazu \"balsam\" to:", "balsamy", "balsamów", "balsamom",
                        "balsamami"},
                new String[]{"Kto jest autorem \"Ferdydurke\"?", "Witold Gombrowicz", "Władysław Broniewski",
                        "Jerzy Putrament", "Stanicław Ignacy Witkiewicz"},
                new String[]{"Wyraz \"wesprzeć\" w 1. osobie liczby pojedynczej czasu przyszłego brzmi:", "wesprę",
                        "wesprzemy", "wsparłem", "wesprzesz się"},
                new String[]{"Tematem przewodnim której powieści jest miłość Justyny Orzelskiej i Jana Bohatyrowicza?",
                        "Nad Niemnem", "Noce i dnie", "Lalka", "Między ustami a brzegiem pucharu"},
                new String[]{"Tumany pyłu, kurzu czy śniego możemy określić wyrazem k__awa. Wybierz odpowiednią " +
                        "konfigurację liter.", "u, rz", "u, ż", "ó, ż", "ó, rz"}
                ));

    static public ArrayList<String[]> angielskiPytania = new ArrayList<>(
            Arrays.asList(
                    new String[]{"Pies po angielsku to", "dog", "cat", "piesh", "PIES!!!"},
                    new String[]{"Przetłumacz słowo \"light\"", "światło", "lekki", "ciężki", "obręcz"},
                    new String[]{"How many years of__________do you have?", "work experience", "working years",
                            "jobbing", "knowledge"},
                    new String[]{"Could you please repeat your sentence? I could not make_________what you said.",
                            "out", "meaning", "up", "over"},
                    new String[]{"I will take_______the new responsibilities gladly.", "on", "into", "up", "in"},
                    new String[]{"This is a private conversation – please_______your own business!", "mind", "state",
                            "care for", "think of"},
                    new String[]{"_________she hardly did any studying before the exam, she managed to pass.",
                            "Although", "However", "Spite", "Despite"},
                    new String[]{"My son is very shy and quiet. In___________, my daughter is very friendly and " +
                            "talkative.", "contrast", "context", "contradiction", "contrary"},
                    new String[]{"Could you_______me a favor? I cannot reach the box on the top shelf.", "do", "give",
                            "let", "make"},
                    new String[]{"The trip was a success because they_________ahead.", "planned", "decided", "set up",
                            "pushed"}
            ));

    static public ArrayList<String[]> matematykaPytania = new ArrayList<>(
            Arrays.asList(
                    new String[]{"Ile w przybliżeniu wynosi liczba PI?", "3.14", "3.15", "567", "2.41"},
                    new String[]{"2 + 2 =", "4", "1", "0", "-7"},
                    new String[]{"Ile wynosi suma kątów w czworokącie?", "360 stopni", "180 stopni", "90 stopni",
                            "100 stopni"},
                    new String[]{"Ile wynosi suma kątów w trójkącie?", "180 stopni", "360 stopni", "90 stopni",
                            "100 stopni"},
                    new String[]{"Ile stopni ma kąt prosty?", "90 stopni", "180 stopni", "360 stopni", "100 stopni"},
                    new String[]{"X+Y=Y+X", "prawda", "fałsz", "półprawda", "półfałsz"},
                    new String[]{"6 * 6 =", "36", "96", "60", "12"},
                    new String[]{"Trójkąt prostokątny ma boki: przyprostokątne 'a' i 'b', przeciwprostokątną 'c'. " +
                            "Zaznacz prawdę", "a*a + b*b = c*c", "a*a + c*c = b*b", "b*b + c*c = a*a", "a + b = c"},
                    new String[]{"2 + 2 * 2", "6", "8", "22", "222"},
                    new String[]{"MCMXCIX to rok", "1999", "1989", "1111", "1000"}
            ));

    static public ArrayList<String[]> historiaPytania = new ArrayList<>(
            Arrays.asList(
                    new String[]{"Chrzest Polski miał miejsce w roku", "966", "1000", "1410", "2022"},
                    new String[]{"Bitwa pod Grunwaldem miała miejsce w roku", "1410", "966", "1000", "2022"},
                    new String[]{"Pierwszym królem Polski był?", "Bolesław Chrobry", "Siemomysł", "Lestek",
                            "Mieszko I"},
                    new String[]{"Który król \"zastał Polskę drewnianą, a zostawił murowaną\"?", "Kazimierz Wielki",
                            "Bolesław Kędzierzawy", "Zygmunt Stary", "Bolesław Krzywousty"},
                    new String[]{"Aktualny hymn Polski to", "Mazurek Dąbrowskiego", "Cicha Noc", "Czerwone Korale",
                            "Master of Puppets"},
                    new String[]{"Która data wyznacza powstanie państwa polskiego?", "966 r.", "nie wiadomo", "996 r.", "1000 r."},
                    new String[]{"Do której dynastii należał Kazimierz Wielki?", "Piastów", "Jagiellonów", "Wazów",
                            "Andegawenów"},
                    new String[]{"Kiedy została uchwalona Konstytucja?", "3 maja 1791", "3 maja 1790", "1 maja 1791",
                            "1 maja 1790"},
                    new String[]{"Daty kolejnych rozbiorów Polski to", "1772, 1793, 1795", "1770, 1794, 1795",
                            "1768, 1794, 1803", "1772, 1790, 1801"},
                    new String[]{"Rok odzyskania niepodległości przez Polskę to", "1918", "1920", "1916", "1914"}
            ));

    public static void main(String[] args) {
        for (String[] strArr : polskiPytania) {
            for (String str : strArr) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        for (String[] strArr : angielskiPytania) {
            for (String str : strArr) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        for (String[] strArr : matematykaPytania) {
            for (String str : strArr) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        for (String[] strArr : historiaPytania) {
            for (String str : strArr) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
