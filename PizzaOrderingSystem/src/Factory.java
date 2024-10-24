import java.util.Random;

public class Factory {

    public static Pizza generatePizza(){
        String[] pizzaNames = new String[] {
                "Margherita", "Pepperoni", "Hawaiian", "Veggie Pizza",
                "Neapolitan", "BBQ Chicken Pizza", "Seafood Pizza"
        };

        double[] pizzaPrices = new double[] {10.99, 15.99, 9.99, 13.99, 12.99, 19.99};

        Random random = new Random();

        int firstRandomIndex = random.nextInt(pizzaNames.length);
        int secondRandomIndex = random.nextInt(pizzaPrices.length);

        return new Pizza(pizzaNames[firstRandomIndex], pizzaPrices[secondRandomIndex]);
    }

    public static Drink generateDrink(){
        String[] drinkNames = new String[] {
                "Water", "Coca-Cola", "Sprite",
                "Fanta", "Beer", "Dr.Pepper"
        };

        double[] drinkPrices = new double[] {2.99, 3.99, 2.99, 1.99, 1.59, 2.35};

        Random random = new Random();

        int firstRandomIndex = random.nextInt(drinkNames.length);
        int secondRandomIndex = random.nextInt(drinkPrices.length);

        return new Drink(drinkNames[firstRandomIndex], drinkPrices[secondRandomIndex]);
    }

    public static Customer generateCustomer(){
        String[] customerUsernames = new String[] {
                "Georgi Kostadinov", "Ivan Ivanov",
                "Mihaela Todorova", "Gabriela Ivanova",
                "Rostislav Popov"
        };

        String[] customerPasswords = new String[]{
                "1234567", "qwerty123", "987654",
                "password123", "drowssap321"
        };

        String[] customerAddresses = new String[]{
                "Studenski grad bl. 54", "Mladost 2 bl. 225",
                "Hladilnika bl. 118", "Bratq Miladinovi bl. 91"
        };

        Random random = new Random();

        int firstRandomIndex = random.nextInt(customerUsernames.length);
        int secondRandomIndex = random.nextInt(customerPasswords.length);
        int thirdRandomIndex = random.nextInt(customerAddresses.length);

        return new Customer(customerUsernames[firstRandomIndex], customerPasswords[secondRandomIndex],  customerAddresses[thirdRandomIndex]);

    }

    public static Employee generateEmployee(){
        String[] employeeUsernames = new String[] {
                "Damqn Kolev", "John Smith",
                "Maria Todorova", "Konstantina Deqnova",
                "Georgi Kostov"
        };

        String[] employeePasswords = new String[] {
                "1234567", "qwerty123", "987654",
                "password123", "drowssap321"
        };


        Random random = new Random();

        int firstRandomIndex = random.nextInt(employeeUsernames.length);
        int secondRandomIndex = random.nextInt(employeePasswords.length);


        return new Employee(employeeUsernames[firstRandomIndex], employeePasswords[secondRandomIndex]);
    }


}
