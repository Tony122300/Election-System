
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class ElectionTable<G> implements Serializable {
    ElectionNode<G>[]  electionTable;
    // set a constant
    Double constFactor = 2.5;
    public ElectionTable(int size) { electionTable=(ElectionNode<G>[])new ElectionNode [size];
    }

    //multiplication and rounding method / when its int use this hash function automatically (to accomplish the hash table)
    public int hashFunction(int key){
        return (int) Math.floor((constFactor*key - Math.floor(constFactor*key)) * Math.pow(2,electionTable.length));
    }

    //Object use this hash function automatically
    public int hashFunction(String key){
        return Math.abs(key.hashCode())%electionTable.length;
    }

    //Store in int type key
    public void add(G item, int key){ //it adds methods using the int key
        int chain=hashFunction(key);
        ElectionNode x=new ElectionNode(item);
        x.next=electionTable[chain];
        electionTable[chain]=x;
    }

    public void add(G item,String key){ //this adds  method by using the hashcode
        int chain=hashFunction(key);
        if (electionTable[chain] != null) {
            System.out.println("input repeated key！！！");
            return;
        }
        ElectionNode x=new ElectionNode(item);
        System.out.println("-----------add-------------- " + chain);
        x.next=electionTable[chain];
        electionTable[chain]=x;
    }

    public void displayHashTable(){
        System.out.println("-----------Hash Table--------------");
        int i = 0;
        for(ElectionNode temp : electionTable) {
            while(temp!=null) {
                i++;
                temp.showMessage(i);
                temp=temp.next;
            }
        }
        System.out.println("-----------Hash Table--------------");
    }

    public void deleteElection(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input Eelete ElectionType:  ");
        String electionType = input.nextLine();
        for (int i = 0; i < electionTable.length; i++) {
            //System.out.println("i:  " + i + electionType);
            if (electionTable[i] != null) {
                //System.out.println("Delete:  " + electionTable[i].getElectionType());
                if (Objects.equals(electionTable[i].getElectionType(), electionType)) {
                    electionTable[i] = null;
                }
            }
        }
        System.out.println("-----------Eelete Hash Table success!!--------------");
    }
    //bubble sort
    public void sortTable(){
        for (int i = 1; i < electionTable.length; i++) {
            // setting a flag if true no changes are made。
            boolean flag = true;
            for (int j = 0; j < electionTable.length - i; j++) {
                int num1 = 0,num2= 0;
                if (electionTable[j] != null) {
                    num1 = electionTable[j].getNumOfWinners();
                }
                if (electionTable[j + 1] != null) {
                    num2 = electionTable[j + 1].getNumOfWinners();
                }
                //System.out.println("ii " + i + " j" + j + " num1 " + num1 + " ii " + num2);
                if (num1 > num2) {
                    ElectionNode tmp = electionTable[j];
                    electionTable[j] = electionTable[j + 1];
                    electionTable[j + 1] = tmp;
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
        if (electionTable[chain] !=null) {
            electionTable[chain].showMessage(-1);
        } else {
            System.out.println("not found，Information incorrect！！！");
        }

    }

    public void searchInfo(int key){
        System.out.println("--------searchInfo---------");
        int chain=hashFunction(key);
        if (electionTable[chain] !=null) {
            electionTable[chain].showMessage(-1);
        } else {
            System.out.println("not found，Information incorrect！！！");
        }

    }

    public void editElection(){
        Scanner input = new Scanner(System.in);
        System.out.println("-----------Start Edit Hash Table !!--------------");

        System.out.println("Input Edit Politician name:");
        String location = input.nextLine();
        ElectionTable.ElectionNode ElectionNode = searchElection(location);
        if (ElectionNode == null) {
            return;
        }
        System.out.println("Edit electionType , Skip to next paragraph: ");
        String electionType = input.nextLine();
        System.out.println("Edit Election location, Skip to next paragraph: ");
        String newLocation = input.nextLine();
        System.out.println("Edit Election year, Skip to next paragraph: ");
        long year = input.nextInt();
        System.out.println("Edit Election numOfWinners, Skip to next paragraph: ");
        int numOfWinners = input.nextInt();
        System.out.println("info electionType: " + electionType + " location " + location + " year " + year + "numOfWinners" + numOfWinners);
        if (!Objects.equals(electionType, "")) {
            ElectionNode.setLocation(electionType);
        }
        if (!Objects.equals(electionType, "")) {
            ElectionNode.getYear();
        }
        if (!Objects.equals(newLocation, "")) {
            ElectionNode.setLocation(newLocation);
        }
        if(!Objects.equals(numOfWinners, "")){
            ElectionNode.setNumOfWinners();
            resetTableIndex(location,newLocation);
        }
    }

    public void resetTableIndex(String location, String newLocation){
        int chain =hashFunction(location);
        int newChain =hashFunction(newLocation);
        electionTable[newChain] = electionTable[chain];
        electionTable[chain] = null;

    }

    public ElectionTable.ElectionNode searchElection(String key){
        //System.out.println("--------searchPolitician--------- " + key);
        int chain=hashFunction(key);
        if (electionTable[chain] !=null) {
            System.out.println("found Election！！！");
            return electionTable[chain];
        } else {
            System.out.println("not found Election！！！");
            return null;
        }
    }
    private int ElectionMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Election Menu");
        System.out.println("  1) Show ElectionTable");
        System.out.println("  2) Search ElectionTable");
        System.out.println("  3) Sort ElectionTable");
        System.out.println("  4) Delete Election");
        System.out.println("  5) Edit Election");
        System.out.println("  6) back to Main Menu");
        System.out.println("  7) exit system");
        int option = input.nextInt();
        return option;
    }

    public void runMenu(MenuSystem app) {
        Scanner input = new Scanner(System.in);
        int option = ElectionMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    displayHashTable();
                    break;
                case 2:
                    System.out.println("input election type:");
                    String name = input.nextLine();
                    System.out.println("name:" + name);
                    searchInfo(name);
                    break;
                case 3:
                    sortTable();
                    break;
                case 4:
                    deleteElection();
                    break;
                case 5:
                    editElection();
                    break;
                case 6:
                    app.runMenu();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;

            }
            System.out.println("\nPress continue");
            input.nextLine();
            option = ElectionMenu();
        }
        System.out.println("Exiting");
        System.exit(0);
    }

    class ElectionNode<G> implements Serializable {
        ElectionNode next;
        Election g;
        public ElectionNode(G item) {
            g = (Election)item;
        }
        public void showMessage(int i){
            g.showMessage(i);
        }

        public int setNumOfWinners() {
            return g.getNumOfWinners();
        }
        public int getNumOfWinners() {
            return g.getNumOfWinners();
        }
        public  long getYear(){
           return g.getYear();
        }
        public void setLocation(String newLocation){
            g.setLocation(newLocation);
        }

        public String getElectionType() {
            return g.getElectionType();
        }
    }

}