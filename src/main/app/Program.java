package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {
    public static void main(String[] args) throws SQLException {
        Connection conn = DB.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from tb_order "
            + "INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id "
            + "INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");

        Map<Long, Order> orders = new HashMap<>();
        Map<Long, Product> products = new HashMap<>();

        while (rs.next()) {
            Long orderId = rs.getLong("order_id");
            if (orders.get("orderId") == null) {
                Order order = instantiateOrder(rs);
                orders.put(orderId, order);
            }

            Long productId = rs.getLong("product_id");
            if (products.get("productId") == null) {
                Product product = instantiateProduct(rs);
                products.put(productId, product);
            }

            orders.get(orderId).getProducts().add(products.get(productId));
        }

        for (Long orderId : orders.keySet()) {
            System.out.print("\n");
            System.out.println(orders.get(orderId));
            for (Product p : orders.get(orderId).getProducts()) {
                System.out.println(p);
            }
            System.out.print("\n------------------------------------------------------------------------------------" +
                    "---------------------------------------------------------------------\n");
        }

    }

    private static Product instantiateProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("order_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getString("image_uri"));
    }

    private static Order instantiateOrder(ResultSet rs) throws SQLException {
        return new Order(rs.getLong("product_id"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude"),
                rs.getTimestamp("moment").toInstant(),
                OrderStatus.values()[rs.getInt("status")]
                );
    }

}
