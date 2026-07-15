import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Pokedex {
    private ArrayList<Pokemon> pokemons;

    public Pokedex() {
        this.pokemons = new ArrayList<Pokemon>();
    }

    public void loadData(){
        Random rand = new Random();

        try {
            Scanner file = new Scanner(new File("pokedex.txt"));
            while (file.hasNext()){
                String region = file.next();
                String name = file.next();
                String type = file.next();
                int health = file.nextInt();
                double attackDamage = file.nextDouble();
                int level = rand.nextInt(10) + 1;;
                Pokemon pokemon = new Pokemon(region,name,type,level,health,attackDamage);
                pokemons.add(pokemon);

            }file.close();
            System.out.println("File loaded successfully");
        } catch (Exception e) {
            System.out.println("File not found.");
        }
    }
    public Pokemon getRandomPokemon() {
        if (pokemons.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(pokemons.size());
        return pokemons.get(index);
    }




}