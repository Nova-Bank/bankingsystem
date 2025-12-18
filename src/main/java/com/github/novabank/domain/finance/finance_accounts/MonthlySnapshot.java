package com.github.novabank.domain.finance.finance_accounts;

import java.time.YearMonth;

public record MonthlySnapshot(
        int accountUid,
        YearMonth month,
        int netPositionCents,
        int accountAgeMonths
) {}