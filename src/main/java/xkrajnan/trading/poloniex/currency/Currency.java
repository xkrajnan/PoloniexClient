/**
 * 
 */
package xkrajnan.trading.poloniex.currency;

/**
 * @author xkrajnan
 *
 */
public enum Currency {

	BTC("BTC", "Bitcoin"), ETH("ETH", "Ethereum"), XEM("XEM", "New Economy Foundation");

	private final String code;
	private final String name;

	private Currency(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return code;
	}

}
