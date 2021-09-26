
import java.util.Scanner;

public class MenuSystem {

    private Scanner input = new Scanner(System.in);
    public static PoliticianTable<Politician> politicianTableList;
    public static ElectionTable<Election> electionTableList;
    public static CandidateTable<Candidate> candidateTableList;

    public static MenuSystem app = new MenuSystem();
    public static void main(String args[]) {
        // MenuSystem app = new MenuSystem();
        load();
        app.runMenu();
    }

    private int mainMenu() {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("  1) Add Election");
        System.out.println("  2) Add Politician");
        System.out.println("  3) addPoliticianToCandidate();");
        System.out.println("  4) Search PoliticianTable");
        System.out.println("  5) Search ElectionTable");
        System.out.println("  6) Search CandidateTable");
        System.out.println("  7) Save");
        int option = input.nextInt();
        return option;
    }

    public void runMenu() {
        int option = mainMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    addElection();
                    break;
                case 2:
                    addPolitician();
                    break;
                case 3:
                    addPoliticianToCandidate();
                    break;
                case 4:
                    politicianTableList.runMenu(app);
                    break;
                case 5:
                    electionTableList.runMenu(app);
                    break;
                case 6:
                    candidateTableList.runMenu(app);
                    break;
                case 7:
                    save();
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;

            }
            System.out.println("\nPress continue");
            input.nextLine();
            option = mainMenu();
        }
        System.out.println("Exiting");
        System.exit(0);
    }
    private Politician addPolitician() {
        System.out.println("Enter Politician name:");
        input.nextLine();
        String fullName = input.nextLine();
        System.out.println("Enter Politician age:");
        int age = input.nextInt();
        input.nextLine();
        System.out.println("Enter Politician politicalParty:");
        String politicalParty = input.nextLine();
        System.out.println("is the party Independent or not independent");
        String partyIndependence = input.nextLine();
        System.out.println("Enter Politician homeCountry:");
        String homeCountry = input.nextLine();
        Politician politician = new Politician(fullName, age, politicalParty, partyIndependence, homeCountry);
        //search and save by name
        politicianTableList.add(politician,fullName);
        return politician;
    }

    private Election addElection(){
        System.out.println(("Pick the type of election from General, Local, European, Presidential"));
        input.nextLine();
        String electionType = input.nextLine();
        System.out.println("Enter the location of the Election");
        String location = input.nextLine();
        System.out.println("Enter the year of the election ");
        long year = input.nextInt();
        input.nextLine();
        System.out.println("Enter the number of winners that can be in this election");
        int numOfWinners = input.nextInt();
        input.nextLine();
        Election election = new Election(electionType, location, year, numOfWinners);
        electionTableList.add(election, electionType);
        return election;
    }

    private void addPoliticianToCandidate(){
        politicianTableList.displayHashTable();
        System.out.println("input the Politician Name:");
        input.nextLine();
        String fullName = input.nextLine();
        Politician politician = politicianTableList.getPoliticianByKey(fullName);
        if (politician != null) {
            Candidate candidate = new Candidate(fullName,politician);
            //search and save by name
            candidateTableList.add(candidate,fullName);
            System.out.println("Add success!!!");
            candidateTableList.displayHashTable();
        } else {
            System.out.println("Add failed!!!");
        }
    }
//    private void addCandidateToElection(){
//        candidateTableList.displayHashTable();
//        System.out.println("input the candidates Name:");
//        input.nextLine();
//        String name = input.nextLine();
//        Candidate candidate = candidateTableList.getCandidateByKey(name);
//        if (candidate != null) {
//            Election election = new Election(name,candidate);
//            //search and save by name
//            electionTableList.add(election,name);
//            System.out.println("Add success!!!");
//            electionTableList.displayHashTable();
//        } else {
//            System.out.println("Add failed!!!");
//        }
//    }

    private void  save() {
        FileUtil.saveObjectByObjectOutput(electionTableList,"F://election.txt");
        FileUtil.saveObjectByObjectOutput(politicianTableList,"F://politician.txt");
        FileUtil.saveObjectByObjectOutput(candidateTableList,"F://candidate.txt");
    }
    private static void  load() {
        electionTableList = (ElectionTable<Election>) FileUtil.getObjectByObjectInput("F://election.txt");
        politicianTableList = (PoliticianTable<Politician>) FileUtil.getObjectByObjectInput("F://politician.txt");
        candidateTableList = (CandidateTable<Candidate>) FileUtil.getObjectByObjectInput("F://candidate.txt");
        if (electionTableList == null) {
            electionTableList = new ElectionTable<Election>(10);
        }
        if (politicianTableList == null) {
            politicianTableList =new PoliticianTable<Politician>(10);
        }
        if (candidateTableList == null) {
            candidateTableList = new CandidateTable<Candidate>(10);
        }
    }
    private void  totalVotes() {

    }

}

