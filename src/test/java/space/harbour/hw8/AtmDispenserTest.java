package space.harbour.hw8;

import java.util.HashMap;
import junit.framework.TestCase;

public class AtmDispenserTest extends TestCase {

    public void testGiveMeMoney() {
        DenominationContainer firstDenominationContainer = new DenominationContainer(50, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 1);
        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);

        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);

        int balance = atmDispenser.balance();
        int amount = 5;
        atmDispenser.giveMeMoney(amount);

        HashMap<Integer, Integer> billsHmap = new HashMap<>();
        billsHmap.put(amount, 1);

        assertEquals(billsHmap, atmDispenser.giveMeMoney(amount));

    }

    public void testGiveMeMoney2() {
        DenominationContainer firstDenominationContainer = new DenominationContainer(5, 1);
        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);

        int amount = 10;
        atmDispenser.giveMeMoney(amount);

        HashMap<Integer, Integer> billsHmap = new HashMap<>();
        billsHmap.put(5, 1);

        // Distribution of bills should remain intact
        assertEquals(billsHmap, atmDispenser.giveMeMoney(amount));

    }


    public void testBalance() {
        DenominationContainer firstDenominationContainer = new DenominationContainer(50, 2);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 5);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 20);
        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);

        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);
        int balance = atmDispenser.balance();
        assertEquals(300, balance);
    }

    // Check that balance updates after a dispense
    public void testBalance2() {
        DenominationContainer firstDenominationContainer = new DenominationContainer(50, 2);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 5);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 20);
        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);

        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);
        int balance = atmDispenser.balance();
        assertEquals(300, balance);
        int amount = 200;
        atmDispenser.giveMeMoney(amount);
        int balanceAfter = atmDispenser.balance();
        assertEquals(100, balanceAfter);
    }

}