package ru.netology.moneytransferservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.model.request.ConfirmRequest;
import ru.netology.moneytransferservice.model.request.TransferRequest;
import ru.netology.moneytransferservice.model.response.TransferAndConfirmResponse;
import ru.netology.moneytransferservice.service.MoneyTransferService;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "${cross.origin.host.name}", maxAge = 3600)
public class MoneyTransferController {

    private MoneyTransferService service;

    @PostMapping("/transfer")
    public TransferAndConfirmResponse postTransfer(@RequestBody TransferRequest transferRequest) {
        return service.postTransfer(transferRequest);
    }

    @PostMapping("/confirmOperation")
    public TransferAndConfirmResponse postConfirmOperation(@RequestBody ConfirmRequest confirmRequest) {
        return service.postConfirmOperation(confirmRequest);
    }
}
