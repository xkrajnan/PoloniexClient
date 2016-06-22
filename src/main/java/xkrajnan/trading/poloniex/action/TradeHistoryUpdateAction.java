package xkrajnan.trading.poloniex.action;

import com.fasterxml.jackson.databind.JsonNode;

import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;
import xkrajnan.trading.poloniex.tradehistory.Trade;

public class TradeHistoryUpdateAction implements Action1<PubSubData>
{
	private static final String ID_DATA = "data";
	private static final String ID_UPDATE_TYPE = "type";
	private static final String ID_NEW_TRADE = "newTrade";

	public TradeHistoryUpdateAction()
	{
	}

	@Override
	public void call(PubSubData data)
	{
		try {
			for (JsonNode node : data.arguments()) {

				String updateType = node.get(ID_UPDATE_TYPE).asText();

				// skip updates that do not concern trade history
				if (!updateType.equals(ID_NEW_TRADE)) {
					continue;
				}

				JsonNode tradeData = node.get(ID_DATA);

				Trade trade = new Trade(tradeData);
				System.out.println(trade);
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}