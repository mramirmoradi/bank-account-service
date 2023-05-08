package com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity;

import com.mobilab.exchangeconversionrateservice.Base.Entity.BaseEntity;
import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency-pair")
public class CurrencyPair extends BaseEntity {

    @Column(columnDefinition = "ENUM('USD','EUR')")
    @Enumerated(EnumType.STRING)
    private Currency baseCurrency;

    @Column(columnDefinition = "ENUM('USD','EUR')")
    @Enumerated(EnumType.STRING)
    private Currency quoteCurrency;

    @Column(name = "exchange_rate")
    private double exchangeRate;
}
