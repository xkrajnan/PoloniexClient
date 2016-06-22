/**
 * 
 */
package xkrajnan.trading.poloniex;

import com.fasterxml.jackson.databind.JsonNode;

import xkrajnan.trading.poloniex.orderbook.Order;
import xkrajnan.trading.poloniex.orderbook.Order.OrderType;

/**
 * @author xkrajnan
 *
 */
public class OrderBookRemove
{

	public static final String TYPE_ORDER_BOOK_REMOVE = "orderBookRemove";

	private final double rate;
	private final Order.OrderType type;

	public OrderBookRemove(JsonNode data)
	{
		rate = data.get("data").get("rate").asDouble();
		type = Order.OrderType.valueOf(data.get("data").get("type").asText());
	}

	@Override
	public String toString()
	{
		return "ask: type= " + type + ", rate=" + String.valueOf(rate);
	}

	public double getRate()
	{
		return rate;
	}

}
