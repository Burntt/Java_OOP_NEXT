package space.harbour.hw9;

import java.util.ArrayList;
import java.util.List;

public class Bank implements ObserverAtm {
    ArrayList<AtmDispenser> atms;
    AtmDispenser prototype;

    public Bank() {
        this.atms = new ArrayList<>();
    }

    public AtmDispenser getNewAtmDispenser() throws CloneNotSupportedException {
        AtmDispenser newAtm = prototype.clone();
        newAtm.addObserver(this);
        atms.add(newAtm);
        return newAtm;
    }

    public int getTotalBalance() {
        return atms.parallelStream().mapToInt(atm -> atm.balance()).sum();
    }

    public AtmDispenser getAtm(int index) {
        return atms.get(index);
    }

    public int getNumberofAtms() {
        return atms.size();
    }

    @Override
    public void update(AtmDispenser atmDispenser) {
        System.out.println("BANK: ATM " + atmDispenser.hashCode() + " is EMPTY");

    }
}
