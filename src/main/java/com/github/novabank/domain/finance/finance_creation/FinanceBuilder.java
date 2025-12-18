<<<<<<< HEAD
package com.github.novabank.domain.finance.finance_creation;

import com.github.novabank.domain.finance.finance_accounts.Finance;
=======
package com.github.novabank.domain.finance;
>>>>>>> origin/main

public interface FinanceBuilder<T extends Finance>{


    Finance build();

    void validate();

    void reset();

    public String toString();
}