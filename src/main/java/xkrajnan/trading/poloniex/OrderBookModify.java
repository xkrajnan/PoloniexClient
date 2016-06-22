/**
 * 
 */
package xkrajnan.trading.poloniex;

import com.fasterxml.jackson.databind.JsonNode;

import xkrajnan.trading.poloniex.orderbook.Order;

/**
 * @author xkrajnan
 *
 */
public class OrderBookModify
{
	public static final String TYPE_ORDER_BOOK_MODIFY = "orderBookModify";

	private final double rate;
	private final double amount;
	private final Order.OrderType type;

	public OrderBookModify(JsonNode data)
	{
		rate = data.get("data").get("rate").asDouble();
		amount = data.get("data").get("amount").asDouble();
		type = Order.OrderType.valueOf(data.get("data").get("type").asText());
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("bid: ");
		builder.append("type=").append(type).append(", ");
		builder.append("rate=").append(rate).append(", ");
		builder.append("amount=").append(amount);

		return builder.toString();
	}

	public double getRate()
	{
		return rate;
	}

	public double getAmount()
	{
		return amount;
	}

}
