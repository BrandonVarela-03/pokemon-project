import java.io.File;
import java.util.Scanner;
import java.util.Random;
public class Pokedex {
    private Pokemon[] pokemons;
    private int count;

    public Pokedex() {
        this.pokemons = new Pokemon[50];
        this.count = 0;

    }

    public void loadData(){
        Random rand = new Random();

        try {
            Scanner file = new Scanner(new File("pokedex.txt"));
            while (file.hasNext()){
                String name = file.next();
                String type = file.next();
                int health = file.nextInt();
                double attackDamage = file.nextDouble();
                int level = rand.nextInt(10) + 1;;
                Pokemon pokemon = new Pokemon(name,type,level,health,attackDamage);

                if (count < pokemons.length){
                    pokemons[count] = pokemon;
                    count++;
                }

            }file.close();
            System.out.println("File loaded successfully");
        } catch (Exception e) {
            System.out.println("File not found.");
        }
    }
    public Pokemon getRandomPokemon() {
        if (count == 0) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(count); // número entre 0 y count-1
        return pokemons[index];
    }




}