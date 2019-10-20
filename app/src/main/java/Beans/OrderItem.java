package Beans;

public class OrderItem {
    private String tableID;
    private String orderItem;
    private String itemNumber;
    private String orderTime;
    private double totalPrice;

    public String getTableID() {
        return tableID;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
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
