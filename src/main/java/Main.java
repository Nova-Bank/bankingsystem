import com.github.novabank.domain.account.AccountFactory;
import com.github.novabank.domain.account.AccountInfo;
import com.github.novabank.domain.finance.Chequing;
import com.github.novabank.domain.finance.Credit;
import com.github.novabank.domain.finance.Finance;
import com.github.novabank.domain.finance.Savings;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.github.novabank.domain.account.AdultAccount;
import com.github.novabank.domain.account.ChildAccount;
import com.github.novabank.infrastructure.config.FirebaseConfig;
import com.github.novabank.infrastructure.database.AccountRepositoryimpl;
import com.google.cloud.firestore.Firestore;


public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("I exist");

        Chequing adultChequing = new Chequing(1000, 0, 500, 1000, 2000);
        Savings adultSavings = new Savings(12345, 5000, 1000, 0, 500);
        Credit adultCredit = new Credit(0, 10000, 0.21, 0, 0, 0, 0, 0, 0);

        List<Finance> adultFinanceProducts = new ArrayList<>();
        adultFinanceProducts.add(adultChequing);
        adultFinanceProducts.add(adultSavings);
        adultFinanceProducts.add(adultCredit);


        AccountInfo adultinfo = new AccountInfo("thisisanemail@gmail.com", "Melons123!", "Evan Wee", LocalDate.parse("2006-10-30"), "6475691030");
        AdultAccount adult = AccountFactory.createAdultAccount(adultinfo, adultFinanceProducts);

        System.out.println("Adult Account Finance Products: " + adult.getFinanceProducts());
        System.out.println("Adult Chequing Balance: " + adult.getFinanceProducts().get("Chequing").getBalance());
        System.out.println("Adult Savings Balance: " + adult.getFinanceProducts().get("Savings").getBalance());
        System.out.println("Adult Credit Limit: " + ((Credit) adult.getFinanceProducts().get("Credit")).creditLimit);


        Chequing childChequing = new Chequing(500, 0, 100, 200, 300);
        List<Finance> childFinanceProducts = new ArrayList<>();
        childFinanceProducts.add(childChequing);

        AccountInfo childinfo = new AccountInfo("thisisaemail@gmail.com", "thisisapassword$", "Toufic Joe", LocalDate.parse("2010-10-30"), "6475691030");
        ChildAccount child = AccountFactory.createChildAccount(childinfo, adult, childFinanceProducts);

        System.out.println("Child Account Finance Products: " + child.getFinanceProducts());
        System.out.println("Child Chequing Balance: " + child.getFinanceProducts().get("Chequing").getBalance());

    }
    
}
