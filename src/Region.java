import java.util.Random;
public class Region {
    private String nameRegion;
    private String climate;
    private int difficulty;
    private Trainer[] trainerInRegion;
    private Pokemon[] wildPokemonInRegion;
    private String[][] regionMap;

    // first contrutcor with name diff and climate
    public Region(String newNameRegion, int newDifficulty, String newClimate) {
        this.nameRegion = newNameRegion;
        this.difficulty = newDifficulty;
        this.climate = newClimate;
        this.trainerInRegion = new Trainer[9];
        this.wildPokemonInRegion = new Pokemon[19];
        this.regionMap = new String[5][5];

    }

    // second Constructor with all att.
    public Region(String newNameRegion, int newDifficulty, String newClimate, Trainer[] newTrainerInRegion,
                  Pokemon[] newWildPokemonInRegion, String[][] newRegionMap) {
        this.nameRegion = newNameRegion;
        this.difficulty = newDifficulty;
        this.climate = newClimate;
        this.trainerInRegion = newTrainerInRegion;
        this.wildPokemonInRegion = newWildPokemonInRegion;
        this.regionMap = newRegionMap;

    }

    //Getters and setters
    public String getNameRegion() {return nameRegion;}

    public void setNameRegion(String nameRegion) {this.nameRegion = nameRegion;}

    public String getClimate() {return climate;}

    public void setClimate(String climate) {this.climate = climate;}

    public int getDifficulty() {return difficulty;}

    public void setDifficulty(int difficulty) {this.difficulty = difficulty;}

    public Trainer[] getTrainerInRegion() {return trainerInRegion;}

    public void setTrainerInRegion(Trainer[] trainerInRegion) {this.trainerInRegion = trainerInRegion;}

    public Pokemon[] getWildPokemonInRegion() {return wildPokemonInRegion;}

    public void setWildPokemonInRegion(Pokemon[] wildPokemonInRegion) {this.wildPokemonInRegion = wildPokemonInRegion;}

    public String[][] getRegionMap() {return regionMap;}

    public void setRegionMap(String[][] regionMap) {this.regionMap = regionMap;}


    // Methods
    public void addTrainer(Trainer t) {
        for (int i = 0; i < trainerInRegion.length; i++) {
            if (trainerInRegion[i] == null) {
                trainerInRegion[i] = t;
                System.out.println("Trainer added to the Region!!.");
                return;

            }
        }
        System.out.println("Region is Full!!.");
    }

    public void removeTrainer(String name) {

        for (int i = 0; i < trainerInRegion.length; i++) {
            if (trainerInRegion[i] != null && trainerInRegion[i].getName().equals(name)) {
                trainerInRegion[i] = null;
                System.out.println("Trainer has been remove!!");
                return;
            }
        }
        System.out.println("Trainer is not on the region.");




        }

