public class OrderFacadeTest {
    private static final String ADDRESS = "10 Main Street";
    private static int passed;

    public static void main(String[] args) {
        successConsumesStock();
        failureLeavesShopUnchanged(0, "Alice", ADDRESS, IllegalArgumentException.class);
        failureLeavesShopUnchanged(-1, "Alice", ADDRESS, IllegalArgumentException.class);
        failureLeavesShopUnchanged(4, "Alice", ADDRESS, IllegalStateException.class);
        failureLeavesShopUnchanged(3, "Bob", ADDRESS, IllegalStateException.class);
        failureLeavesShopUnchanged(3, "Unknown", ADDRESS, IllegalArgumentException.class);
        failureLeavesShopUnchanged(3, "", ADDRESS, IllegalArgumentException.class);
        failureLeavesShopUnchanged(3, null, ADDRESS, IllegalArgumentException.class);
        failureLeavesShopUnchanged(3, "Alice", "   ", IllegalArgumentException.class);
        failureLeavesShopUnchanged(3, "Alice", null, IllegalArgumentException.class);
        System.out.println("All " + passed + " tests passed.");
    }

    private static void successConsumesStock() {
        OrderFacade shop = new OrderFacade();
        assertConfirmation(shop.placeOrder(3, "Alice", ADDRESS));
        expectFailure(() -> shop.placeOrder(1, "Alice", ADDRESS),
                IllegalStateException.class, "Not enough Java Books in stock.");
        passed++;
    }

    private static void failureLeavesShopUnchanged(int quantity, String customer,
                                                  String address,
                                                  Class<? extends RuntimeException> type) {
        OrderFacade shop = new OrderFacade();
        expectFailure(() -> shop.placeOrder(quantity, customer, address), type, null);
        // All three books and Alice's full balance must still be available.
        assertConfirmation(shop.placeOrder(3, "Alice", ADDRESS));
        passed++;
    }

    private static void assertConfirmation(String actual) {
        String expected = "Order confirmed for Alice: SHIP-1, total 3000 KZT.";
        if (!expected.equals(actual)) {
            throw new AssertionError("Unexpected confirmation: " + actual);
        }
    }

    private static void expectFailure(Runnable action,
                                      Class<? extends RuntimeException> type,
                                      String expectedMessage) {
        try {
            action.run();
        } catch (RuntimeException error) {
            if (!type.isInstance(error)) {
                throw new AssertionError("Unexpected exception type.", error);
            }
            if (expectedMessage != null && !expectedMessage.equals(error.getMessage())) {
                throw new AssertionError("Unexpected exception message.", error);
            }
            return;
        }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
}
