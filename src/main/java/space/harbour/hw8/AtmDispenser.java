package space.harbour.hw8;

import java.util.HashMap;
import java.util.Map;

public class AtmDispenser {

    private final DenominationContainer denominationContainer;
    private Map<Integer, Integer> bills;

    public AtmDispenser(DenominationContainer denominationContainer) {
        this.denominationContainer = denominationContainer;
        this.bills = new HashMap<>();
    }

    public Map<Integer, Integer> giveMeMoney(int amount) {
        denominationContainer.handle(amount, bills);
        return bills;
    }

    public int balance() {
        DenominationContainer currentContainer = denominationContainer;
        int countBalance = currentContainer.getDenomination() * currentContainer.getCount();;
        while (currentContainer.hasNext()) {
            currentContainer = (DenominationContainer) currentContainer.next();
            countBalance += currentContainer.getDenomination() * currentContainer.getCount();
        }
        return countBalance;
    }

}