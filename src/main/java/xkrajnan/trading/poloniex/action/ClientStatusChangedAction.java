package xkrajnan.trading.poloniex.action;

import rx.functions.Action1;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClient.ConnectedState;
import ws.wamp.jawampa.WampClient.ConnectingState;
import ws.wamp.jawampa.WampClient.DisconnectedState;
import ws.wamp.jawampa.WampClient.State;
import xkrajnan.trading.poloniex.currency.CurrencyPair;
import xkrajnan.trading.poloniex.orderbook.OrderBook;

public class ClientStatusChangedAction implements Action1<State>
{
	private final WampClient client;
	private final OrderBook orderBook;

	public ClientStatusChangedAction(WampClient client, OrderBook orderBook)
	{
		this.client = client;
		this.orderBook = orderBook;
	}

	@Override
	public void call(State status)
	{
		System.err.println("Status: " + status);

		if (status instanceof ConnectedState) {
			client.makeSubscription("ticker").subscribe(new PrintTickerDataAction());
			client.makeSubscription(CurrencyPair.BTC_ETH.getCode())
					.subscribe(new PrintOrderAction(orderBook));

		} else if (status instanceof ConnectingState) {

		} else if (status instanceof DisconnectedState) {

		} else {
			System.err.println("Invalid client state!");
		}
	}
}