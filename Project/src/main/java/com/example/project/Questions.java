package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Grzegorz Poreda
 */

/**Klasa zawierająca wszystkie pytania i metody/pola pomocnicze*/
public class Questions {
    /**Zmienna przechowywujaca gamemode*/
    static public boolean gamemode;
    /**Zmienna zawierająca aktualny index pytania*/
    static private int questionIndex = 0;
    /**Zmeinna zawierająca całkowity czas rozwiązywanych pytań*/
    static private int timeAll = 0;
    /**Zmienna zawierająca aktualny wynik*/
    static private int punctation = 0;
    /**Tablica zawierająca na które pytania udało nam się odpowiedzieć*/
    static private boolean[] AnswerQuestion = {false, false, false, false, false, false, false, false, false, false};
    /**Lista zawierająca konkretne odpowiedzi do pytania*/
    static public ArrayList<String > odpowiedzi = new ArrayList<>();

//    static public ArrayList<String[]> polskiPytania = new ArrayList<>(
//        Arrays.asList(
//                new String[]{"Stopień wyższy od wyrazu dobry to", "lepszy", "najlepszy", "dobre", "zły"},
//                new String[]{"Z ilu sylab składa się wyraz mammografia?", "4", "11", "7", "6"},
//                new String[]{"Jak nazywa się część zdania określająca rzeczownik?", "przydawka", "orzeczenie",
//                        "okolicznik", "podmiot"},
//                new String[]{"Jak brzmi liczba mnoga wyrazu \"kisiel\"?", "Kisiele", "Kiśle", "Kisiels", "Kisiel's"},
//                new String[]{"Z ilu głosek składa się wyraz mammografia?", "11", "4", "7", "6"},
//                new String[]{"Biernik liczby mnogiej od wyrazu \"balsam\" to:", "balsamy", "balsamów", "balsamom",
//                        "balsamami"},
//                new String[]{"Kto jest autorem \"Ferdydurke\"?", "Witold Gombrowicz", "Władysław Broniewski",
//                        "Jerzy Putrament", "Stanicław Ignacy Witkiewicz"},
//                new String[]{"Wyraz \"wesprzeć\" w 1. osobie liczby pojedynczej czasu przyszłego brzmi:", "wesprę",
//                        "wesprzemy", "wsparłem", "wesprzesz się"},
//                new String[]{"Tematem przewodnim której powieści jest miłość Justyny Orzelskiej i Jana Bohatyrowicza?",
//                        "Nad Niemnem", "Noce i dnie", "Lalka", "Między ustami a brzegiem pucharu"},
//                new String[]{"Tumany pyłu, kurzu czy śniego możemy określić wyrazem k__awa. Wybierz odpowiednią " +
//                        "konfigurację liter.", "u, rz", "u, ż", "ó, ż", "ó, rz"}
//                ));
    static public final ArrayList<String[]> polskiPytaniaEasy = new ArrayList<>(
            Arrays.asList(
                    new String[]{"Ile sylab ma wyraz lokomotywa?","5","3","8","10"},
                    new String[]{"Na jakie pytanie odpowiada biernik?","kogo? co?","kto? co?","kogo? czego?","komu? czemu?"},
                    new String[]{"Jakie powieści pisał Stanisław Lem?","Science-fiction","Kryminalne","Przygodowe","Historyczne"},
                    new String[]{"Jaką częścią mowy jest wyraz hej!?","Wykrzyknieniem","Partykułą","Spójnikiem","Zaimkiem"},
                    new String[]{"Jak nazywa się utwór zawierajacy opis koncertu Wojskiego?","Pan Tadeusz","Pan Wołodyjowski","W pustyni i puszczy","Akademia Pana Kleksa"},
                    new String[]{"Kto napisał książkę o przygodach hobbita i trzynastu krasnoludów?","J.R.R. Tolkien","C.S. Lewis","J. Flanagan","R. Riordan"},
                    new String[]{"Przed którym ze spójników nie stawiamy przecinka?","i","że","żeby","ponieważ"},
                    new String[]{"Z jakiego gatunku jest znany Ignacy Krasicki?","Bajek","Powieści fantasy","Powieści kryminalne","Biografii"},
                    new String[]{"Jak będzie brzmiał dopełniacz liczby mnogiej rzeczownika książka?","książek","książkom","książki","książkami"},
                    new String[]{"Ile było panien roztropnych w przypowieści biblijnej","5","10","3","1"}
            ));
    static public final ArrayList<String[]> polskiPytaniaMed = new ArrayList<>(
            Arrays.asList(
                    new String[]{"Jak ma na imię bohater ugodzony w piętę pod Troją?","Achilles","Arystoteles","Parys","Hektor"},
                    new String[]{"Jak miała na imię wybranka Filona?","Laura","Beatrycze","Lidia","Beata"},
                    new String[]{"Jakie były sklepy w tytule jednego z tomików opowiadań Brunona Schulza?","Cynamonowe","Czekoladowe","Kamienne","Senne"},
                    new String[]{"Jaki taniec możemy odnaleźć w tytule dramatu Sławomira Mrożka?","Tango","Polonez","Walc","Kujawiak"},
                    new String[]{"Jaką końcówkę przyjmują imiesłowy przysłówkowe współczesne?","-ąc","-łszy, -wszy","-ty, -ny, -ony","-ący"},
                    new String[]{"Kto w 1980 roku otrzymał Literacką Nagrodę Nobla?","Czesław Miłosz","Henryk Sienkiewicz","Wisława Szymborska","Witold Gombrowicz"},
                    new String[]{"Kto stworzył postać doktora Tomasza Judyma?","Stefan Żeromski","Zofia Nałkowska","Adam Mickiewicz","Juliusz Słowacki"},
                    new String[]{"Kto w literaturze polskiej tworzył wybitne poematy heroikomiczne?","Ignacy Krasicki","Józef Kraszewski","Franciszek Karpiński","Franciszek Kniaźnin"},
                    new String[]{"Jaki utwór można zaliczyć do gatunku powiastek filozoficznych?","Kandyd","Myszeida","Cud mniemany","Uwagi nad życiem Jana Zamoyskiego"},
                    new String[]{"Kto napisał powieść epistelarną o Werterze?","J.W. Goethe","A. Mickiewicz","J. Słowacki","G.G. Byron"}
            ));
    static public final ArrayList<String[]> polskiPytaniaHard = new ArrayList<>(
            Arrays.asList(
                    new String[]{"Hagiografia zajmuje się życiem...?","Świętych","Pisarzy","Bohaterów literackich","Bóstw greckich"},
                    new String[]{"Jak nazywa się gatunek, który reprezentuje utwór o Rolandzie?","Chanson de geste","Epos","Hymn","Powieść"},
                    new String[]{"Do kogo pisał swoje sonety Petrarka?","Laury","Klary","Leokadii","Kunegundy"},
                    new String[]{"Kto prowadził spór o mur w komedii Fredry?","Cześnik i Rejent","Podstoli i Marszałek","Hrabia i Sędzia","Wojski i Klucznik"},
                    new String[]{"Co nie jest jedną z cech dramatu antycznego?","komizm","zasada decorum","zasada trzech jedności","patos"},
                    new String[]{"Ile sylab w każdym wersie jest w polskiej epopei narodowej?","13","11","7","9"},
                    new String[]{"Dystych nie jest?","gatunkiem literackim","dwuwierszem","strofą","utworem"},
                    new String[]{"Za jednego z trzech wieszczy Polski nie jest uznawany?","Norwid","Mickiewicz","Słowacki","Krasiński"},
                    new String[]{"Turpizmem nie posługiwał się w swojej poezji?","Kochanowski","Grochowiak","Różewicz","Morsztyn"},
                    new String[]{"Jak nazywa się postać stworzona przez Zbigniewa Herberta, która pojawia się w wielu jego wierszach?","Pan Cogito","Sancho Pansa","Jan","Fortynbras"}
            ));
    static public final ArrayList<String[]> angielskiPytania = new ArrayList<>(
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

    static public final ArrayList<String[]> matematykaPytania = new ArrayList<>(
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
                    new String[]{"Trójkąt prostokątny ma boki: przyprostokątne 'a' i 'b', przeciwprostokątną 'c'." +
                            "Zaznacz prawdę", "a*a + b*b = c*c", "a*a + c*c = b*b", "b*b + c*c = a*a", "a + b = c"},
                    new String[]{"2 + 2 * 2", "6", "8", "22", "222"},
                    new String[]{"MCMXCIX to rok", "1999", "1989", "1111", "1000"}
            ));

