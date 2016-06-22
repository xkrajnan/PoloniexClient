package xkrajnan.trading.poloniex.orderbook;

public class Order
{

	public static enum OrderType {
		ask, bid
	}

	private final OrderType type;
	private final double price;
	private final double amount;

	public Order(OrderType type, double price, double amount)
	{
		this.type = type;
		this.price = price;
		this.amount = amount;
	}

	public OrderType getType()
	{
		return type;
	}

	public double getPrice()
	{
		return price;
	}

	public double getAmount()
	{
		return amount;
	}

}