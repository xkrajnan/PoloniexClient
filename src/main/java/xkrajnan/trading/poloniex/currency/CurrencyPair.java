/**
 * 
 */
package xkrajnan.trading.poloniex.currency;

/**
 * @author xkrajnan
 *
 */
public enum CurrencyPair {

	BTC_ETH("BTC_ETH", Currency.BTC, Currency.ETH), BTC_XEM("BTC_XEM", Currency.BTC, Currency.XEM);

	private final String code;
	private final Currency currency1;
	private final Currency currency2;

	private CurrencyPair(String code, Currency currency1, Currency currency2)
	{
		this.code = code;
		this.currency1 = currency1;
		this.currency2 = currency2;
	}

	@Override
	public String toString()
	{
		return currency2.getCode() + "/" + currency1.getCode();
	}

	public String getCode()
	{
		return code;
	}

	public Currency getCurrency1()
	{
		return currency1;
	}

	public Currency getCurrency2()
	{
		return currency2;
	}

}
