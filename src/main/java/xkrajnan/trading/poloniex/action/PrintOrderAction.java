package xkrajnan.trading.poloniex.action;

import com.fasterxml.jackson.databind.JsonNode;

import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;
import xkrajnan.trading.poloniex.OrderBookModify;
import xkrajnan.trading.poloniex.OrderBookRemove;
import xkrajnan.trading.poloniex.orderbook.OrderBook;
import xkrajnan.trading.poloniex.tradehistory.Trade;

public class PrintOrderAction implements Action1<PubSubData>
{
	private final OrderBook orderBook;

	public PrintOrderAction(OrderBook orderBook)
	{
		this.orderBook = orderBook;
	}

	@Override
	public void call(PubSubData data)
	{
		try {
			for (JsonNode node : data.arguments()) {
				String type = node.get("type").asText();
				if (type.equals(OrderBookModify.TYPE_ORDER_BOOK_MODIFY)) {
					OrderBookModify orderBookData = new OrderBookModify(node);
					System.out.println("modify " + orderBookData);
					orderBook.put(orderBookData.getRate(), orderBookData.getAmount());
				} else if (type.equals(OrderBookRemove.TYPE_ORDER_BOOK_REMOVE)) {
					OrderBookRemove orderBookData = new OrderBookRemove(node);
					System.out.println("remove " + orderBookData);
					orderBook.remove(orderBookData.getRate());
				} else {
					Trade trade = new Trade(node);
					System.out.println(trade);
					System.out.println(orderBook);
					System.out.println(orderBook.getMinPrice());
					System.out.println(orderBook.getTotalVolume());
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}