package lk.ijse.dep7.pos.entity;

import java.io.Serializable;

public class OrderDetailsPK implements Serializable {
    private String orderId;
    private String itemCode;

    public OrderDetailsPK() {
    }

    public OrderDetailsPK(String orderId, String itemCode) {
        this.orderId = orderId;
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "OrderDetailsPK{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                '}';
    }
}
