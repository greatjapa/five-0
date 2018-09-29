package com.five0;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private BigDecimal amount;
    private Date timestamp;

    public Transaction(BigDecimal amount, Date timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
