import java.io.Serializable;

public class Candidate implements Serializable {
    // private int totalVotes; add votes to the end of each candidate;
    private String name;
    private int votes = 0;//initial value 0
    private Politician politician;

    public Candidate(String name,Politician politician){
        this.name = name;
        this.politician = politician;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return this.votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }

    public void showPoliticianInfo() {
        this.politician.showMessage(-1);
    }

    public void showMessage(int i) {
        if (i != -1) {
            System.out.println("Candidate" + i);
        }
        showPoliticianInfo();
        System.out.println("Candidate Votes：" + this.votes);
    }
}
