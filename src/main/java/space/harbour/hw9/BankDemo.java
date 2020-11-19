package space.harbour.hw9;

import space.harbour.hw9.AtmDispenser;
import space.harbour.hw9.DenominationContainer;

public class BankDemo {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Initialize single ATM
        DenominationContainer firstDenominationContainer = new DenominationContainer(50, 2);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 2);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 1);
        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);
        AtmDispenser myPrototype = new AtmDispenser(firstDenominationContainer);

        // Start Banking
        Bank bankDepartment = new Bank();
        bankDepartment.prototype = myPrototype;

        // Create dispensers
        bankDepartment.getNewAtmDispenser();
        bankDepartment.getNewAtmDispenser();
        bankDepartment.getNewAtmDispenser();

        bankDepartment.atms.get(1).giveMeMoney(1000);
        System.out.println(bankDepartment.getTotalBalance());

    }

}