/**
 * 
 */
package xkrajnan.trading.poloniex;

import com.fasterxml.jackson.databind.JsonNode;

import ws.wamp.jawampa.PubSubData;

/**
 * @author xkrajnan
 *
 */
public class OrderBookModify
{

	private static final String TYPE_ORDER_BOOK_MODIFY = "orderBookModify";

	private final double rate;
	private final double amount;

	public OrderBookModify(PubSubData orderData)
	{
		JsonNode data = orderData.arguments().get(0);

		if (!data.get("type").asText().equals(TYPE_ORDER_BOOK_MODIFY)) {
			throw new IllegalArgumentException("Invalid data type: " + data.get("type").asText());
		}

		rate = data.get("data").get("rate").asDouble();
		amount = data.get("data").get("amount").asDouble();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("bid: ");
		builder.append("rate=").append(rate).append(", ");
		builder.append("amount=").append(amount);

		return builder.toString();
	}

	public double getRate()
	{
		return rate;
	}

}
