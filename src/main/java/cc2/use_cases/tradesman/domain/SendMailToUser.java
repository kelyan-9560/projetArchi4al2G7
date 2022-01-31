package cc2.use_cases.tradesman.domain;


public final class SendMailToUser {

    public void sendMail (TradesMan tradesMan){
        System.out.println("Mail envoyé à " + tradesMan);
    }
}