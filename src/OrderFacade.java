public class OrderFacade {
    private final InventoryService inventory = new InventoryService(3, 1000);
    private final PaymentProcessor payment = new PaymentProcessor();
    private final ShippingService shipping = new ShippingService();
    private final NotificationService notification = new NotificationService();

    public String placeOrder(int quantity, String customer, String address) {
        if (customer == null || customer.isBlank()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
        int total = inventory.reserve(quantity);
        boolean paid = false;
        try {
            payment.charge(customer, total);
            paid = true;
            String shipmentId = shipping.createShipment(address);
            return notification.confirm(customer, shipmentId, total);
        } catch (IllegalArgumentException | IllegalStateException error) {
            if (paid) {
                payment.refund(customer, total);
            }
            inventory.release(quantity);
            throw error;
        }
    }
}
