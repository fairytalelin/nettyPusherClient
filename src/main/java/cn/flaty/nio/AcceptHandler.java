package cn.flaty.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcceptHandler {
	
	private Logger log = LoggerFactory.getLogger(AcceptHandler.class);


	private Selector selector;

	private SocketChannel channel;


	public void connect(Selector s, SelectionKey key) throws IOException {
		this.selector = s;
		this.channel = (SocketChannel) key.channel();

		// 如果正在连接，则完成连接
		if (channel.isConnectionPending()) {
			channel.finishConnect();
		}

		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_READ);
		// channel.register(selector, SelectionKey.OP_WRITE);
		log.info("----> 连接建立成功");

	}


	public static interface AfterAcceptListener {
		void success();
		void fail();
	}

}
