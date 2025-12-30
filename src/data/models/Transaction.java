package data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Date date;
    private Account senderAccount;
    private Account receiverAccount;
    private double amount;
    private String referenceNumber;

}
