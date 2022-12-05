package userinterface;

import Comparator.TimeComparator;
import domain.Controller;
import domain.Events;
import domain.Swimmer;
import domain.SwimTime;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TrainerUserInterfaceTest {

    Controller controller = new Controller();


    @Test
    void topTimeForEachSwimmer(){
        controller.initialLoad();
        ArrayList<SwimTime> localTimeList = controller.getSwimTimeList();
        ArrayList<Swimmer> localSwimmerList = controller.getSwimmerList();
        ArrayList<SwimTime> localTopTimesList = new ArrayList<>();
        ArrayList<SwimTime> top5Times = new ArrayList<>();

        for(Swimmer s : localSwimmerList){ //Disse to for loops samler alle svømmetider for et bestemt hold i en bestemt disciplin
            for(SwimTime st : localTimeList){
                if(st.getMemberID() == s.getMemberID() && st.getEvent().equals(Events.CRAWL50M )&& !st.getPlaceSet().equals("Træning")){
                    localTimeList.add(st);
                }
            }
    }
        Collections.sort(localTopTimesList, new TimeComparator());



        for(Swimmer s : localSwimmerList) {
            for (SwimTime st : localTimeList) {
                if (s.getMemberID() == st.getMemberID()){
                    top5Times.add(st);
                    break;
                }
            }
        }


        for(SwimTime st: top5Times){
            System.out.println(st.getTime() + " " + st.getMemberID());
        }
        assertEquals(30.42,top5Times.get(0).getTime());
    }



}