import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class PoliticianTable<R> implements Serializable {
    PoliticianNode<R>[] politicianTable;
    Double constFactor = 2.5;
    public PoliticianTable(int size) {
        politicianTable=(PoliticianNode<R>[])new PoliticianNode [size];
    }

    //multiplication and rounding method / when its int use this hash function automatically (to accomplish the hash table)
    public int hashFunction(int key){
        return (int) Math.floor((constFactor*key - Math.floor(constFactor*key)) * Math.pow(2,politicianTable.length));
    }

    //Object use this hash function automatically
    public int hashFunction(String key){
        return Math.abs(key.hashCode())%politicianTable.length;
    }

    //Store in int type key
    public void add(R item, int key){ //it adds methods using the int key
        int chain=hashFunction(key);
        PoliticianNode x=new PoliticianNode(item);
        x.next=politicianTable[chain];
        politicianTable[chain]=x;
    }

    public void add(R item,String key){ //this adds  method by using the hashcode.
        int chain=hashFunction(key);
        PoliticianNode x=new PoliticianNode(item);
        x.next=politicianTable[chain];
        politicianTable[chain]=x;
    }

    public void displayHashTable(){
        System.out.println("-----------Politician Table--------------");
        int i = 0;
        for(PoliticianNode temp : politicianTable) {
            while(temp!=null) {
                temp.showMessage(i);
                i++;
                temp=temp.next;
            }
        }
        System.out.println("-----------Politician Table--------------");
    }

    public Politician getPoliticianByKey(String key) {
        int chain = hashFunction(key);
        if (politicianTable[chain] != null) {
            return politicianTable[chain].r;
        } else {
            System.out.println("not found，Information incorrect！！！");
            return null;
        }
    }
    public void deletePolitician(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input Eelete Politician Name:  ");
        String fullName = input.nextLine();
        for (int i = 0; i < politicianTable.length; i++) {
            //System.out.println("i:  " + i + electionType);
            if (politicianTable[i] != null) {
                //System.out.println("Eelete:  " + electionTable[i].getElectionType());
                if (Objects.equals(politicianTable[i].getFullName(), fullName)) {
                    politicianTable[i] = null;
                }
            }
        }
        System.out.println("-----------Eelete Hash Table success!!--------------");
    }

    public void sortTable(){
        for (int i = 1; i < politicianTable.length; i++) {
            // setting a flag if true no changes are made。
            boolean flag = true;
            for (int j = 0; j < politicianTable.length - i; j++) {
                int num1 = 0,num2= 0;
                if (politicianTable[j] != null) {
                    num1 = politicianTable[j].getAge();
                }
                if (politicianTable[j + 1] != null) {
                    num2 = politicianTable[j + 1].getAge();
                }
                //System.out.println("ii " + i + " j" + j + " num1 " + num1 + " ii " + num2);
                if (num1 > num2) {
                    PoliticianTable.PoliticianNode tmp = politicianTable[j];
                    politicianTable[j] = politicianTable[j + 1];
                    politicianTable[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println("-----------Sort Hash Table success!!--------------");
    }
    public void editPolitician(){
        Scanner input = new Scanner(System.in);
        System.out.println("-----------Start Edit Hash Table !!--------------");

        System.out.println("Input Edit Politician name:");
        String fullName = input.nextLine();
        PoliticianNode PoliticianNode = searchPolitician(fullName);
        if (PoliticianNode == null) {
            return;
        }
        System.out.println("Edit Politician name, Skip to next paragraph: ");
        String fixName = input.nextLine();
        System.out.println("Edit Politician politicalParty, Skip to next paragraph: ");
        String politicalParty = input.nextLine();
        System.out.println("Edit Politician homeCountry, Skip to next paragraph: ");
        String homeCountry = input.nextLine();
        System.out.println("info fixName: " + fixName + " politicalParty " + politicalParty + " homeCountry " + homeCountry);
        if (!Objects.equals(politicalParty, "")) {
            PoliticianNode.setPoliticalParty(politicalParty);
        }
        if (!Objects.equals(homeCountry, "")) {
            PoliticianNode.setHomeCountry(homeCountry);
        }
        if (!Objects.equals(fixName, "")) {
            PoliticianNode.setFullName(fixName);
            resetTableIndex(fullName,fixName);
        }
    }

    public void resetTableIndex(String fullName, String fixName){
        int chain =hashFunction(fullName);
        int newChain =hashFunction(fixName);
        politicianTable[newChain] = politicianTable[chain];
        politicianTable[chain] = null;

    }

    public PoliticianNode searchPolitician(String key){
        //System.out.println("--------searchPolitician--------- " + key);
        int chain=hashFunction(key);
        if (politicianTable[chain] !=null) {
            System.out.println("found Politician！！！");
            return politicianTable[chain];
        } else {
            System.out.println("not found Politician！！！");
            return null;
        }
    }

    public void searchInfo(String key){
        System.out.println("--------searchInfo--------- " + key);
        int chain=hashFunction(key);
        if (politicianTable[chain] !=null) {
            politicianTable[chain].showMessage(-1);
        } else {
            System.out.println("not found，Information incorrect！！！");
        }

    }

    public void searchInfo(int key){
        System.out.println("--------searchInfo---------");
        int chain=hashFunction(key);
        if (politicianTable[chain] !=null) {
            politicianTable[chain].showMessage(-1);
        } else {
            System.out.println("not found，Information incorrect！！！");
        }

    }



    private int PoliticianMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("PoliticianTable Menu");
        System.out.println("  1) Show PoliticianTable");
        System.out.println("  2) Search PoliticianTable");
        System.out.println("  3) Sort PoliticianTable");
        System.out.println("  4) Delete Politician");
        System.out.println("  5) Edit Politician");
        System.out.println("  6) back to Main Menu");
        System.out.println("  7) exit system");
        int option = input.nextInt();
        return option;
    }

    public void runMenu(MenuSystem app) {
        Scanner input = new Scanner(System.in);
        int option = PoliticianMenu();
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
                    deletePolitician();
                    break;
                case 5:
                    editPolitician();
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
            option = PoliticianMenu();
        }
        System.out.println("Exiting");
        System.exit(0);
    }

    class PoliticianNode<R> implements Serializable {
        PoliticianNode next;
        Politician r;
        public PoliticianNode(R item) {
            r = (Politician)item;
        }
        public void showMessage(int i){
            r.showMessage(i);
        }

        public String getFullName() {
            return r.getFullName();
        }

        public void setFullName(String fixName) {
            r.setFullName(fixName);
        }

        public int getAge() {
           return (int) r.getAge();
        }

        public void setPoliticalParty(String politicalParty) {
            r.setPoliticalParty(politicalParty);
        }

        public void setHomeCountry(String homeCountry) {
            r.setHomeCountry(homeCountry);
        }
    }

}