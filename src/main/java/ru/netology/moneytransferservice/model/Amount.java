package ru.netology.moneytransferservice.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Amount {

    @NotBlank(message = "недопустимая сумма перевода")
    @Min(0)
    private Integer value;

    @NotBlank(message = "валюта должна быть указана")
    private String currency;
}
