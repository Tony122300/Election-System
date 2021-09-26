import java.io.Serializable;
import java.util.Objects;

public class Politician implements Serializable {
    private String fullName;
    private long age;
    private String politicalParty;
    private String partyIndependence;
    private String homeCountry;

    public Politician(String fullName, int age, String politicalParty,String partyIndependence, String homeCountry){
        this.fullName = fullName;
        this.age = age;
        this.politicalParty = politicalParty;
        this.partyIndependence = partyIndependence;
        this.homeCountry = homeCountry;
    }


    public String getFullName(){

        return fullName;
    }

    public long getAge(){
        return age;
    }

    public String getPoliticalParty(){
        return politicalParty;
    }

    public String getPartyIndependence() {
        return partyIndependence;
    }

    public String getHomeCountry(){
        return politicalParty;
    }

    public void setFullName(String fullName){
        int maxLength = 40;
        if(fullName.length() >= maxLength){
            fullName = fullName.substring(0, maxLength);
        }
        this.fullName = fullName;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setPoliticalParty(String politicalParty){
        this.politicalParty = politicalParty;
    }

    public void setPartyIndependence(String partyIndependence) {
        if((politicalParty.equals("Independent")) || (politicalParty.equals("independent"))){
            this.politicalParty = "Independent";
        }else if((politicalParty.equals("Not Independent")) || (politicalParty.equals("not independent")) || (politicalParty.equals("not Independent")) || (politicalParty.equals("Not independent"))){
            this.politicalParty = "Not Independent";
        }
        this.partyIndependence = partyIndependence;
    }

    public void setHomeCountry(String homeCountry){
        this.homeCountry = homeCountry;
    }

    @Override
    public String toString() {
        return "Politician{" +
                "name='" + fullName + '\'' +
                ", age=" + age +
                ", politicalParty='" + politicalParty + '\'' +
                ", partyIndependence='" + partyIndependence + '\'' +
                ", homeCountry='" + homeCountry + '\'' +
                '}';
    }


    public boolean equals(Politician o) {
        if (this == o) return true;
        if (o == null)
            return false;
        Politician politician = (Politician) o;
        return fullName == politician.fullName &&
                age == politician.age &&
                politicalParty == politician.politicalParty &&
                homeCountry == politician.homeCountry &&
                partyIndependence == politician.partyIndependence &&
                Objects.equals(fullName, politician.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, age, politicalParty,partyIndependence, homeCountry);
    }

    public void showMessage(int i) {
        if (i != -1) {
            System.out.println("Politician" + i);
        }
        System.out.println("<name：" + this.fullName);
        System.out.println("age：" + this.age);
        System.out.println("politicalParty：" + this.politicalParty);
        System.out.println("partyIndependence：" + this.partyIndependence);
        System.out.println("homeCountry：" + this.homeCountry + ">");
    }


}
