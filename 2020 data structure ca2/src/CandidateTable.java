import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class CandidateTable<E> implements Serializable {
    CandidateNode<E>[] candidateTable;
    Double constFactor = 2.5;
    public CandidateTable(int size) {
        candidateTable=(CandidateNode<E>[])new CandidateNode [size];
    }

    //multiplication and rounding method / when its int use this hash function automatically (to accomplish the hash table)
    public int hashFunction(int key){
        return (int) Math.floor((constFactor*key - Math.floor(constFactor*key)) * Math.pow(2,candidateTable.length));
    }

    //Object use this hash function automatically
    public int hashFunction(String key){
        return Math.abs(key.hashCode())%candidateTable.length;
    }

    //Store in int type key
    public void add(E item, int key){ //it adds methods using the int key
        int chain=hashFunction(key);
        CandidateNode x=new CandidateNode(item);
        x.next=candidateTable[chain];
        candidateTable[chain]=x;
    }

    public void add(E item,String key){ //this adds  method by using the hashcode
        int chain=hashFunction(key);
        CandidateNode x=new CandidateNode(item);
        x.next=candidateTable[chain];
        candidateTable[chain]=x;
    }

    public void displayHashTable(){
        System.out.println("-----------Candidates--------------\n");
        int i = 0;
        for(CandidateNode temp : candidateTable) {
            while(temp!=null) {
                temp.showMessage(i);
                i++;
                temp=temp.next;
            }
        }
        System.out.println("-----------Candidates--------------\n");
    }

    public Candidate getCandidateByKey(String key) {
        int chain = hashFunction(key);
        if (candidateTable[chain] != null) {
            return candidateTable[chain].e;
        } else {
            System.out.println("not found，Information incorrect！！！");
            return null;
        }
    }

    public void deleteCandidate(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input Delete candidate name:  ");
        String name = input.nextLine();
        for (int i = 0; i < candidateTable.length; i++) {
            //System.out.println("i:  " + i + electionType);
            if (candidateTable[i] != null) {
                //System.out.println("Delete:  " + electionTable[i].getElectionType());
                if (Objects.equals(candidateTable[i].getName(), name)) {
                    candidateTable[i] = null;
                }
            }
        }
        System.out.println("-----------Eelete Hash Table success!!--------------");
    }

    public void sortTable(){
        for (int i = 1; i < candidateTable.length; i++) {
            boolean flag = true;
            for (int j = 0; j < candidateTable.length - i; j++) {
                int num1 = 0,num2= 0;
                if (candidateTable[j] != null) {
                    num1 = candidateTable[j].getVotes();
                }
                if (candidateTable[j + 1] != null) {
                    num2 = candidateTable[j + 1].getVotes();
                }
                //System.out.println("ii " + i + " j" + j + " num1 " + num1 + " ii " + num2);
                if (num1 > num2) {
                    CandidateTable.CandidateNode tmp = candidateTable[j];
                    candidateTable[j] = candidateTable[j + 1];
                    candidateTable[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println("-----------Sort Hash Table success!!--------------");
    }

    public void searchInfo(String key){
        System.out.println("--------searchInfo---------");
        int chain=hashFunction(key);
        if (candidateTable[chain] !=null) {
            candidateTable[chain].showMessage(-1);
        } else {
            System.out.println("not found，Information incorrect！！！");
        }

    }

    public void searchInfo(int key){
        System.out.println("--------searchInfo---------");
        int chain=hashFunction(key);
        if (candidateTable[chain] !=null) {
            candidateTable[chain].showMessage(-1);
        } else {
            System.out.println("not found，Information incorrect！！！");
        }

    }

    private int CandidateMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("CandidateMenu Menu");
        System.out.println("  1) Show CandidateTable");
        System.out.println("  2) Search CandidateTable");
        System.out.println("  3) Sort CandidateTable");
        System.out.println("  4) Delete Election");
        System.out.println("  5) back to Main Menu");
        System.out.println("  6) exit system");
        int option = input.nextInt();
        return option;
    }

    public void runMenu(MenuSystem app) {
        Scanner input = new Scanner(System.in);
        int option = CandidateMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    displayHashTable();
                    break;
                case 2:
                    System.out.println("input user name:");
                    String name = input.nextLine();
                    searchInfo(name);
                    break;
                case 3:
                    sortTable();
                    break;
                case 4:
                    deleteCandidate();
                    break;
                case 5:
                    app.runMenu();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;

            }
            System.out.println("\nPress continue");
            input.nextLine();
            option = CandidateMenu();
        }
        System.out.println("Exiting");
        System.exit(0);
    }

    class CandidateNode<E> implements Serializable {
        CandidateNode next;
        Candidate e;

        public CandidateNode(E item) {
            e = (Candidate)item;
        }
        public void showMessage(int i){
            e.showMessage(i);
        }
        public String getName(){
            return e.getName();
        }

        public int getVotes(){
            return e.getVotes();
        }
    }

}