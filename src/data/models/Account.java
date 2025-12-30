package data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Account {
    private String name;
    private long bvn;
    private String nubanAccountNumber;
    private double balance;
    private List<Card> cards;
    private String pin;
    private List <Transaction> transactionList;
}
