import java.util.*;
import java.io.*;

public class Toy_Lottery {
    private List<Toy> listToys = new ArrayList<>();
    private int toy_count;

    public List<Toy> getToys() {
        return listToys;
    }

    public int getToy_count() {
        System.out.println("Number of toys = ");
        return toy_count;
    }

    public List<Toy> add_toy_in_listToy(Toy name) {
        listToys.add(name);
        toy_count++;
        return listToys;
    }

    private List<Toy> delete_toy_in_listToy(Toy name) {
        listToys.remove(name);
        toy_count--;
        return listToys;
    }

    private void saveInFile() {
        try (FileWriter writer = new FileWriter("winning_toys.txt", true)) {
            String text = listToys.get(0).getToy_name();
            writer.write(text + "\n");
            writer.close();
        } catch (Exception ex) {
            System.out.println("File rewrite error");
        }
    }

    public void readFile() {
        try {
            FileReader fr = new FileReader("winning_toys.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }

        } catch (Exception ex) {
            System.out.println("File read error");
        }
    }

    public void lotteryToy() {
        Scanner scan = new Scanner(System.in);
        int chance = 50;
        System.out.println("Default chance: 50%");
        System.out.println("If you would like to change the chance, type 1");
        System.out.println("If you would like to continue with 50% chance, type 2");
        String changeСhance = scan.next();
        if (changeСhance.equals("1")) {
            System.out.println("Type the chance between 1% and 100%");
            chance = scan.nextInt();
        }
        if (chance >= 0 && chance <= 100) {
            Random rnd = new Random();
            int num = rnd.nextInt(100);
            if (num > chance) {
                System.out.println("Lost");
            } else if (num < chance) {
                System.out.println("Won");
                saveInFile();
                delete_toy_in_listToy(listToys.get(0));
            }
        } else {
            System.out.println("Wrong chance, try again");
        }
    }
}