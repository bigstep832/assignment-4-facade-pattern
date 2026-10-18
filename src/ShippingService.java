import java.util.HashMap;
import java.util.Map;

public class ShippingService {
    private final Map<String, String> shipments = new HashMap<>();
    private int nextShipmentNumber = 1;

    public String createShipment(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Shipping address is required.");
        }
        String shipmentId = "SHIP-" + nextShipmentNumber++;
        shipments.put(shipmentId, address);
        return shipmentId;
    }

    public String getAddress(String shipmentId) {
        return shipments.get(shipmentId);
    }
}
