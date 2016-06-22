/**
 * 
 */
package xkrajnan.trading.poloniex.tradehistory;

import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author xkrajnan
 *
 */
public class Trade
{
	public static enum TradeType {
		buy, sell
	}

	private final int id;
	private final double price;
	private final double amount;
	private final double total;
	private final TradeType type;
	private final Date date;

	public Trade(int id, double price, double amount, double total, TradeType type, Date date)
	{
		this.id = id;
		this.price = price;
		this.amount = amount;
		this.total = total;
		this.type = type;
		this.date = date;
	}

	public Trade(JsonNode data)
	{
		id = data.get("data").get("amount").asInt();
		price = data.get("data").get("rate").asDouble();
		amount = data.get("data").get("amount").asDouble();
		total = data.get("data").get("total").asDouble();
		type = TradeType.valueOf(data.get("data").get("type").asText());
		// date = new Date(data.get("data").get("date").asText());
		date = null;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("new trade: ");
		builder.append("id=").append(id).append(", ");
		builder.append("date=").append(date).append(", ");
		builder.append("type=").append(type).append(", ");
		builder.append("price=").append(price).append(", ");
		builder.append("amount=").append(amount).append(", ");
		builder.append("total=").append(total);

		return builder.toString();
	}

	public int getId()
	{
		return id;
	}

	public double getPrice()
	{
		return price;
	}

	public double getAmount()
	{
		return amount;
	}

	public double getTotal()
	{
		return total;
	}

	public TradeType getType()
	{
		return type;
	}

	public Date getDate()
	{
		return date;
	}

}
