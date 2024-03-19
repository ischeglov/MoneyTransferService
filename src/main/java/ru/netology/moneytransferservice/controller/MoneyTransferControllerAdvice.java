package ru.netology.moneytransferservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.moneytransferservice.exception.ConfirmException;
import ru.netology.moneytransferservice.exception.InputDataException;
import ru.netology.moneytransferservice.exception.TransferException;
import ru.netology.moneytransferservice.model.response.ErrorResponse;

import java.util.concurrent.atomic.AtomicInteger;

@ControllerAdvice
public class MoneyTransferControllerAdvice {

    private final AtomicInteger id = new AtomicInteger();

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ErrorResponse> handleInputData(InputDataException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), incrementAndGetId()));
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> handleTransfer(TransferException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(),
                incrementAndGetId()));
    }

    @ExceptionHandler(ConfirmException.class)
    public ResponseEntity<ErrorResponse> handleConfirmation(ConfirmException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(),
                incrementAndGetId()));
    }

    private int incrementAndGetId() {
        return id.incrementAndGet();
    }
}
