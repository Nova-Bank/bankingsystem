package com.github.novabank.application.financal_actions;

import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.Credit;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;
import org.springframework.stereotype.Service;

@Service
public class MakePayment {

    private final FinanceRepository financeRepository;

    public MakePayment(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    public int execute(
            String payerAccountUid,
            FinanceType payerType,
            String creditOwnerAccountUid,
            int amountCents
    ) {
        if (amountCents <= 0) {
            throw new IllegalStateException("amountCents must be greater than 0");
        }

        Finance payer = financeRepository.find(payerAccountUid, payerType)
                .orElseThrow(() -> new IllegalStateException("Payer finance not found for accountUid=" + payerAccountUid + " type=" + payerType));

        Finance creditFinance = financeRepository.find(creditOwnerAccountUid, FinanceType.CREDIT)
                .orElseThrow(() -> new IllegalStateException("Credit finance not found for accountUid=" + creditOwnerAccountUid));

        if (!(creditFinance instanceof Credit)) {
            throw new IllegalStateException("FinanceType.CREDIT is not a Credit instance");
        }

        Credit credit = (Credit) creditFinance;

        payer.withdraw(amountCents);
        credit.closeBalance(amountCents);

        financeRepository.save(payerAccountUid, payerType, payer);
        financeRepository.save(creditOwnerAccountUid, FinanceType.CREDIT, credit);

        return credit.getBalance();
    }
}