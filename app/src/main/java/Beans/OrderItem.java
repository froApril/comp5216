package Beans;

public class OrderItem {
    private String tableID;
    private String orderItem;
    private String orderTime;
    private double totalPrice;

    public String getTableID() {
        return tableID;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
