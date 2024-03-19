package ru.netology.moneytransferservice.service;

import org.junit.jupiter.api.Test;
import ru.netology.moneytransferservice.model.response.TransferAndConfirmResponse;
import ru.netology.moneytransferservice.repository.MoneyTransferRepositoryCardMock;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.moneytransferservice.TestData.*;

class MoneyTransferServiceTest {

    private final MoneyTransferService service = new MoneyTransferService(new MoneyTransferRepositoryCardMock());

    @Test
    void postTransfer() {
        TransferAndConfirmResponse expected = new TransferAndConfirmResponse(OPERATION_ID);

        TransferAndConfirmResponse actual = service.postTransfer(TRANSFER_REQUEST_1);

        assertEquals(expected, actual);
    }

    @Test
    void postConfirmOperation() {
        TransferAndConfirmResponse expected = new TransferAndConfirmResponse(OPERATION_ID);

        TransferAndConfirmResponse actual = service.postConfirmOperation(CONFIRM_REQUEST);

        assertEquals(expected, actual);
    }
}