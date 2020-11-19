package space.harbour.hw9;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class BankTest {

    public Bank bankDepartment = new Bank();
    public Bank bankDepartment2 = new Bank();

    @Before
    public void setup() throws CloneNotSupportedException {
        // Initialize single ATM
        DenominationContainer firstDenominationContainer = new DenominationContainer(50, 2);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 2);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 1);
        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);
        AtmDispenser myPrototype = new AtmDispenser(firstDenominationContainer);

        // Bank 1
        bankDepartment.prototype = myPrototype;
        bankDepartment.getNewAtmDispenser();
        bankDepartment.getNewAtmDispenser();
        bankDepartment.getNewAtmDispenser();

        // Bank 2
        bankDepartment2.prototype = myPrototype;
        bankDepartment2.getNewAtmDispenser();
        bankDepartment2.getNewAtmDispenser();
        bankDepartment2.getNewAtmDispenser();
    }

    @Test
    public void testGetNewAtmDispenser() throws CloneNotSupportedException {
        assertEquals(3, bankDepartment.getNumberofAtms());
    }

    @Test
    public void testGetTotalBalance() {
        assertEquals(435, bankDepartment.getTotalBalance());
    }

    @Test
    public void testAddAtmObserver() {
        bankDepartment.atms.get(0).addObserver(bankDepartment2);
        assertEquals(2, bankDepartment.atms.get(0).countObservers());
    }

    @Test
    public void testRemoveAtmObserver() {
        bankDepartment.atms.get(0).addObserver(bankDepartment2);
        bankDepartment.atms.get(0).removeObserver(bankDepartment);
        assertEquals(1, bankDepartment.atms.get(0).countObservers());
    }

    @Test
    public void testNotifyAllObservers() {
      //  bankDepartment.update(bankDepartment.atms.get(0));
    }
}

