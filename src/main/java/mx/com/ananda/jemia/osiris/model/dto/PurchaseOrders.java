package mx.com.ananda.jemia.osiris.model.dto;

import lombok.Data;

@Data
public class PurchaseOrders {
    private int docNum;
    private String docDate;
    private double docTotal;
    private String cardCode;
    private String cardName;
}
