
import AthletesPackage.Athletes;
import AthletesPackage.ScoresCalculation;
import org.junit.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import  static org.junit.Assert.*;

public class DecathlonTests {


    //all files we need to read
    List<String> filesToRead = Arrays.asList("src/main/resources/Decathlon_input.txt");
    @Test
    public  void checkIfFileExists(){
        for (String file : filesToRead){
            File fileExists = new File(file);
            assertTrue(fileExists.exists());
        }
    }
    //all files can be readed
    @Test
    public  void checkIfFilesCanBeRead(){
        for (String file : filesToRead){
            File fileExists = new File(file);
            assertTrue(fileExists.canRead());
        }
    }

    @Test
    public void  createCreateFile  () throws IOException {
        File fileExists = new File("src/main/resources/testfile.xml");
        fileExists.createNewFile();
        assertTrue(fileExists.exists());
        fileExists.delete();
        assertTrue(!fileExists.exists());
    }

    @Test
    public  void  sortWinners(){
        List<Athletes> athletesList= new ArrayList<>();
        Athletes athlete1 = new Athletes("Peter","13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6.22.75");
        Athletes athlete2 = new Athletes("Peter sameScore","13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6:22:75");
        Athletes athletes3 = new Athletes("First Place", "13.70;4.90;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6:20:75");

        athletesList.add(athlete1);
        athletesList.add(athlete2);
        athletesList.add(athletes3);
        ScoresCalculation calculateWinners = new ScoresCalculation(athletesList);
        calculateWinners.prepareDataForCalculationsAndCalculateWinners();
        athletesList=calculateWinners.getAthletesListToReturn();
        assertEquals(athletesList.get(0).getFullName(),"First Place");
        assertEquals(athletesList.get(1).getTotalScore(),3499);
        //Both athletes with same score share same place
        assertEquals(athletesList.get(1).getTakenPlace(),2);
        assertEquals(athletesList.get(2).getTakenPlace(),2);


    }


}
