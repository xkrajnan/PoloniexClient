package xkrajnan.trading.poloniex;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import ws.wamp.jawampa.EventDetails;
import ws.wamp.jawampa.PubSubData;
import ws.wamp.jawampa.SubscriptionFlags;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.WampError;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		PoloniexClient poloniex = new PoloniexClient();
		poloniex.open();

		for (;;) {

		}
	}

	public void pokus() throws Exception
	{
		final WampClient client;

		try {
			NettyWampClientConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();
			WampClientBuilder builder = new WampClientBuilder();
			builder.withConnectorProvider(connectorProvider).withUri("wss://api.poloniex.com").withRealm("realm1")
					.withInfiniteReconnects().withReconnectInterval(5, TimeUnit.SECONDS);

			client = builder.build();
			client.open();

			client.statusChanged().subscribe((s) -> {
				System.out.println("s: " + s);

				Observable<PubSubData> tickerSubscription = client.makeSubscription("ticker");
				Action1<PubSubData> tickerAction = new Action1<PubSubData>() {
					@Override
					public void call(PubSubData tickerData)
					{
						System.out.println("ticker: " + tickerData.arguments());
					}
				};
				tickerSubscription.subscribe(tickerAction, (e) -> {
					System.err.println("ticker-e: " + e);
				});

				Observable<EventDetails<String>> trollboxSubscription = client.makeSubscriptionWithDetails("trollbox",
						SubscriptionFlags.Wildcard, String.class);
				trollboxSubscription.subscribe((t) -> {
					System.out.println("trollbox: " + t.message());
				}, (e) -> {
					System.err.println("trollbox-e: " + e);
				});

			}, (e) -> {
				System.err.println("e: " + e);
			});

			for (;;) {

			}

		} catch (WampError e) {
			System.err.println(e);
		}
	}
}
