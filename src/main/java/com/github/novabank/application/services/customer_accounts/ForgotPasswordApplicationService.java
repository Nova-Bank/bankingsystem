package com.github.novabank.application.services.customer_accounts;

import com.github.novabank.application.customer_accounts.ChangePassword;
import com.github.novabank.application.dtos.ForgotPasswordResult;
import com.github.novabank.presentation.dtos.ForgotPasswordRequest;
import com.github.novabank.domain.account.accounts.Account;

/**
 * Application service wrapping the forgot password flow.
 * Accepts a front-end ForgotPasswordRequest and returns a ForgotPasswordResult.
 */
public class ForgotPasswordApplicationService {

    /**
     * Executes the forgot password flow for the given request.
     *
     * @param request ForgotPasswordRequest containing email and new password
     * @return ForgotPasswordResult containing the username/email and masked password
     * @throws Exception if account lookup or update fails
     */
    public ForgotPasswordResult execute(ForgotPasswordRequest request) throws Exception {
        if (request == null) {
            throw new IllegalArgumentException("ForgotPasswordRequest cannot be null");
        }

        // Call domain logic
        Account updatedAccount = ChangePassword.changePassword(request.getEmail(), request.getNewPassword());

        // Map domain Account -> back-end DTO
        ForgotPasswordResult result = new ForgotPasswordResult();
        result.setUsername(updatedAccount.getEmail()); // use email as identifier
        result.setNewPassword("[PROTECTED]"); // never expose actual password

        return result;
    }
}
