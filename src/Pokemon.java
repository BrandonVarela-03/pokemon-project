import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Pokemon {
    private String region;
    private String name;
    private String type;
    private int level;
    private int health;
    private double attackDamage;

    //Constructor with name and type
    public Pokemon(String newRegion,String newName, String newType) {
        this.region = newRegion;
        this.name = newName;
        this.type = newType;
        this.level = 1;
        this.health = 30;
        this.attackDamage = 10;
    }

    // Constructor with all attributes.
    public Pokemon(String newRegion,String newName, String newType, int newLevel, int newHealth, double newAttackDamage) {
        this.region = newRegion;
        this.name = newName;
        this.type = newType;
        this.level = newLevel;
        this.health = newHealth;
        this.attackDamage = newAttackDamage;
    }

    // getters and setters for all the attributes.
    public String getRegion(){ return region;}

    public void setRegion(String region) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }


    //methods for level speak and get details
    // Increase the level and health
    public void levelUp() {
        level++;
        health += 14;
        attackDamage += 1;
        System.out.println( "Pokemon: " + name + " has increase his level now is level: " +
                level+" Health: "+ health + " Attack Damage: " + attackDamage);
    }

    // Pokemon says his name twice
    public void speak() {
        System.out.println(name + "!" + name + "!");
    }

    // get all the info of the pokemons
    public void getDetails() {
        System.out.println("===== POKEMON INFO =====");
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Level: " + level);
        System.out.println("Health: " + health);
        System.out.println("Attack Damage: " + attackDamage);
    }

    public void evolve() {
        try {
            Scanner scanner = new Scanner(new File("Evos.txt"));
            while (scanner.hasNext()){
                String current = scanner.next();
                String nextName = scanner.next();
                if (current.equals(name)){
                    String newType = scanner.next();
                    int newHealth = scanner.nextInt();
                    int newAttack = scanner.nextInt();
                    int lvlRequired = scanner.nextInt();
                    name = nextName;
                    health = newHealth;
                    attackDamage = newAttack;
                    if (level >= lvlRequired){
                        System.out.println("Your Pokémon has evolved into " + name + "!");
                        scanner.close();
                    }
                    if (level < lvlRequired) {
                        System.out.println(name + " isn't high enough level to evolve yet (needs level " + lvlRequired + ").");
                        scanner.close();
                        return;
                    }
                    scanner.close();
                    return;
                }
            }
            System.out.println("No evolution found.");
            scanner.close();
        } catch (Exception e){
            System.out.println("File not found.");
        }
    }
    public void catchPoke(Pokemon p, Trainer s, Region r){
        Random rand = new Random();
        int catchRate = (int) (100 / (p.getLevel() * 0.5));
        int randomNum = rand.nextInt(101);

        if (randomNum <= catchRate) {
            System.out.println("You caught " + p.getName() + "!");
            System.out.println("Catch success chance was " + catchRate + "%.");
            // Add to trainer team
            for (int i = 0; i < s.getPokemonTeam().length; i++) {
                if (s.getPokemonTeam()[i] == null) {
                    s.getPokemonTeam()[i] = p;
                    System.out.println(p.getName() + " was added to your team!");
                    break;
                }
            }
            // Remove from the willPoke Arr
            for (int i = 0; i < r.getWildPokemonInRegion().length; i++) {
                if (r.getWildPokemonInRegion()[i] != null &&
                        r.getWildPokemonInRegion()[i].getName().equals(p.getName())) {
                    r.getWildPokemonInRegion()[i] = null;
                    break;
                }
            }
            for (int row = 0; row < r.getRegionMap().length; row++) {
                for (int col = 0; col < r.getRegionMap()[row].length; col++) {
                    if (r.getRegionMap()[row][col] != null &&
                            r.getRegionMap()[row][col].equals( p.getName())) {
                        r.getRegionMap()[row][col] = null;
                    }
                }
            }
        }else {
            System.out.println("The Pokemon fled! Catch failed.");
            System.out.println("Catch success chance was " + catchRate + "%.");
        }


    }

}
