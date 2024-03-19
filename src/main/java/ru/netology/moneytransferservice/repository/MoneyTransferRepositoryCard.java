package ru.netology.moneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.request.TransferRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MoneyTransferRepositoryCard implements MoneyTransferRepository {

    private final Map<String, Card> cards = new ConcurrentHashMap<>();

    private final Map<String, TransferRequest> transfers = new ConcurrentHashMap<>();
    private final Map<String, String> codes = new ConcurrentHashMap<>();
    private final AtomicInteger operationId = new AtomicInteger();

    {
        final String cardNumber1 = "1111000011110000";
        final String cardNumber2 = "2222000022220000";
        final String cardNumber3 = "3333000033330000";

        cards.put(cardNumber1, new Card(cardNumber1, "11/27", "100", new Amount(100_000, "RUR")));
        cards.put(cardNumber2, new Card(cardNumber2, "12/28", "200", new Amount(100_000, "RUR")));
        cards.put(cardNumber3, new Card(cardNumber3, "01/29", "300", new Amount(100_000, "RUR")));
    }

    @Override
    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }

    @Override
    public int incrementAndGetOperationId() {
        return operationId.incrementAndGet();
    }

    @Override
    public void putTransfers(String id, TransferRequest transferRequest) {
        transfers.put(id, transferRequest);
    }

    @Override
    public void putCodes(String id, String code) {
        codes.put(id, code);
    }

    @Override
    public TransferRequest removeTransfer(String id) {
        return transfers.remove(id);
    }

    @Override
    public String removeCode(String id) {
        return codes.remove(id);
    }
}
