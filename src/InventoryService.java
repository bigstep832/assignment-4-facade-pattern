public class InventoryService {
    private int stock;
    private final int unitPrice;

    public InventoryService(int stock, int unitPrice) {
        if (stock < 0 || unitPrice <= 0) {
            throw new IllegalArgumentException("Invalid stock or unit price.");
        }
        this.stock = stock;
        this.unitPrice = unitPrice;
    }

    public int reserve(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (quantity > stock) {
            throw new IllegalStateException("Not enough Java Books in stock.");
        }
        int total = Math.multiplyExact(quantity, unitPrice);
        stock -= quantity;
        return total;
    }

    public void release(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        stock += quantity;
    }

    public int getStock() {
        return stock;
    }
}
