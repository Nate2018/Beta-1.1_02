package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.lax1dude.eaglercraft.EaglerOutputStream;
import net.lax1dude.eaglercraft.internal.IWebSocketClient;
import net.lax1dude.eaglercraft.internal.IWebSocketFrame;
import net.lax1dude.eaglercraft.v1_8.netty.ByteBuf;
import net.lax1dude.eaglercraft.v1_8.netty.Unpooled;
import net.peyton.eagler.netty.NettyUtils;

public class NetworkManager {
	private boolean isRunning = true;
	private NetHandler netHandler;
	private List<Packet> readPackets = Collections.synchronizedList(new ArrayList());
	private boolean isServerTerminating = false;
	private boolean isTerminating = false;
	private String terminationReason = "";
	private Object[] field_20101_t;
	private int timeSinceLastRead = 0;
	private int sendQueueByteLength = 0;
	public int chunkDataSendCounter = 0;
	private int field_20100_w = 0;
	
	public IWebSocketClient webSocket;

	public NetworkManager(NetHandler var3) {
		this.netHandler = var3;
	}
	
	public void setWebsocketClient(IWebSocketClient client) {
		this.webSocket = client;
	}
	
	private EaglerOutputStream sendBuffer = new EaglerOutputStream();

	public void addToSendQueue(Packet var1) {
		if(!this.isServerTerminating) {
			sendBuffer.reset();
			try (DataOutputStream dos = new DataOutputStream(sendBuffer)) {
				Packet.writePacket(var1, dos);
				webSocket.send(sendBuffer.toByteArray());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void onNetworkError(Exception var1) {
		var1.printStackTrace();
		this.networkShutdown("disconnect.genericReason", new Object[]{"Internal exception: " + var1.toString()});
	}

	public void networkShutdown(String var1, Object... var2) {
		if(this.isRunning) {
			this.isTerminating = true;
			this.terminationReason = var1;
			this.field_20101_t = var2;
			this.isRunning = false;
			webSocket.close();
			webSocket = null;
		}
	}
	
	private static class ByteBufferDirectInputStream extends InputStream {
		private ByteBuffer buf;
		private ByteBufferDirectInputStream(ByteBuffer b) {
			this.buf = b;
		}
		
		@Override
		public int read() throws IOException {
			return buf.remaining() > 0 ? ((int)buf.get() & 0xFF) : -1;
		}
		
		@Override
		public int available() {
			return buf.remaining();
		}
	}
	
	public void processReadPackets() {
		IWebSocketFrame frame;
		while((frame = webSocket.getNextBinaryFrame()) != null) {
			byte[] arr = frame.getByteArray();
			if(arr != null) {
				DataInputStream packetStream = new DataInputStream(new ByteBufferDirectInputStream(ByteBuffer.wrap(arr)));
				try {
					Packet pkt = Packet.readPacket(packetStream);
					pkt.processPacket(this.netHandler);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static boolean isRunning(NetworkManager var0) {
		return var0.isRunning;
	}

	static boolean isServerTerminating(NetworkManager var0) {
		return var0.isServerTerminating;
	}
}
