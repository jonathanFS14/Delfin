package Comparator;

import domain.SwimTime;

import java.util.Comparator;

public class TimeComparator implements Comparator<SwimTime> {
    public int compare(SwimTime s1, SwimTime s2){
        int compare;
        double stCompare = s1.getTime()-s2.getTime();
        if(stCompare<0)
            compare = -1;
        else if(stCompare>0)
            compare = 1;
        else
            compare = 0;

        return compare;
    }
}
