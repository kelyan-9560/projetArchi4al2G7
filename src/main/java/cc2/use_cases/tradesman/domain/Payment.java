package cc2.use_cases.tradesman.domain;

import cc2.use_cases.tradesman.domain.TradesMan;

public final class Payment {

    public void payment(TradesMan tradesMan){
        System.out.println("Paiement par " + tradesMan);
    }
}
