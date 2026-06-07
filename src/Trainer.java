import java.util.Scanner;

public class Trainer {
private String name;
private Pokemon[] pokemonTeam;
private String[] badges;
private boolean pokemonChampion;
private Pokemon partner;
// Constructor with name
public Trainer (String newName){
    this.name = newName;
    this.pokemonTeam = new Pokemon[5];
    this.badges = new String[4];
    this.pokemonChampion = false;
    this.partner = null;

}
// Constructor with all attributes.
public Trainer(String newName, Pokemon[] newPokemonTeam, String[] newBadges, boolean newPokemonChampion, Pokemon newPartner){
    this.name = newName;
    this.pokemonTeam = newPokemonTeam;
    this.badges = newBadges;
    this.pokemonChampion = newPokemonChampion;
    this.partner = newPartner;

}
// Getters and setters for all attributes


    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Pokemon[] getPokemonTeam() {return pokemonTeam;}

    public void setPokemonTeam(Pokemon[] pokemonTeam) {this.pokemonTeam = pokemonTeam;}

    public String[] getBadges() {return badges;}

    public void setBadges(String[] badges) {this.badges = badges;}

    public boolean isPokemonChampion() {return pokemonChampion;}

    public void setPokemonChampion(boolean pokemonChampion) {
    this.pokemonChampion = pokemonChampion;
    }

    public Pokemon getPartner() {return partner;}

    public void setPartner(Pokemon partner) {this.partner = partner;}

    // Methods
    public void addPokemon(Pokemon p){
        for (int i = 0; i < pokemonTeam.length; i++){
            if (pokemonTeam[i] == null) {
                pokemonTeam[i] = p;
                System.out.println("Pokemon added.");
                return;
            }
        }
        System.out.println("Team is full.");
    }

public void removePokemon(String name){
    for (int i =0; i < pokemonTeam.length; i++){
        if (pokemonTeam[i] != null && pokemonTeam[i].getName().equals(name)){
            pokemonTeam[i] = null;
            System.out.println("Pokemon removed.");
            return;
        }
    }
    System.out.println("Pokemon is not in your team.");

}
public void trainPokemon(){
    for (int i = 0 ; i < pokemonTeam.length; i++){
        if (pokemonTeam[i] != null) {
            pokemonTeam[i].levelUp();
        }
    }
}

    public void getDetails(){
    System.out.println("==== Trainer Info ====");
        System.out.println("Name: " + name);
        System.out.println("Pokemon Team:");
        boolean hasTeam = false;
        for (Pokemon p : pokemonTeam) {
            if (p != null) {
                System.out.print(p.getName() + " ");
                hasTeam = true;
            }
        }
        if (!hasTeam) {
            System.out.print("None");
        }
        System.out.println();
        System.out.println("Badges:");
        boolean hasBadges = false;
        for (String b : badges) {
            if (b != null) {
                System.out.print(b + " ");
                hasBadges = true;
            }
        }
        if (!hasBadges) {
            System.out.print("None");
        }
        System.out.println();
        //System.out.println();
        System.out.println("Pokemon Champion: " + pokemonChampion);
        if (partner != null){
            System.out.println("Partner: " + partner.getName());
        }
        System.out.println();
    }
    public void choosePartner(){
        Scanner input = new Scanner(System.in);
        System.out.println("Choose your partner Pokemon:");
        for (Pokemon p : pokemonTeam){
            if (p != null){
                System.out.print(p.getName() + " ");
            }
        }
        System.out.println("Enter name:");
        String choice = input.nextLine();
        for (Pokemon p : pokemonTeam){
            if (p != null && p.getName().equals(choice)){
                partner = p;
                System.out.println("Partner set to " + partner.getName());
                return;
            }
        }
        System.out.println("Pokemon not found in team.");
    }



}
