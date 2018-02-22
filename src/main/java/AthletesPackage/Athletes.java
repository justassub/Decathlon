package AthletesPackage;

public class Athletes {
    private String fullName;
    private String scores;
    private int totalScore;
    private int takenPlace;


    public Athletes(String fullName, String allResults) {
        this.fullName = fullName;
        this.scores = allResults;
        this.totalScore = totalScore;
    }


    public Athletes(String fullName, String allResults, int totalScore, int place) {
        this.fullName = fullName;
        this.scores = allResults;
        this.totalScore = totalScore;
        this.takenPlace = place;
    }


    public Athletes() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAllResults() {
        return scores;
    }

    public void setAllResults(String allResults) {
        this.scores = allResults;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTakenPlace() {
        return takenPlace;
    }

    public void setTakenPlace(int takenPlace) {
        this.takenPlace = takenPlace;
    }
}
