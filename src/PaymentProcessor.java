import java.util.HashMap;
import java.util.Map;

public class PaymentProcessor {
    private final Map<String, Integer> balances = new HashMap<>();

    public PaymentProcessor() {
        balances.put("Alice", 3000);
        balances.put("Bob", 500);
    }

    public void charge(String customer, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment must be positive.");
        }
        int balance = getBalance(customer);
        if (amount > balance) {
            throw new IllegalStateException("Payment declined: insufficient balance.");
        }
        balances.put(customer, balance - amount);
    }

    public void refund(String customer, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Refund must be positive.");
        }
        balances.put(customer, getBalance(customer) + amount);
    }

    public int getBalance(String customer) {
        if (!balances.containsKey(customer)) {
            throw new IllegalArgumentException("Unknown customer: " + customer);
        }
        return balances.get(customer);
    }
}
