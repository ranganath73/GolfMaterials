package lesson_24;

import lesson_19.FuelType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String FILENAME = "cars.txt";

    public static void main(String[] args) {
        List<Car> cars = readCarsFromFile(FILENAME);
        CarRepository carRepository = new CarRepository(cars);

        Scanner scanner = new Scanner(System.in);
        endlessLoop:
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0 -> {
                    break endlessLoop;
                }
                case 1 -> printCars(carRepository.getCars());
                case 2 -> printCars(carRepository.getAvailableCars());
                case 3 -> printCars(carRepository.getRentedCars());
                case 4 -> {
                    System.out.println("Select one of the available cars:");
                    printCars(carRepository.getAvailableCars());

                    // 1. Audi
                    // 2. BMW
                    // 3. Toyota -> rented
                    int carChoice = Integer.parseInt(scanner.nextLine());
                    carRepository.getAvailableCars().get(carChoice - 1).setRented(true);
                }
                case 5 -> {
                    System.out.println("Which car are you returning:");
                    printCars(carRepository.getRentedCars());

                    int carChoice = Integer.parseInt(scanner.nextLine());
                    carRepository.getRentedCars().get(carChoice - 1).setRented(false);
                }
                case 6 -> {
                    Car car = createCarFromUserInput(scanner);
                    carRepository.add(car);
                }
            }
        }

        saveCarsToFile(carRepository.getCars(), FILENAME);
    }

    private static Car createCarFromUserInput(Scanner scanner) {
        System.out.println("Enter maker:");
        String maker = scanner.nextLine();
        System.out.println("Enter model:");
        String model = scanner.nextLine();
        System.out.println("Enter year:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter is manual:");
        boolean isManual = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Enter fuel type:");
        FuelType fuelType = FuelType.valueOf(scanner.nextLine());
        System.out.println("Enter daily price:");
        double price = Double.parseDouble(scanner.nextLine());
        return new Car(maker, model, year, isManual, fuelType, price, false);
    }

    private static void printCars(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, cars.get(i)));
        }
    }

    private static List<Car> readCarsFromFile(String filename) {
        List<Car> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                Car car = Car.parseFromString(line);
                list.add(car);
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found. Try again.");
        } catch (IOException e) {
            System.out.println("Something went wrong while reading from the file...");
        }

        return list;
    }

    private static void saveCarsToFile(List<Car> cars, String filename) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename);

            for (Car car : cars) {
                writer.println(car.toFileFormat());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
        }
    }

    public static void printMenu() {
        System.out.println("Enter an option from below:");
        System.out.println("0. Exit");
        System.out.println("1. List all cars");
        System.out.println("2. List available cars");
        System.out.println("3. List rented cars");
        System.out.println("4. Rent a car");
        System.out.println("5. Return a car");
        System.out.println("6. Add a car");
    }
}
