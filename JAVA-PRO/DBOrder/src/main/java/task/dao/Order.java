package task.dao;

public class Order extends AbstractDao {
    private int clientsId;
    private int productId;
    private int amount;

    public Order() {
    }

    public Order(int clientsId, int productId, int amount) {
        this.clientsId = clientsId;
        this.productId = productId;
        this.amount = amount;
    }

    public int getClientsId() {
        return clientsId;
    }

    public void setClientsId(int clientsId) {
        this.clientsId = clientsId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                super.toString() +
                ", clientsId=" + clientsId +
                ", product='" + productId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
