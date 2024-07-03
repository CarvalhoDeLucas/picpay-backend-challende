package com.carvalhodelucas.picpay_backend_challenge.transaction;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carvalhodelucas.picpay_backend_challenge.authorization.AuthorizerService;
import com.carvalhodelucas.picpay_backend_challenge.exception.InvalidTransactionException;
import com.carvalhodelucas.picpay_backend_challenge.notification.NotificationService;
import com.carvalhodelucas.picpay_backend_challenge.wallet.Wallet;
import com.carvalhodelucas.picpay_backend_challenge.wallet.WalletRepository;
import com.carvalhodelucas.picpay_backend_challenge.wallet.WalletType;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository,
            AuthorizerService authorizerService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        validade(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));

        authorizerService.authorize(transaction);

        notificationService.notify(transaction);

        return newTransaction;
    }

    private void validade(Transaction transaction) {
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Invalid transaction -%s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Invalid transaction -%s".formatted(transaction)));
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMNUM.getValue() &&
                payer.balance().compareTo(transaction.value()) >= 0 &&
                !payer.id().equals(transaction.payee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
