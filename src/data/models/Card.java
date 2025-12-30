package data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private String cvv;
    private Date issuerDate;
    private Date expiryDate;
    private String name;
    private CardType cardType;
    private String bankName;
    private long cardNumber;


}
