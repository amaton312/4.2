import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cereal {

    String name;
    String type;
    int calories;
    int protein;
    int fat;
    int sodium;
    double fiber;
    double carbs;
    int sugar;
    int potassium;
    int vitamins;
    int shelf;
    double weight;
    double cups;
    double rating;

    public Cereal(String name, String type, int calories, int protein, int fat, int sodium,
                  double fiber, double carbs, int sugar, int potassium, int vitamins,
                  int shelf, double weight, double cups, double rating) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.fiber = fiber;
        this.carbs = carbs;
        this.sugar = sugar;
        this.potassium = potassium;
        this.vitamins = vitamins;
        this.shelf = shelf;
        this.weight = weight;
        this.cups = cups;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Cereal{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", fiber=" + fiber +
                ", carbs=" + carbs +
                ", sugar=" + sugar +
                ", potassium=" + potassium +
                ", vitamins=" + vitamins +
                ", shelf=" + shelf +
                ", weight=" + weight +
                ", cups=" + cups +
                ", rating=" + rating +
                '}';
    }

    public static double calculateAverageRating(Cereal[] cereals) {
        double total = 0;
        for (Cereal cereal : cereals) {
            total += cereal.getRating();
        }
        return total / cereals.length;
    }

    public static double calculateStdevRating(Cereal[] cereals) {
        double average = calculateAverageRating(cereals);
        double sumSquaredDiffs = 0;
        for (Cereal cereal : cereals) {
            double diff = cereal.getRating() - average;
            sumSquaredDiffs += diff * diff;
        }
        return Math.sqrt(sumSquaredDiffs / cereals.length);
    }

    public static void main(String[] args) {
        List<Cereal> cereals = new ArrayList<>();
        String filePath = "Cereal.csv";
        Cereal[] cerealArray = null;

        try (Scanner scanner = new Scanner(new File(filePath))) { // csv parsing helped by copilot
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");

                String name = values[0];
                String type = values[1];
                int calories = Integer.parseInt(values[2]);
                int protein = Integer.parseInt(values[3]);
                int fat = Integer.parseInt(values[4]);
                int sodium = Integer.parseInt(values[5]);
                double fiber = Double.parseDouble(values[6]);
                double carbs = Double.parseDouble(values[7]);
                int sugar = Integer.parseInt(values[8]);
                int potassium = Integer.parseInt(values[9]);
                int vitamins = Integer.parseInt(values[10]);
                int shelf = Integer.parseInt(values[11]);
                double weight = Double.parseDouble(values[12]);
                double cups = Double.parseDouble(values[13]);
                double rating = Double.parseDouble(values[14]);

                Cereal cereal = new Cereal(name, type, calories, protein, fat, sodium, fiber, carbs, sugar, potassium, vitamins, shelf, weight, cups, rating);
                cereals.add(cereal);
            }

            cerealArray = cereals.toArray(new Cereal[0]);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing a number: " + e.getMessage());
        }

        if (cerealArray.length > 0) {
            System.out.println("avg rating: " + calculateAverageRating(cerealArray));

            double averageRating = calculateAverageRating(cerealArray);
            double highestDeviation = 0;
            Cereal highestCereal = null;

            for (Cereal cereal : cerealArray) {
                double deviation = Math.abs(cereal.getRating() - averageRating);
                if (deviation > highestDeviation) {
                    highestDeviation = deviation;
                    highestCereal = cereal;
                }
            }

            System.out.println("Cereal with highest nutrition rating deviation: " + highestCereal.getName());
            
            // for (Cereal cereal : cerealArray) {
            //     System.out.println("Rating: " + cereal.getRating());
            // }

            double zScore = (highestCereal.getRating() - averageRating) / calculateStdevRating(cerealArray);
            System.out.println("z score of cereal w highest rating deviation: " + zScore);

        } else {
            System.err.println("error");
        }


    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public double getFiber() {
        return fiber;
    }

    public double getCarbs() {
        return carbs;
    }

    public int getSugar() {
        return sugar;
    }

    public int getPotassium() {
        return potassium;
    }

    public int getVitamins() {
        return vitamins;
    }

    public int getShelf() {
        return shelf;
    }

    public double getWeight() {
        return weight;
    }

    public double getCups() {
        return cups;
    }

    public double getRating() {
        return rating;
    }
}