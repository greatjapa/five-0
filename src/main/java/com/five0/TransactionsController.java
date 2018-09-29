package com.five0;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TransactionsController {

    Statistics statistics = new Statistics();

    // curl -d '{"amount":"12.3343", "timestamp":"2018-07-17T09:59:51.312Z"}' -H "Content-Type: application/json" -X POST http://localhost:8080/transactions
    // curl -X "DELETE" http://localhost:8080/transactions

    @RequestMapping(method=POST, name = "/transactions")
    public ResponseEntity<String> transactions(@RequestBody Transaction transaction) {

        if (!isTimeRange(transaction.getTimestamp())) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (isFuture(transaction.getTimestamp())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        calcStatistics(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method=GET, name = "/statistics")
    public @ResponseBody Statistics statistics() {
        return statistics;
    }

    @RequestMapping(method=DELETE, name = "/transactions")
    public ResponseEntity<String> transactions() {
        this.statistics = new Statistics();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void calcStatistics(Transaction transaction) {
        BigDecimal amount = transaction.getAmount();

        statistics.setSum(amount.add(statistics.getSum()).setScale(2, RoundingMode.HALF_UP));
        if (amount.compareTo(statistics.getMax()) > 0) {
            statistics.setMax(amount.setScale(2, RoundingMode.HALF_UP));
        }
        if (statistics.getMin().compareTo(BigDecimal.ZERO) == 0 || amount.compareTo(statistics.getMin()) < 0) {
            statistics.setMin(amount.setScale(2, RoundingMode.HALF_UP));
        }
        statistics.setCount(statistics.getCount() + 1);
        statistics.setAvg(statistics.getSum().divide(BigDecimal.valueOf(statistics.getCount()),2, RoundingMode.HALF_UP));

        System.out.println(statistics);
    }

    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    private static boolean isTimeRange(Date date) {
        long diff = new Date().getTime() - date.getTime();
        System.out.println("diff time : " + TimeUnit.MILLISECONDS.convert(diff,TimeUnit.MILLISECONDS));
        return TimeUnit.MILLISECONDS.convert(diff,TimeUnit.MILLISECONDS) < 60000;
    }

    private static boolean isFuture(Date date) {
        return new Date().getTime() - date.getTime() < 0;
    }
}
