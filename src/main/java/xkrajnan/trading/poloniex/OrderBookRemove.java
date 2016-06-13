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
public class OrderBookRemove
{

	private static final String TYPE_ORDER_BOOK_REMOVE = "orderBookRemove";

	private final double rate;

	public OrderBookRemove(PubSubData orderData)
	{
		JsonNode data = orderData.arguments().get(0);

		if (!data.get("type").asText().equals(TYPE_ORDER_BOOK_REMOVE)) {
			throw new IllegalArgumentException("Invalid data type: " + data.get("type").asText());
		}

		rate = data.get("data").get("rate").asDouble();
	}

	@Override
	public String toString()
	{
		return "ask: rate=" + String.valueOf(rate);
	}

	public double getRate()
	{
		return rate;
	}

}
