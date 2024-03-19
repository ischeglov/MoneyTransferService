package ru.netology.moneytransferservice;

import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Card;
import ru.netology.moneytransferservice.model.request.ConfirmRequest;
import ru.netology.moneytransferservice.model.request.TransferRequest;

public class TestData {

    public static final String CARD_NUMBER_1 = "1111000011110000";
    public static final String CARD_VALID_TILL_1 = "11/27";
    public static final String CARD_CVV_1 = "100";
    public static final Card CARD_1 = new Card(CARD_NUMBER_1, CARD_VALID_TILL_1, CARD_CVV_1, new Amount(100_000, "RUR"));

    public static final String CARD_NUMBER_2 = "2222000022220000";
    public static final String CARD_VALID_TILL_2 = "12/28";
    public static final String CARD_CVV_2 = "200";
    public static final Card CARD_2 = new Card(CARD_NUMBER_2, CARD_VALID_TILL_2, CARD_CVV_2, new Amount(100_000, "RUR"));


    public static final String CARD_NUMBER_3 = "3333000033330000";
    public static final String CARD_VALID_TILL_3 = "01/29";
    public static final String CARD_CVV_3 = "300";
    public static final Card CARD_3 = new Card(CARD_NUMBER_3, CARD_VALID_TILL_3, CARD_CVV_3, new Amount(100_000, "RUR"));

    public static final String CARD_NUMBER_4 = "4444000044440000";
    public static final String CARD_VALID_TILL_4 = "02/30";
    public static final String CARD_CVV_4 = "400";

    public static final String OPERATION_ID = "1";
    public static final String CODE = "0000";

    public static final TransferRequest TRANSFER_REQUEST_1 = new TransferRequest (CARD_NUMBER_1, CARD_VALID_TILL_1,
            CARD_CVV_1, CARD_NUMBER_2, new Amount(1_000, "RUR"));
    public static final TransferRequest TRANSFER_REQUEST_2 = new TransferRequest (CARD_NUMBER_3, CARD_VALID_TILL_3,
            CARD_CVV_3, CARD_NUMBER_4, new Amount(100, "RUR"));

    public static final ConfirmRequest CONFIRM_REQUEST = new ConfirmRequest(OPERATION_ID, CODE);
}
