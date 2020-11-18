package space.harbour.hw8;

import java.util.Map;
import java.util.Map;

public class DenominationContainer implements Iterator {

    private final int denomination;
    private int count;

    private DenominationContainer nextContainer;

    public DenominationContainer(int denomination, int count) {
        this.denomination = denomination;
        this.count = count;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getCount() {
        return count;
    }

    public void setNextContainer(DenominationContainer nextContainer) {
        this.nextContainer = nextContainer;
    }

    public void handle(int amount, Map<Integer, Integer> bills) {
        int billsToGive = 0;
        int remainingAmount = 0;
        if (amount > 0 && amount >= denomination) {
            billsToGive = Math.min(count, amount / denomination);
            remainingAmount = amount - (billsToGive * denomination);
            count = count - billsToGive;
            if (bills.containsKey(denomination)) {
                bills.put(denomination, bills.get(denomination) + billsToGive);
            } else {
                bills.put(denomination, billsToGive);
            }
        } else {
            remainingAmount = amount;
        }
        if (nextContainer != null) {
            nextContainer.handle(remainingAmount, bills);
        } else {
            if (remainingAmount > 0) {
                System.out.println("Sorry not enough money for this!" + "\n");
            } else {
                System.out.println("Successful transaction!");
                System.out.println("Bills distribution :" + bills + "\n");
            }
        }
    }

    @Override
    public boolean hasNext() {
        boolean nextCont = false;
        if (nextContainer != null) {
            nextCont = true;
        }
        return nextCont;
    }

    @Override
    public Object next() {
        return nextContainer;
    }
}