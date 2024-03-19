package ru.netology.moneytransferservice.repository;

import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.request.TransferRequest;

public interface MoneyTransferRepository {

    Card getCard(String cardNumber);

    int incrementAndGetOperationId();

    void putTransfers(String id, TransferRequest transferRequest);

    void putCodes(String id, String code);

    TransferRequest removeTransfer(String id);

    String removeCode(String id);
}
