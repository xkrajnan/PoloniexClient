/**
 * 
 */
package xkrajnan.trading.poloniex.ticker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import ws.wamp.jawampa.PubSubData;
import xkrajnan.trading.poloniex.currency.CurrencyPair;

/**
 * @author xkrajnan
 *
 */
public class TickerRecord
{
	public static enum TickerField {
		CURRENCY_PAIR, LAST, LOWEST_ASK, HIGHEST_BID, PERCENT_CHANGE, BASE_VOLUME, QUOTE_VOLUME, IS_FROZEN, HR24_HIGH, HR24_LOW
	}

	private final CurrencyPair currencyPair;

	private final double last;
	private final double lowestAsk;
	private final double highestBid;
	private final double percentChange;
	private final double baseVolume;
	private final double quoteVolume;
	private final boolean isFrozen;
	private final double hr24high;
	private final double hr24low;

	public TickerRecord(PubSubData tickerData)
	{
		ArrayNode arguments = tickerData.arguments();

		// there are no field names returned in data
		this.currencyPair = CurrencyPair.valueOf(getField(arguments, TickerField.CURRENCY_PAIR).asText());
		this.last = getField(arguments, TickerField.LAST).asDouble();
		this.lowestAsk = getField(arguments, TickerField.LOWEST_ASK).asDouble();
		this.highestBid = getField(arguments, TickerField.HIGHEST_BID).asDouble();
		this.percentChange = getField(arguments, TickerField.PERCENT_CHANGE).asDouble();
		this.baseVolume = getField(arguments, TickerField.BASE_VOLUME).asDouble();
		this.quoteVolume = getField(arguments, TickerField.QUOTE_VOLUME).asDouble();
		this.isFrozen = getField(arguments, TickerField.IS_FROZEN).asBoolean();
		this.hr24high = getField(arguments, TickerField.HR24_HIGH).asDouble();
		this.hr24low = getField(arguments, TickerField.HR24_LOW).asDouble();
	}

	private JsonNode getField(ArrayNode data, TickerField field)
	{
		return data.get(field.ordinal());
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append(currencyPair).append(": ");
		builder.append("last=").append(last).append(", ");
		builder.append("lowestAsk=").append(lowestAsk).append(", ");
		builder.append("highestBid=").append(highestBid).append(", ");
		builder.append("percentChange=").append(percentChange).append(", ");
		builder.append("baseVolume=").append(baseVolume).append(", ");
		builder.append("quoteVolume=").append(quoteVolume).append(", ");
		builder.append("isFrozen=").append(isFrozen).append(", ");
		builder.append("hr24high=").append(hr24high).append(", ");
		builder.append("hr24low=").append(hr24low);

		return builder.toString();
	}

	public CurrencyPair getCurrencyPair()
	{
		return currencyPair;
	}

	public double getLast()
	{
		return last;
	}

	public double getLowestAsk()
	{
		return lowestAsk;
	}

	public double getHighestBid()
	{
		return highestBid;
	}

	public double getPercentChange()
	{
		return percentChange;
	}

	public double getBaseVolume()
	{
		return baseVolume;
	}

	public double getQuoteVolume()
	{
		return quoteVolume;
	}

	public boolean isFrozen()
	{
		return isFrozen;
	}

	public double getHr24high()
	{
		return hr24high;
	}

	public double getHr24low()
	{
		return hr24low;
	}

}
