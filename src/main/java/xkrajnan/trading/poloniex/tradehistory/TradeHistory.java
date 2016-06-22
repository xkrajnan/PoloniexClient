/**
 * 
 */
package xkrajnan.trading.poloniex.tradehistory;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xkrajnan
 *
 */
public class TradeHistory
{

	private final ConcurrentLinkedQueue<Trade> trades;

	public TradeHistory()
	{
		this.trades = new ConcurrentLinkedQueue<>();
	}

	public boolean add(Trade trade)
	{
		return trades.add(trade);
	}

	public Trade remove()
	{
		return trades.remove();
	}

	@Override
	public String toString()
	{
		return trades.toString();
	}

}
