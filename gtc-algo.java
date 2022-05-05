import java.lang.StringBuilder;
import java.util.Random;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;


public class Main
{
    static final String target = "Prototyping is fun!";
    static final String ascii = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~";
    static final int initial_pop_size = 10000;
    static final int iteration_pop_size = 200;

    static String randomString(int len)
    {
        StringBuilder sb = new StringBuilder(len);
        Random rand = new Random();
        for (int i = 0; i < len; i++)
            {
                sb.append(ascii.charAt(rand.nextInt(ascii.length())));
            }
        return sb.toString();
    }

    static String[] makeInitialPopulation()
    {
        String[] population = new String[initial_pop_size];
        for (int i = 0; i < population.length; i++)
            {
                population[i] = randomString(target.length());
            }
        return population;
    }

    static String[] sortHashMap(HashMap<String, Integer> pop)
    {
        ArrayList<String> fit_pop = new ArrayList<>();
        int highest = 0;
        for (int i: pop.values())
        {
            if (i > highest)
            {
                highest = i;
            }
        }
        
        // this horrible thing is a beginners implementation of a sorting
        // algorithm for the hashmap keys. there are better ways to do this.
        // that's my problem in the future. This creates a descending list
        // of individuals in the population by values. Good luck!
        for (int i = 0; i < highest; i++)
        {
            for (Entry<String, Integer> entry: pop.entrySet())
            {
                if (entry.getValue().equals(highest))
                {
                    fit_pop.add(entry.getKey());
                }
            }
            highest--;
        }
        
        String[] fit_array = fit_pop.toArray(new String[fit_pop.size()]);
        return fit_array;
    }

    // scores a string based on it's relevancy (fitness). Higher scores indicate higher fitness
    static String[] sortForFittest(String[] population)
    {
        HashMap<String, Integer> pop_dict = new HashMap<>();
        // for each individual in the population
        for (String individual: population)
        {
            int score = target.length();
            int i = 0;
            // for each individual character that composes the individual.
            // This decreases the score on a non match and increments i to
            // navigate up the array target.toCharArray()
            for (char c: individual.toCharArray())
            {
                if (c != target.toCharArray()[i])
                {
                    score--;
                }
                i++;
            }
            pop_dict.put(individual, score);
        }
        population = sortHashMap(pop_dict);
        return population;
    }

    static String crossover(String individual_a, String individual_b)
    {
        return "a";
    }

    static String[] evolvePopulation(String[] population)
    {
        ArrayList<String> new_population = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5 * iteration_pop_size; i++)
        {
            int bound_a = rand.nextInt(population.length-1);
            int bound_b = rand.nextInt(population.length-1);

            if (bound_a == bound_b && bound_b < 999)
            {
                bound_b++;
            }
            else if (bound_a == bound_b && bound_b == 999)
            {
                bound_b--;
            }



        }
        return population; 
    }
    
    public static void main(String[] args)
    {
        String[] population = makeInitialPopulation();
        population = sortForFittest(population);
        boolean matching = false;
        int counter = 1;
        while (!matching)
        {
            System.out.println("============\nGeneration " + counter + "\n============\n" + population[0]);
            if (population[0].equals(target))
            {
                break;
            }
            population = evolvePopulation(population);
            counter++;
        }
    }
}
