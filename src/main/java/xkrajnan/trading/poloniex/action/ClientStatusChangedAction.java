package xkrajnan.trading.poloniex.action;

import rx.functions.Action1;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClient.ConnectedState;
import ws.wamp.jawampa.WampClient.ConnectingState;
import ws.wamp.jawampa.WampClient.DisconnectedState;
import ws.wamp.jawampa.WampClient.State;
import xkrajnan.trading.poloniex.currency.CurrencyPair;
import xkrajnan.trading.poloniex.orderbook.OrderBook;
import xkrajnan.trading.poloniex.orderbook.OrderType;

public class ClientStatusChangedAction implements Action1<State>
{
	private final WampClient client;
	private final OrderBook orderBookAsk;
	private final OrderBook orderBookBid;

	public ClientStatusChangedAction(WampClient client, OrderBook orderBookAsk, OrderBook orderBookBid)
	{
		this.client = client;
		this.orderBookAsk = orderBookAsk;
		this.orderBookBid = orderBookBid;
	}

	@Override
	public void call(State status)
	{
		System.err.println("Status: " + status);

		if (status instanceof ConnectedState) {
			subscribeForUpdates();

		} else if (status instanceof ConnectingState) {

		} else if (status instanceof DisconnectedState) {

		} else {
			System.err.println("Invalid client state!");
		}
	}

	private void subscribeForUpdates()
	{
		client.makeSubscription("ticker").subscribe(new PrintTickerDataAction());
		client.makeSubscription(CurrencyPair.BTC_ETH.getCode())
				.subscribe(new OrderBookUpdateAction(orderBookAsk, OrderType.ASK));
		client.makeSubscription(CurrencyPair.BTC_ETH.getCode())
				.subscribe(new OrderBookUpdateAction(orderBookBid, OrderType.BID));
		client.makeSubscription(CurrencyPair.BTC_ETH.getCode()).subscribe(new TradeHistoryUpdateAction());
	}
}