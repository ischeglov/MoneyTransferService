package ru.netology.moneytransferservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {

    @NotBlank(message = "неверный номер карты")
    @Size(min = 16, max = 16)
    @Pattern(regexp = "(?<!\\d)\\d{16}(?!\\d)")
    private String cardNumber;

    @NotBlank(message = "неверный срок действия карты")
    @Size(min = 5, max = 5)
    @Pattern(regexp = "(0[1-9]|1[0-2])[/][0-9]{2}")
    private String cardValidTill;

    @NotBlank(message = "CVV обязателен для карты")
    @Size(min = 3, max = 3)
    @Pattern(regexp = "(?<!\\d)\\d{3}(?!\\d)")
    private String cardCVV;

    private Amount amount;
}
