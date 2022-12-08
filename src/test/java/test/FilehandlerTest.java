package test;

import domain.enums.Events;
import domain.SwimTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilehandlerTest {
    SwimTime time1;
    SwimTime time2;

    @Test
    void parseToEnum(){
        String stringToEnum = "CRAWL50M";

        Events crawlEnum = Events.valueOf(stringToEnum);
        int tal = 22;
        assertTrue(crawlEnum == Events.CRAWL50M);
    }

   /* @Test
    void createSwimTime(){
        String swimTimeStævne = "1;00.17;CRAWL50M;Roskilde";
        String swimTimeTræning = "1;00.20;CRAWL50M";
        ArrayList<SwimTime>testList = new ArrayList<>();

        String linje1 = swimTimeStævne;
        String[] attributes = linje1.split(";");
          SwimTime swimTime = new SwimTime(
                        Boolean.parseBoolean(attributes[0]),
                        Integer.parseInt(attributes[1]),
                        Double.parseDouble(attributes[2]),
                        Events.valueOf(attributes[3]),

                )
                testList.add(swimTime);
    }*/

}