    static public final ArrayList<String[]> historiaPytania = new ArrayList<>(
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
    /**Metoda zwracająca aktualny index pytania*/
    public static int getIndex(){return questionIndex;}
    /**Metoda zerująca index*/
    public static void resetIndex(){questionIndex = 0;}
    /**Metoda zwiekszająca index o 1*/
    public static void incrementIndex(){questionIndex++;}
    /**Metoda zwieksza całkowity czas o time*/
    public static void addTime(int time){timeAll+=time;}
    /**Metoda zerująca czas*/
    public static void resetTime(){timeAll = 0;}
    /**Metoda zwracająca aktualny całkowity czas*/
    public static int getTime(){return timeAll;}
    /**Metoda zwracająca aktualną punktację*/
    public static int getPunctation(){return punctation;}
    /**Metoda zwiększająca punktację w oparciu o czas odpowiedzi na pytanie*/
    public static void increasePunctation(int time){punctation += 54*time;}
    /**Metoda zerująca punktację*/
    public static void resetPunctation(){punctation = 0;}
    /**Metoda zatwierdzająca, że pytanie o danym indeksie zostało odpowiedziane prawidłowo*/
    public static void changeAnswer(int index){AnswerQuestion[index] = true;}
    /**Metoda zwracająca czy odpowiedz o danym indeksie była prawidłowa*/
    public static boolean getAnswer(int index){return AnswerQuestion[index];}
    /**Metoda resetująca wszystkie odpowiedzi na pytania*/
    public static void resetAnswers(){
        for(int i = 0; i < 10;i++)
        {
            AnswerQuestion[i] = false;
        }
    }
}
