/**
 * 
 */
package xkrajnan.trading.poloniex.orderbook;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xkrajnan
 *
 */
public class OrderBook
{

	private final Map<Double, Double> orders;

	public OrderBook()
	{
		this.orders = new ConcurrentHashMap<>();
	}

	public void put(double price, double amount)
	{
		orders.put(price, amount);
	}

	public double get(double price)
	{
		return orders.get(price);
	}

	public Double remove(double price)
	{
		return orders.remove(price);
	}

	public double getMinPrice()
	{
		return Collections.min(orders.keySet());
	}

	public double getMaxPrice()
	{
		return Collections.max(orders.keySet());
	}

	public double getTotalVolume()
	{
		return orders.values().stream().mapToDouble(Double::doubleValue).sum();
	}

	@Override
	public String toString()
	{
		return orders.toString();
	}

}
