import com.github.novabank.domain.account.account_creation.*;
import com.github.novabank.domain.account.accounts.AdultAccount;
import com.github.novabank.domain.account.accounts.ChildAccount;
import com.github.novabank.domain.finance.finance_accounts.Chequing;
import com.github.novabank.domain.finance.finance_accounts.Credit;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.Savings;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("I exist");

        Chequing adultChequing = new Chequing(1000, 0, 500, 1000);
        Savings adultSavings = new Savings(12345, 5000, 1000, 0, 500);
        Credit adultCredit = new Credit(0, 10000, 0.21, 0, 0, 0, 0, 0, 0);

        List<Finance> adultFinanceProducts = new ArrayList<>();
        adultFinanceProducts.add(adultChequing);
        adultFinanceProducts.add(adultSavings);
        adultFinanceProducts.add(adultCredit);


        AccountInfo adultinfo = new AccountInfo("thisisanemail@gmail.com", "Melons123!", "Evan Wee", LocalDate.parse("2006-10-30"), "6475691030");
        AdultAccount adult = (AdultAccount) AccountFactory.createAccount(adultinfo, adultFinanceProducts, null);

        System.out.println("Adult Account Finance Products: " + adult.getFinanceProducts());
        System.out.println("Adult Chequing Balance: " + adult.getFinanceProducts().get("Chequing").getBalance());
        System.out.println("Adult Savings Balance: " + adult.getFinanceProducts().get("Savings").getBalance());
        System.out.println("Adult Credit Limit: " + ((Credit) adult.getFinanceProducts().get("Credit")).getCreditLimit());


        Chequing childChequing = new Chequing(500, 0, 100, 200);
        List<Finance> childFinanceProducts = new ArrayList<>();
        childFinanceProducts.add(childChequing);

        AccountInfo childinfo = new AccountInfo("thisisaemail@gmail.com", "thisisapassword$", "Toufic Joe", LocalDate.parse("2010-10-30"), "6475691030");
        ChildAccount child = (ChildAccount) AccountFactory.createAccount(childinfo, childFinanceProducts, adult);

        System.out.println("Child Account Finance Products: " + child.getFinanceProducts());
        System.out.println("Child Chequing Balance: " + child.getFinanceProducts().get("Chequing").getBalance());

    }
    
}
