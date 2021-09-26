
import java.io.Serializable;
import java.util.Objects;

public class
Election implements Serializable {
    private String electionType;
    private String location;
    private long year;
    private int numOfWinners;
    //   private Candidate candidate;

    public Election(String electionType, String location, long year, int numOfWinners/* Candidate candidate*/) {
        this.electionType = electionType;
        this.location = location;
        this.year = year;
        this.numOfWinners = numOfWinners;
        //   this.candidate = candidate;
    }

    public String getElectionType() {
        return electionType;
    }

    public String getLocation() {
        return location;
    }

    public long getYear() {
        return year;
    }

    public int getNumOfWinners() {
        return numOfWinners;
    }

    public void setElectionType() {
        if ((electionType.equals("general")) || (electionType.equals("General"))) {
            this.electionType = "General";
        } else if ((electionType.equals("local")) || (electionType.equals("Local"))) {
            this.electionType = "Local";
        } else if ((electionType.equals("european")) || (electionType.equals("European"))) {
            this.electionType = "European";
        } else if ((electionType.equals("presidential")) || (electionType.equals("Presidential"))) {
            this.electionType = "Presidential";
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setNumOfWinners(int numOfWinners) {
        this.numOfWinners = numOfWinners;
    }

    @Override
    public String toString() {
        return "Election{" +
                "electionType='" + electionType + '\'' +
                ", location='" + location + '\'' +
                ", year=" + year +
                ", numOfWinners=" + numOfWinners +
                '}';
    }


// if this get the constant o gives true if null give false
    public boolean equals(Election o) {
        if (this == o) return true;
        if (o == null)
            return false;
        Election election = (Election) o;
        return electionType == election.electionType &&
                location == election.location &&
                year == election.year &&
                numOfWinners == election.numOfWinners &&
                Objects.equals(electionType, election.electionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(electionType, location, year, numOfWinners);
    }
    /*
        public void showCandidateInfo() {
            this.candidate.showMessage(-1);
        }


     */
    public void showMessage(int i) {
        if (i != -1) {
            System.out.println("Election" + i);
        }
        System.out.println("electionType：" + this.electionType);
        System.out.println("location：" + this.location);
        System.out.println("year：" + this.year);
        System.out.println("numOfWinners：" + this.numOfWinners + ">");
        /*
        showCandidateInfo();
        System.out.println("Election" + this.electionType);

         */
    }
}
