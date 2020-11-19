package space.harbour.hw9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmDispenser implements Cloneable, ObservableAtm {

    private final DenominationContainer denominationContainer;
    private Map<Integer, Integer> bills;
    private List<Bank> observers;

    public AtmDispenser(DenominationContainer denominationContainer) {
        this.denominationContainer = denominationContainer;
        this.bills = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public Map<Integer, Integer> giveMeMoney(int amount) {
        denominationContainer.handle(amount, bills);
        if (balance() == 0) {
            notifyObserver("Empty");
        }
        return bills;
    }

    public int balance() {
        DenominationContainer currentContainer = denominationContainer;
        int countBalance = currentContainer.getDenomination() * currentContainer.getCount();
        ;
        while (currentContainer.hasNext()) {
            currentContainer = (DenominationContainer) currentContainer.next();
            countBalance += currentContainer.getDenomination() * currentContainer.getCount();
        }
        return countBalance;
    }

    public AtmDispenser clone() throws CloneNotSupportedException {
        // Initialize empty currentContainer and a list of Containers
        DenominationContainer currentContainer = denominationContainer;
        // Initialize list of objects
        List<Object> listOfContainers = new ArrayList<Object>();
        listOfContainers.add(currentContainer.clone());
        while (currentContainer.hasNext()) {
            currentContainer = (DenominationContainer) currentContainer.next();
            DenominationContainer clonedContainer = currentContainer.clone();
            listOfContainers.add(clonedContainer);
        }
        //System.out.println("size of list is :" + listOfContainers.size());
        DenominationContainer pointerContainer;
        for (int i = 0; i < listOfContainers.size() - 1; i++) {
            pointerContainer = (DenominationContainer) listOfContainers.get(i);
            // System.out.println(i);
            pointerContainer.setNextContainer((DenominationContainer) listOfContainers.get(i + 1));
        }
        //System.out.println(listOfContainers.get(3));
        AtmDispenser clonedAtm = new AtmDispenser((DenominationContainer) listOfContainers.get(0));
        return clonedAtm;
    }

    @Override
    public void addObserver(ObserverAtm observer) {
        observers.add((Bank) observer);
    }

    @Override
    public void removeObserver(ObserverAtm observer) {
        observers.remove(observer);
    }

    public int countObservers() {
        return observers.size();
    }

    @Override
    public void notifyObserver(String message) {
        for (Bank department : observers) {
            department.update(this);
        }
    }
}

