package ru.netology.moneytransferservice.repository;

import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.request.TransferRequest;

import static ru.netology.moneytransferservice.TestData.*;

public class MoneyTransferRepositoryCardMock implements MoneyTransferRepository{

    @Override
    public Card getCard(String cardNumber) {
        if (cardNumber.equals(CARD_NUMBER_1)) {
            return CARD_1;
        }
        if (cardNumber.equals(CARD_NUMBER_2)) {
            return CARD_2;
        }
        return null;
    }

    @Override
    public int incrementAndGetOperationId() {
        return Integer.parseInt(OPERATION_ID);
    }

    @Override
    public void putTransfers(String id, TransferRequest transferRequest) {

    }

    @Override
    public void putCodes(String id, String code) {

    }

    @Override
    public TransferRequest removeTransfer(String id) {
        if (id.equals(OPERATION_ID)) {
            return TRANSFER_REQUEST_1;
        }
        return null;
    }

    @Override
    public String removeCode(String id) {
        if (id.equals(OPERATION_ID)) {
            return CODE;
        }
        return null;
    }
}
