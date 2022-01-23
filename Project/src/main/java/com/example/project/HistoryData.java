package com.example.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryData implements Serializable {//dane sa serializowane PO zakonczeniu pierwszego quizu
        String login;
    ArrayList<ArrayList<String>> odp;
    ArrayList<ArrayList<Integer>> daneDoPytan = new ArrayList<>(6);
    int size = 0;
    HistoryData()
    {
        this("",null,null);
    }
    HistoryData(String log,ArrayList<String> odpowiedzi, ArrayList<Integer> dane) {
        size++;
        this.login = log;

        this.odp = new ArrayList<>();
        this.odp.add(new ArrayList<>(odpowiedzi));//copy constructor

        this.daneDoPytan = new ArrayList<>();
        this.daneDoPytan.add(new ArrayList<>(dane));
    }

    public void saveData(ArrayList<String> odpowiedzi,ArrayList<Integer> dane)
    {
        this.odp.add(new ArrayList<>(odpowiedzi));
        this.daneDoPytan.add(new ArrayList<>(dane));
        if(odp.size() > 4)
        {
            this.odp.remove(0);
            this.daneDoPytan.remove(0);
        }
    }

    public int getSize()
    {
        return daneDoPytan.size();
    }

}
