public class Main {
    public static void main(String[] args) {
        OrderFacade shop = new OrderFacade();

        demonstrate(shop, "1. Successful order", 1, "Alice", "10 Main Street");
        demonstrate(shop, "2. Payment declined", 2, "Bob", "20 Main Street");
        demonstrate(shop, "3. Shipping failed", 2, "Alice", "");
        demonstrate(shop, "4. Order after rollback", 2, "Alice", "10 Main Street");
        demonstrate(shop, "5. Stock exhausted", 1, "Alice", "10 Main Street");
    }

    private static void demonstrate(OrderFacade shop, String title,
                                    int quantity, String customer, String address) {
        System.out.println(title);
        try {
            System.out.println(shop.placeOrder(quantity, customer, address));
        } catch (IllegalArgumentException | IllegalStateException error) {
            System.out.println("Order failed: " + error.getMessage());
        }
        System.out.println();
    }
}
