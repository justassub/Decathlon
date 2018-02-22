

import AthletesPackage.*;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class Decathlon {

    public static void main(String[] args) throws Exception {
        //reading files- you can put all files you want to read
        List<String> filesToRead = Arrays.asList("src/main/resources/Decathlon_input.txt");
        //All participants - from all files .
        List<Athletes> athletesList = new ArrayList<>();

        //READS all Data from input source and adds all participans into list;
        for (String singleFile : filesToRead) {
            try (Scanner scanner = new Scanner(new FileReader(singleFile));
            ) {
                while (scanner.hasNext()) {
                    List<String> athleteDataFromSCVFile = Arrays.asList(scanner.nextLine().split(";"));
                    String athleteFullName = athleteDataFromSCVFile.get(0);

                    //We need to save  AthletesPackage.AthletesPackage scores.
                    String athleteResult = "";

                    //In Java8 we should use lambdas .
                    for (int i = 1; i < athleteDataFromSCVFile.size(); i++) {
                        athleteResult = athleteResult + athleteDataFromSCVFile.get(i) + ";";
                    }


                    Athletes newAthlete = new Athletes(athleteFullName, athleteResult);
                    athletesList.add(newAthlete);

                }
                //Close-we do not want lose data or use addition resources;
                scanner.close();


            } catch (IOException e) {
                System.out.println("File was not found");
                e.printStackTrace();
            }
        }
        //try to create file.
        try {
            //Create object to calculate points and taken place.
            ScoresCalculation scoresCalculation = new ScoresCalculation(athletesList);
            scoresCalculation.prepareDataForCalculationsAndCalculateWinners();
            //place to save our data;
            FileOutputStream xmlFile = new FileOutputStream(new File("./src/main/resources/decathlonWinners.xml"));
            FileOutputStream xslFile = new FileOutputStream(new File("./src/main/resources/decathlonWinners.xsl"));
            XMLEncoder encoderXSL = new XMLEncoder(xslFile);
            XMLEncoder encoderXML = new XMLEncoder(xmlFile);


            encoderXSL.writeObject(scoresCalculation.getAthletesListToReturn());
            encoderXML.writeObject(scoresCalculation.getAthletesListToReturn());

            //Close-we do not want lose data or use addition resources;
            encoderXML.close();
            encoderXSL.close();
            xslFile.close();
            xmlFile.close();

        } catch (IOException e) {
            //if file cannot be created
            e.printStackTrace();
        }
    }


}
