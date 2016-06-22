package xkrajnan.trading.poloniex.orderbook;

public enum OrderType {

	ASK("ask"), BID("bid");

	private final String code;

	private OrderType(String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}

}