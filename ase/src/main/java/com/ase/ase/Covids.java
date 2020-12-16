package com.ase.ase;
import java.util.ArrayList;
import java.util.List;

public class Covids{
        private List<Covid> covidList;

        public List<Covid> getCovidList() {
            if(covidList == null) {
                covidList = new ArrayList<>();
            }
            return covidList;
        }

        public void setCovidList(List<Covid> covidList) {
            this.covidList = covidList;
        }
}
