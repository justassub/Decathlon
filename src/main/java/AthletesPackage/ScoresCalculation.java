package AthletesPackage;

import java.util.*;

public class ScoresCalculation {
    private List<Athletes> athletesList;
    private List<Athletes> athletesListToReturn = new ArrayList<>();

    public ScoresCalculation(List<Athletes> athletesList) {
        this.athletesList = athletesList;
    }


    //prepares data for every Athlete.
    public void prepareDataForCalculationsAndCalculateWinners() {
        for (Athletes athlete : athletesList) {
            List<String> athleteResults = Arrays.asList(athlete.getAllResults().split(";"));
            List<Double> listToCalculateScores = new ArrayList<>();
            //Converts all data to double - for calculations.Except very last index - it has strange format .
            for (int i = 0; i < athleteResults.size() - 1; i++) {
                listToCalculateScores.add(Double.parseDouble(athleteResults.get(i)));
            }
            //last parameter is : mm:ss:SSS format , so we need convert it to seconds.
            String running1500Time = athleteResults.get(athleteResults.size() - 1);
            running1500Time = running1500Time.replace(".", ":");
            String[] stringTimeFormatConvertedIntoIntArray = running1500Time.split(":");
            double totalSeconds = (Integer.parseInt(stringTimeFormatConvertedIntoIntArray[0])) * 60 + (Integer.parseInt(stringTimeFormatConvertedIntoIntArray[1])) + (Double.parseDouble(stringTimeFormatConvertedIntoIntArray[2])) / 100;
            listToCalculateScores.add(totalSeconds);
            calculateScores(athlete, listToCalculateScores);
            Athletes newAthlete = new Athletes(athlete.getFullName(), athlete.getAllResults(), athlete.getTotalScore(), 0);
            athletesListToReturn.add(newAthlete);

        }
        sortAthletesListByScores();
        setTakenPlace();

    }


    //calculates scores
    private void calculateScores(Athletes athlete, List<Double> listToCalculateScores) {

        double running100MetersScore = runningCalculator(listToCalculateScores.get(0), 25.4347, 18, 1.81);
        double longJumpDistanceScore = fieldCalculator(listToCalculateScores.get(1) * 100, 0.14354, 220, 1.4);
        double shotPutDistanceScore = fieldCalculator(listToCalculateScores.get(2), 51.39, 1.5, 1.05);
        double highJumpDistanceScore = fieldCalculator(listToCalculateScores.get(3) * 100, 0.8465, 75, 1.42);
        double running400MetersTimeScore = runningCalculator(listToCalculateScores.get(4), 1.53775, 82, 1.81);
        double running110hurdlesTimeScore = runningCalculator(listToCalculateScores.get(5), 5.74352, 28.5, 1.92);
        double discusThrowDistanceScore = fieldCalculator(listToCalculateScores.get(6), 12.91, 4, 1.1);
        double poleVaultDistanceScore = fieldCalculator(listToCalculateScores.get(7) * 100, 0.2797, 100, 1.35);
        double javelinThrowDistanceScore = fieldCalculator(listToCalculateScores.get(8), 10.14, 7, 1.08);
        double running1500metersTimeScore = runningCalculator(listToCalculateScores.get(9), 0.03768, 480, 1.85);


        double totalScores = running100MetersScore + longJumpDistanceScore + shotPutDistanceScore + highJumpDistanceScore + running400MetersTimeScore + running110hurdlesTimeScore + discusThrowDistanceScore + poleVaultDistanceScore + javelinThrowDistanceScore + running1500metersTimeScore;
        //Total scores must be Integer.
        int totalScoresRounded = (int) Math.round(totalScores);
        athlete.setTotalScore(totalScoresRounded);


    }

    //track events (faster time produces a better score)
    private double runningCalculator(double athletesResult, double aParameter, double bParameter, double cParameter) {
        return (aParameter * Math.pow((bParameter - athletesResult), cParameter));
    }

    //field events (greater distance or height produces a better score)
    private double fieldCalculator(double athletesResult, double aParameter, double bParameter, double cParameter) {
        System.out.println();
        ;
        return (aParameter * Math.pow((athletesResult - bParameter), cParameter));
    }


    private void sortAthletesListByScores() {

        Collections.sort(this.athletesListToReturn, new Comparator<Athletes>() {
            @Override
            public int compare(Athletes athlete1, Athletes athlete2) {
                if (athlete1.getTotalScore() > athlete2.getTotalScore()) {
                    return -1;
                } else if (athlete1.getTotalScore() < athlete2.getTotalScore()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        //In Java 8 , we should use this code :
//            int[] scorePoints = {0};
//        int[] no = {0};
//        int[] takenPlace = {0};
//        List<AthletesPackage.AthletesPackage> listToSortByScores = listToSort.stream()
//                .sorted((athlete1,athlete2)->athlete2.getTotalScore()-athlete1.getTotalScore())
//                .map(p->{
//                    ++no[0];
//                    if (scorePoints[0]!=p.getTotalScore()){
//                        takenPlace[0]= no[0];
//                    }
//                    return new AthletesPackage.AthletesPackage(p.getFullName(),p.getAllResults(),scorePoints[0]=p.getTotalScore(),takenPlace[0]);
//                })
//                .collect(Collectors.toList());


    }

    //Calculates AthletesPackage.AthletesPackage place taken.
    private void setTakenPlace() {
        List<Athletes> athletesListWithPlaces = new ArrayList<>();
        int[] scorePoints = {0};
        int[] takenPlace = {0};
        int[] number = {0};


        for (Athletes athletes : this.athletesListToReturn) {
            ++number[0];
            //in case of same score- they share same place.
            if (scorePoints[0] != athletes.getTotalScore()) {
                scorePoints[0] = athletes.getTotalScore();
                takenPlace[0] = number[0];
            }
            athletes.setTakenPlace(takenPlace[0]);
            athletesListWithPlaces.add(athletes);

        }
        this.athletesListToReturn = athletesListWithPlaces;

    }


    public List<Athletes> getAthletesListToReturn() {
        return this.athletesListToReturn;
    }


}
