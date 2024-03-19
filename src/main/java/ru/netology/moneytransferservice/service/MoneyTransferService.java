package ru.netology.moneytransferservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.exception.InputDataException;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.request.ConfirmRequest;
import ru.netology.moneytransferservice.model.request.TransferRequest;
import ru.netology.moneytransferservice.model.response.TransferAndConfirmResponse;
import ru.netology.moneytransferservice.repository.MoneyTransferRepository;

@Service
@AllArgsConstructor
@Slf4j
public class MoneyTransferService {

    private final MoneyTransferRepository repository;

    public TransferAndConfirmResponse postTransfer(TransferRequest transferRequest) {
        final Card cardFrom = repository.getCard(transferRequest.getCardFromNumber());
        final Card cardTo = repository.getCard(transferRequest.getCardToNumber());

        if (cardFrom == null && cardTo != null) {
            log.error("Карта отправителя не найдена");
            throw new InputDataException("Карта отправителя не найдена");
        } else if (cardFrom != null && cardTo == null) {
            log.error("Карта получателя не найдена");
            throw new InputDataException("Карта получателя не найдена");
        } else if (cardFrom == null && cardTo == null) {
            log.error("Карта отправителя и карта получателя не найдены");
            throw new InputDataException("Карта отправителя и карта получателя не найдены");
        }

        if (cardFrom == cardTo) {
            log.error("Указаны идентичные номера карт");
            throw new InputDataException("Указаны идентичные номера карт");
        }

        final String cardFromValidTill = cardFrom.getCardValidTill();
        final String cardFromCVV = cardFrom.getCardCVV();
        final String transferRequestCardFromValidTill = transferRequest.getCardFromValidTill();
        final String transferRequestCardFromCVV = transferRequest.getCardFromCVV();

        if (!cardFromValidTill.equals(transferRequestCardFromValidTill) && cardFromCVV.equals(transferRequestCardFromCVV)) {
            log.error("Указан неверный срок действия карты");
            throw new InputDataException("Указан неверный срок действия карты");
        } else if (cardFromValidTill.equals(transferRequestCardFromValidTill) && !cardFromCVV.equals(transferRequestCardFromCVV)) {
            log.error("Указан не верный CVV код");
            throw new InputDataException("Указан не верный CVV код");
        } else if (!cardFromValidTill.equals(transferRequestCardFromValidTill) && !cardFromCVV.equals(transferRequestCardFromCVV)) {
            log.error("Указаны не верные срок действия и CVV код карты");
            throw new InputDataException("Указаны не верные срок действия и CVV код карты");
        }

        if (cardFrom.getAmount().getValue() < transferRequest.getAmount().getValue()) {
            log.error("Недостаточно средств на карте");
            throw new InputDataException("Недостаточно средств на карте");
        }

        final String transferId = Integer.toString(repository.incrementAndGetOperationId());
        repository.putTransfers(transferId, transferRequest);
        repository.putCodes(transferId, "0000");

        return new TransferAndConfirmResponse(transferId);
    }

    public TransferAndConfirmResponse postConfirmOperation(ConfirmRequest confirmRequest) {
        final String operationId = confirmRequest.getOperationId();

        final TransferRequest transferRequest = repository.removeTransfer(operationId);
        if (transferRequest == null) {
            log.error("Операция не найдена");
            throw new InputDataException("Операция не найдена");
        }

        final String code = repository.removeCode(operationId);
        if (!confirmRequest.getCode().equals(code) || code.isEmpty()) {
            log.error("Неверный код");
            throw new InputDataException("Неверный код");
        }

        final Card cardFrom = repository.getCard(transferRequest.getCardFromNumber());
        final Card cardTo = repository.getCard(transferRequest.getCardToNumber());

        final int cardFromValue = cardFrom.getAmount().getValue();
        final int cardToValue = cardTo.getAmount().getValue();
        final int transferValue = transferRequest.getAmount().getValue();
        final int commission = (int) (transferValue * 0.01);

        cardFrom.getAmount().setValue(cardFromValue - transferValue);
        cardTo.getAmount().setValue(cardToValue + transferValue - commission);

        log.info(String.format("Перевод осуществлен успешно. Номер операции %s. Карта отпрвителя %s. Карта получателя %s. +" +
                        " Сумма %d. Коммисия %d",
                operationId, transferRequest.getCardFromNumber(), transferRequest.getCardToNumber(), transferValue, commission));

        return new TransferAndConfirmResponse(operationId);
    }
}
