package lk.ijse.dep7.pos.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetail implements Serializable {
    private OrderDetailsPK orderDetailsPK;
    private BigDecimal unitPrice;
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailsPK orderDetailsPK, BigDecimal unitPrice, int qty) {
        this.orderDetailsPK = orderDetailsPK;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public OrderDetail(String orderId, String itemCode, BigDecimal unitPrice, int qty) {
        this.orderDetailsPK = new OrderDetailsPK(orderId,itemCode);
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsPK=" + orderDetailsPK +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                '}';
    }
}