    // method to add new trainer to the map
    public void addTrainerMap(String name, int newRow , int newCol){
        if (newRow < 0 || newRow >= regionMap.length ||
                newCol < 0 || newCol >= regionMap[0].length) {
            System.out.println("Invalid position");
            return;
        }
        if (regionMap[newRow][newCol] != null) {
            System.out.println("Spot already occupied");
            return;
        }
        regionMap[newRow][newCol] = name;
        System.out.println(name + " added to position (" + newRow + ", " + newCol + ")");

    }
    // method to remove trainer to the map
    public void removeTrainerMap(String name) {
        for (int row = 0; row < regionMap.length; row++) {
            for (int col = 0; col < regionMap[row].length; col++) {
                if (regionMap[row][col] != null && regionMap[row][col].equals(name)) {
                    regionMap[row][col] = null;
                    System.out.println(name + " removed from the map.");
                    return;
                }
            }
        }
        System.out.println("Trainer not found in the map.");
    }
    // if spot is occupied
    public boolean isOccupied(int row, int col) {
        return regionMap[row][col] != null;
    }
    // if name is duplicate
    public boolean trainerDuplicate(String name) {
        for (int i = 0; i < trainerInRegion.length; i++) {
            if (trainerInRegion[i] != null &&
                    trainerInRegion[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void moveTrainer(String name, int newRow, int newCol) {
        if (newRow < 0 || newRow >= regionMap.length ||
                newCol < 0 || newCol >= regionMap[0].length) {
            System.out.println("Invalid position");
            return;
        }
        if (regionMap[newRow][newCol] != null) {
            System.out.println("Spot already occupied");
            return;
        }
        for (int row = 0; row < regionMap.length; row++) {
            for (int col = 0; col < regionMap[row].length; col++) {
                if (regionMap[row][col] != null && regionMap[row][col].equals(name)) {
                    regionMap[newRow][newCol] = regionMap[row][col];
                    regionMap[row][col] = null;
                    System.out.println(name + " moved to position (" + newRow + ", " + newCol + ")");
                    return;
                }
            }
        }
        System.out.println("Trainer not found");
    }

    public void addWildPokemon(Pokemon p) {
        Random random = new Random();

        for (int i = 0; i < wildPokemonInRegion.length; i++) {

            if (wildPokemonInRegion[i] == null) {
                wildPokemonInRegion[i] = p;

                int row = random.nextInt(regionMap.length);
                int col = random.nextInt(regionMap[0].length);
                while (regionMap[row][col] != null) {
                    row = random.nextInt(regionMap.length);
                    col = random.nextInt(regionMap[0].length);
                }
                regionMap[row][col] = p.getName();
                System.out.println("There's a new wild Pokemon called " + p.getName() + " in the region added at (" + row + ", " + col + ")");
                return;
            }
        }
        System.out.println("No space for more wild Pokemon.");
    }

    public void removeWildPokemon(String name) {
        boolean found = false;
        for (int i = 0; i < wildPokemonInRegion.length; i++) {
            if (wildPokemonInRegion[i] != null &&
                    wildPokemonInRegion[i].getName().equals(name)) {
                wildPokemonInRegion[i] = null;
                break;
            }
        }
        for (int row = 0; row < regionMap.length; row++) {
            for (int col = 0; col < regionMap[row].length; col++) {
                if (regionMap[row][col] != null &&
                        regionMap[row][col].equals(name)) {
                    regionMap[row][col] = null;
                    found = true;
                }
            }
        }
        if (!found){
            System.out.println("Pokemon not found.");
        }
        if (found){
            System.out.println("Wild Pokémon " + name + " removed from Region.");
        }
    }


    public Pokemon generateWildPokemon() {
        Random rand = new Random();
        int attempts = 0;
        while (attempts < 100) {
            int index = rand.nextInt(wildPokemonInRegion.length);
            if (wildPokemonInRegion[index] != null) {
                return wildPokemonInRegion[index];
            }
            attempts++;
        }
        return null;
    }

    public void printMap() {
        System.out.println("\n--- Region Map: " + nameRegion + " ---");
        for (int row = 0; row < regionMap.length; row++) {
            // up line
            for (int col = 0; col < regionMap[row].length; col++) {
                System.out.print("+----------");
            }
            System.out.println("+");
            for (int col = 0; col < regionMap[row].length; col++) {
                String value = regionMap[row][col];
                if (value == null) {
                    System.out.print("| --       ");
                } else {
                    // detect if is trainer
                    boolean isTrainer = false;
                    for (int i = 0; i < trainerInRegion.length; i++) {
                        if (trainerInRegion[i] != null &&
                                trainerInRegion[i].getName().equals(value)) {
                            isTrainer = true;
                            break;
                        }
                    }
                    // make it short to 8 characters
                    if (value.length() > 8) {
                        value = value.substring(0, 8);
                    }
                    // print and full empty spaces
                    System.out.print("| " + value);
                    int spaces = 8 - value.length();
                    for (int s = 0; s < spaces; s++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("|");
        }
        // final line
        for (int col = 0; col < regionMap[0].length; col++) {
            System.out.print("+----------");
        }
        System.out.println("+\n");
    }

    public void getDetails() {
        System.out.println("Name of the Region: " + nameRegion);
        System.out.println("Type of climate: " + climate);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Trainers in Region: ");
        if (trainerInRegion != null) {
            for (int i = 0; i < trainerInRegion.length; i++) {
                if (trainerInRegion[i] != null) {
                    System.out.println(trainerInRegion[i].getName());
                }
            }
        }
        System.out.println("Wild Pokemon in the Region: ");
        if (wildPokemonInRegion != null) {
            for (int j = 0; j < wildPokemonInRegion.length; j++) {
                if (wildPokemonInRegion[j] != null) {
                    System.out.println(wildPokemonInRegion[j].getName());
                }
            }
        }
    }

    //I am a beginner programmer and you are an expert Java programmer. Create a Java method called describeRegion()
    // for a class named Region. The method should print all the details of the region including nameRegion (String),
    // climate (String), difficulty (int), trainers in the region (Trainer[] array), and wild Pokémon in the region
    // (Pokemon[] array). The method must be simple and easy to understand at an Intro to Computer Science level.
    // Do not use advanced Java concepts .and conditionals (if statements), and print the information in a clean and readable format.
    // Preconditions:
    // The Region object is already created, the arrays trainerInRegion and wildPokemonInRegion may contain null values,
    // and Trainer and Pokemon classes have a getName() method. Postconditions: The method prints all region details to the console,
    // only valid (non-null) trainers and Pokémon are printed, and the output is clear and organized.
    // Level: Beginner (Intro to Computer Science). Now generate the Java method describeRegion().
    public void describeRegion() {

        System.out.println("=== Region Details ===");

        System.out.println("Name: " + nameRegion);
        System.out.println("Climate: " + climate);
        System.out.println("Difficulty: " + difficulty);

        // Trainers
        System.out.println("\nTrainers in Region:");
        boolean hasTrainers = false;

        for (int i = 0; i < trainerInRegion.length; i++) {
            if (trainerInRegion[i] != null) {
                System.out.println("- " + trainerInRegion[i].getName());
                hasTrainers = true;
            }
        }

        if (!hasTrainers) {
            System.out.println("No trainers in this region.");
        }

        // Wild Pokemon
        System.out.println("\nWild Pokemon in Region:");
        boolean hasPokemon = false;

        for (int i = 0; i < wildPokemonInRegion.length; i++) {
            if (wildPokemonInRegion[i] != null) {
                System.out.println("- " + wildPokemonInRegion[i].getName());
                hasPokemon = true;
            }
        }

        if (!hasPokemon) {
            System.out.println("No wild Pokemon in this region.");
        }
    }
    public void trainerTeam(Trainer t){
        System.out.println(t.getName() +"'s Pokemon Team:");
        for (int i = 0 ; i < t.getPokemonTeam().length; i ++){
            if (t.getPokemonTeam()[i] != null){
                Pokemon p = t.getPokemonTeam()[i];
                System.out.println((i + 1) + ". " +
                        p.getName() +
                        ", Type: " + p.getType() +
                        ", Level: " + p.getLevel() +
                        ", Health: " + p.getHealth()
                );
            }
        }
    }
}

