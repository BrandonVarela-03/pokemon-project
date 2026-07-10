import java.util.Random;
import java.util.Scanner;
public class MainPokemon {
    static void main(String[] args) {
        Region kanto = new Region("Kanto", 1, "Warm");
        Pokedex pokedexKanto = new Pokedex();
        pokedexKanto.loadData();

        // Variables for Switch
        Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean running = true;

        while (running) {
            printMenu();
            choice = getInt(input);

            switch (choice) {
                case 1:
                    kanto.describeRegion();
                    break;
                case 2:

                    System.out.println("Would you like to add or remove a trainer to/from the region? ");
                    String option = input.next();
                    if (option.equals("add") || option.equals("Add")) {
                        input.nextLine();
                        System.out.println("Enter trainer Name: ");
                        String name = input.nextLine();
                        if (!onlyString(name)) {
                            System.out.println("Invalid name. Returning to main menu.");
                            break;
                        }
                        if (kanto.trainerDuplicate(name)) {
                            System.out.println("Trainer already exists. Returning to main menu.");
                            break;
                        }
                        Trainer p = new Trainer(name);
                        System.out.println("Enter row: (between 0 - 4)");
                        int row = getInt(input);
                        input.nextLine();
                        System.out.println("Enter column: (between 0 - 4)");
                        int col = getInt(input);
                        if (row < 0 || row >= 5 || col < 0 || col >= 5) {
                            System.out.println("Invalid location. Returning to main menu.");
                            break;
                        }
                        if (kanto.isOccupied(row, col)) {
                            System.out.println("Location already occupied. Returning to main menu.");
                            break;
                        }
                        kanto.addTrainer(p);
                        kanto.addTrainerMap(name, row, col);
                        break;
                    } else if (option.equals("remove") || option.equals("Remove")) {
                        input.nextLine();
                        System.out.println("Enter trainer Name: ");
                        String name = input.nextLine();
                        if (!onlyString(name)) {
                            System.out.println("Invalid name. Returning to main menu.");
                            break;
                        }
                        kanto.removeTrainer(name);
                        kanto.removeTrainerMap(name);
                        break;
                    }
                    System.out.println("invalid input only (add or remove)");
                    break;

                case 3:
                    System.out.println("Would you like to add or remove wild Pokémon to/from the region?");
                    String opt = input.next();
                    if (opt.equals("add") || opt.equals("Add")) {
                        input.nextLine();
                        Pokemon base = pokedexKanto.getRandomPokemon();
                        if (base == null) {
                            System.out.println("No Pokémon available in Pokédex.");
                            break;
                        }
                        Pokemon newPokemon = new Pokemon(base.getName(), base.getType(),
                                base.getLevel(),
                                base.getHealth(),
                                base.getAttackDamage()
                        );
                        kanto.addWildPokemon(newPokemon);
                        break;
                    }
                    if (opt.equals("remove") || opt.equals("Remove")) {
                        System.out.println("Which Pokemon you would like to remove: ");
                        String name = input.next();
                        if (!onlyString(name)) {
                            System.out.println("Invalid name. Returning to main menu.");
                            break;
                        }
                        kanto.removeWildPokemon(name);
                        break;

                    }
                    System.out.println("invalid input only (add or remove)");

                    break;

                case 4:
                    System.out.println("Which Trainer you want to modify? ");
                    String name = input.next();
                    Trainer selected = null;

                    for (int i = 0; i < kanto.getTrainerInRegion().length; i++) {
                        if (kanto.getTrainerInRegion()[i] != null &&
                                kanto.getTrainerInRegion()[i].getName().equals(name)) {
                            selected = kanto.getTrainerInRegion()[i];
                            break;
                        }
                    }
                    if (selected == null) {
                        System.out.println("Trainer not found.");
                        break;
                    }
                    selected.getDetails();
//
                    System.out.println("Would you like to modify name, champ status, partner or continue?");
                    String modify = input.next();
                    if (!onlyString(modify)) {
                        System.out.println("Invalid input. Returning to main menu.");
                        break;
                    }
                    if (modify.equals("name") || modify.equals("Name")) {
                        String oldName = name;
                        System.out.println("insert the new trainer name:");
                        String newName = input.next();
                        if (!onlyString(newName)) {
                            System.out.println("Invalid input. Returning to main menu.");
                            break;
                        }
                        selected.setName(newName);
                        selected.setName(newName);
                        for (int i = 0; i < kanto.getRegionMap().length; i++) {
                            for (int j = 0; j < kanto.getRegionMap()[i].length; j++) {
                                if (kanto.getRegionMap()[i][j] != null &&
                                        kanto.getRegionMap()[i][j].equals(oldName)) {
                                    kanto.getRegionMap()[i][j] = newName;
                                }
                            }
                        }
                        System.out.println("Trainer name update.");
                        break;

                    } else if (modify.equals("champ") || modify.equals("champ status") || modify.equals("Champ") || modify.equals("Champ Status")) {
                        selected.setPokemonChampion(!selected.isPokemonChampion());
                        System.out.println("Champion status updated.");

                    } else if (modify.equals("partner") || modify.equals("Partner")) {
                        boolean hasPokemon = false;
                        System.out.println("Your Pokemon Team: ");
                        for (int i = 0; i < selected.getPokemonTeam().length; i++) {
                            if (selected.getPokemonTeam()[i] != null) {
                                System.out.println(selected.getPokemonTeam()[i].getName());
                                hasPokemon = true;
                            }
                        }
                        if (!hasPokemon) {
                            System.out.println("This trainer has no Pokémon in their team.");
                            break;
                        }
                        System.out.println("Which Pokemon do you want as partner");
                        String pokemonName = input.next();
                        if (!onlyString(pokemonName)) {
                            System.out.println("Invalid input. Returning to main menu.");
                            break;
                        }
                        Pokemon partner = null;

                        for (int i = 0; i < selected.getPokemonTeam().length; i++) {
                            if (selected.getPokemonTeam()[i] != null &&
                                    selected.getPokemonTeam()[i].getName().equals(pokemonName)) {
                                partner = selected.getPokemonTeam()[i];
                                break;
                            }
                        }
                        if (partner == null) {
                            System.out.println("Pokémon not found in your team.");
                        } else {
                            selected.setPartner(partner);
                            System.out.println("Partner assigned.");
                        }
                    } else if (modify.equals("continue") || modify.equals("Continue")) {
                        System.out.println("Returning to the menu.");
                        break;
                    } else {
                        System.out.println("invalid input. back to the menu.");
                    }
                    break;

                case 5:
                    System.out.println("Which Trainer? ");
                    String nameTrainer = input.next();
                    Trainer trainerFound = null;
                    for (int i = 0; i < kanto.getTrainerInRegion().length; i++) {
                        if (kanto.getTrainerInRegion()[i] != null &&
                                kanto.getTrainerInRegion()[i].getName().equals(nameTrainer)) {
                            trainerFound = kanto.getTrainerInRegion()[i];
                            break;
                        }
                    }
                    if (trainerFound == null) {
                        System.out.println("Trainer not found.");
                        break;
                    }
                    System.out.println("Would you like to add or remove Pokémon for Trainer?");
                    String addRemove = input.next();
                    if (!onlyString(addRemove)) {
                        System.out.println("Invalid input. Returning to main menu.");
                        break;
                    }
                    if (addRemove.equals("add") || addRemove.equals("Add")) {
                        boolean hasWild = false;
                        for (int i = 0; i < kanto.getWildPokemonInRegion().length; i++) {
                            if (kanto.getWildPokemonInRegion()[i] != null) {
                                hasWild = true;
                                break;
                            }
                        }
                        if (!hasWild) {
                            System.out.println("There are no wild Pokémon in the region.");
                            break;
                        }
                        Pokemon wild = null;
                        Random randPoke = new Random();
                        while (wild == null) {
                            int index = randPoke.nextInt(kanto.getWildPokemonInRegion().length);
                            if (kanto.getWildPokemonInRegion()[index] != null) {
                                wild = kanto.getWildPokemonInRegion()[index];
                                System.out.println("Wild " + wild.getName() + " appeared!");
                                wild.getDetails();
                                System.out.println("Do you want to catch it with a Poke Ball or Run? (Catch/Run)");
                                String pokeRun = input.next();
                                if (!onlyString(pokeRun)) {
                                    System.out.println("Invalid input. Returning to main menu.");
                                    break;
                                }
                                if (pokeRun.equals("catch") || pokeRun.equals("Catch")) {
                                    wild.catchPoke(wild, trainerFound, kanto);
                                    break;
                                } else if (pokeRun.equals("run") || pokeRun.equals("Run")) {
                                    System.out.println(trainerFound.getName() + " successfully Ran!!");
                                    break;
                                }
                            }
                        }
                    } else if (addRemove.equals("Remove") || addRemove.equals("remove")) {
                        boolean hasPokemon = false;
                        System.out.println("Your Pokemon Team: ");
                        for (int i = 0; i < trainerFound.getPokemonTeam().length; i++) {
                            if (trainerFound.getPokemonTeam()[i] != null) {
                                System.out.println(trainerFound.getPokemonTeam()[i].getName());
                                hasPokemon = true;
                                break;
                            }
                        }
                        if (!hasPokemon) {
                            System.out.println("This trainer has no Pokémon in their team.");
                            break;
                        }
                        System.out.println("Which Pokemon you would like to remove");
                        String remove = input.next();
                        if (!onlyString(remove)) {
                            System.out.println("Invalid input. Returning to main menu.");
                            break;
                        }
                        trainerFound.removePokemon(remove);
                        break;
                    } else {
                        System.out.println("invalid input");
                    }
                    break;
                case 6:
                    System.out.println("Which trainer?");
                    String seeteam = input.next();
                    Trainer select = null;
                    if (!onlyString(seeteam)) {
                        System.out.println("Invalid input. Returning to main menu.");
                        break;
                    }
                    for (int i = 0; i < kanto.getTrainerInRegion().length; i++) {
                        if (kanto.getTrainerInRegion()[i] != null &&
                                kanto.getTrainerInRegion()[i].getName().equals(seeteam)) {
                            select = kanto.getTrainerInRegion()[i];
                            break;
                        }
                    }
                    if (select == null) {
                        System.out.println("Trainer not found.");
                        break;
                    }
                    boolean hasPokemon = false;
                    for (int i = 0; i < select.getPokemonTeam().length; i++) {
                        if (select.getPokemonTeam()[i] != null) {
                            hasPokemon = true;
                            break;
                        }
                    }
                    if (!hasPokemon) {
                        System.out.println("This trainer has no Pokémon in their team.");
                        break;
                    }
                    kanto.trainerTeam(select);
                    System.out.println("You want train one of your Pokemon's? (yes/no)");
                    String yesNo = input.next();
                    if (!onlyString(yesNo)) {
                        System.out.println("Invalid input. Returning to main menu.");
                        break;
                    }
                    if (yesNo.equals("no") || yesNo.equals("No")) {
                        System.out.println("Returning to the menu.");
                        break;
                    }
                    if (yesNo.equals("yes") || yesNo.equals("Yes")) {
                        System.out.println("Which Pokemon?");
                        String pokemonName = input.next();
                        Pokemon selectedPoke = null;
                        for (int i = 0; i < select.getPokemonTeam().length; i++) {
                            if (select.getPokemonTeam()[i] != null &&
                                    select.getPokemonTeam()[i].getName().equalsIgnoreCase(pokemonName)) {
                                selectedPoke = select.getPokemonTeam()[i];
                                break;
                            }
                        }
                        if (selectedPoke == null) {
                            System.out.println("Pokémon not found in team.");
                            break;

                        }
                        selectedPoke.levelUp();
                        break;
                    } else System.out.println("Invalid input.");

                    break;


                case 7:
                    System.out.println("Which trainer?");
                    String openTeam = input.next();
                    Trainer selectTrainer = null;
                    if (!onlyString(openTeam)) {
                        System.out.println("Invalid input. Returning to main menu.");
                        break;
                    }
                    for (int i = 0; i < kanto.getTrainerInRegion().length; i++) {
                        if (kanto.getTrainerInRegion()[i] != null &&
                                kanto.getTrainerInRegion()[i].getName().equals(openTeam)) {
                            selectTrainer = kanto.getTrainerInRegion()[i];
                            break;
                        }
                    }
                    if (selectTrainer == null) {
                        System.out.println("Trainer not found.");
                        break;
                    }
                    kanto.trainerTeam(selectTrainer);
                    System.out.println();
                    System.out.println("Which Pokémon would you like to evolve?");
                    String pokeEvolve = input.next();
                    if (!onlyString(pokeEvolve)) {
                        System.out.println("Invalid input. Returning to main menu.");
                        break;
                    }
                    Pokemon selectedPoke = null;
                    for (int i = 0; i < selectTrainer.getPokemonTeam().length; i++) {
                        if (selectTrainer.getPokemonTeam()[i] != null &&
                                selectTrainer.getPokemonTeam()[i].getName().equalsIgnoreCase(pokeEvolve)) {
                            selectedPoke = selectTrainer.getPokemonTeam()[i];
                            break;
                        }
                    }
                    if (selectedPoke == null) {
                        System.out.println("Pokémon not found in team.");
                        break;
                    }
                    System.out.println(selectedPoke.getName() + " is ready to evolve!!");
                    selectedPoke.evolve();

                    break;

                case 8:
                    System.out.println("Would you like to view the map or move a trainer? (view/move)");
                    String mapMove = input.next();
                    if (!onlyString(mapMove)) {
                        System.out.println("Invalid input. Returning to main menu.");
                        break;
                    }
                    else if (mapMove.equals("view") || mapMove.equals("View")) {
                        System.out.println("Displaying map:");
                        kanto.printMap();
                        break;
                    }
                    if (mapMove.equals("move") || mapMove.equals("Move")) {
                        System.out.println("Which trainer would you like to move?");
                        String trainerName = input.next();
                        if (!onlyString(trainerName)) {
                            System.out.println("Invalid input. Returning to main menu.");
                            break;
                        }
                        boolean exists = false;
                        for (int i = 0; i < kanto.getTrainerInRegion().length; i++) {
                            if (kanto.getTrainerInRegion()[i] != null &&
                                    kanto.getTrainerInRegion()[i].getName().equalsIgnoreCase(trainerName)) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            System.out.println("Trainer not found. Returning to main menu.");
                            break;
                        }

                        System.out.println("Enter row: (between 0 - 4)");
                        int row = getInt(input);
                        input.nextLine();
                        System.out.println("Enter column: (between 0 - 4)");
                        int col = getInt(input);
                        if (row < 0 || row >= 5 || col < 0 || col >= 5) {
                            System.out.println("Invalid location. Returning to main menu.");
                            break;
                        }
                        if (kanto.isOccupied(row, col)) {
                            System.out.println("Location already occupied. Returning to main menu.");
                            break;
                        }
                        kanto.moveTrainer(trainerName, row, col);
                        break;


                    } else {
                        System.out.println("Invalid input.");
            }
                    break;

                case 9:
                    System.out.println("Game over!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");


            }


        }

    }
    //Helper methods


    public static void printMenu() {
        System.out.println("|******************************| \n" +
                " |* Welcome to Poke-Miner *| \n" +
                "|******************************|");
        System.out.println("Options: \n" +
                "1. Show Everything in Region \n" +
                "2. Add/Remove Trainer to Region \n" +
                "3. Add/Remove Wild Pokémon to Region \n" +
                "4. Modify Trainer \n" +
                "5. Add/remove Pokémon from Trainer \n" +
                "6. List Pokémon in Trainer/Training \n" +
                "7. Evolve Trainers' Pokémon \n" +
                "8. View/Move on Region Map \n" +
                "9. Exit \n" +
                "|******************************| \n" +
                "Select option: ");


    }

    public static int getInt(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            input.next();
        }
        return input.nextInt();
    }

    public static boolean onlyString(String name) {
        if (name.trim().length() == 0) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
