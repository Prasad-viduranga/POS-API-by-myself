package lk.ijse.dep7.pos.dao;

import lk.ijse.dep7.pos.entity.Order;

import java.sql.Connection;
import java.util.List;

public class OrderDAO {

    private final Connection connection;


    public OrderDAO(Connection connection) {
        this.connection = connection;
    }


    public void saveOrder(Order order) {

    }

    public void updateOrder(Order order) {

    }

    public void deleteOrderByCode(String orderCode) {

    }

    public Order findOrderByCode(String orderCode) {
        return null;
    }

    public List<Order> findAllOrder() {
        return null;
    }

    public long countOrder() {
        return 0;
    }

    public boolean existOrderByCode(String orderCode) {
        return false;
    }

//    public boolean existOrder(String orderId) {
//        try {
//            PreparedStatement stm = connection.prepareStatement("SELECT id FROM `order` WHERE id=?");
//            stm.setString(1, orderId);
//            return stm.executeQuery().next();
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Unable to find the order.");
//        }
//    }
//
//    public void saveOrder(String orderId, LocalDate orderDate, String customerId) throws FailedOperationException, DuplicateIdentifierException, NotFoundException {
//
//        try {
//
//            PreparedStatement stm = connection.prepareStatement("INSERT INTO `order` (id, date, customer_id) VALUES (?,?,?)");
//            stm.setString(1, orderId);
//            stm.setDate(2, Date.valueOf(orderDate));
//            stm.setString(3, customerId);
//            stm.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException("");
//        }
//    }
//
//    public void saveOrderDetail(String orderId, OrderDetailDTO orderDetail) {
//        try {
//            PreparedStatement stm = connection.prepareStatement("INSERT INTO order_detail (order_id, item_code, unit_price, qty) VALUES (?,?,?,?)");
//            stm.setString(1, orderId);
//            stm.setString(2, orderDetail.getItemCode());
//            stm.setBigDecimal(3, orderDetail.getUnitPrice());
//            stm.setInt(4, orderDetail.getQty());
//            stm .executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to save the order details");
//        }
//    }

}