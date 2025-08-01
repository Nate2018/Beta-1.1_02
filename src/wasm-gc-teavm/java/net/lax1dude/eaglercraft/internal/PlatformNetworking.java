/*
 * Copyright (c) 2024 lax1dude. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package net.lax1dude.eaglercraft.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.teavm.interop.Import;
import org.teavm.jso.core.JSString;

import net.lax1dude.eaglercraft.internal.wasm_gc_teavm.BetterJSStringConverter;
import net.lax1dude.eaglercraft.internal.wasm_gc_teavm.WASMGCWebSocketClient;

public class PlatformNetworking {

	private static final Logger logger = LogManager.getLogger("PlatformNetworking");

	public static IWebSocketClient openWebSocket(String socketURI) {
		IWebSocketClient client = openWebSocketImpl(socketURI);
		if(client == null) {
			logger.error("Could not open WebSocket to \"{}\"!", socketURI);
		}
		return client;
	}

	public static IWebSocketClient openWebSocketUnsafe(String socketURI) {
		IWebSocketClient client = openWebSocketImpl(socketURI);
		if(client == null) {
			throw new IllegalArgumentException("Could not open WebSocket to \"" + socketURI + "\"!");
		}
		return client;
	}

	public static IWebSocketClient openWebSocketImpl(String socketURI) {
		WASMGCWebSocketClient.JSWebSocketClientHandle handle = createWebSocketHandle(
				BetterJSStringConverter.stringToJS(socketURI));
		if(handle != null) {
			return new WASMGCWebSocketClient(handle, socketURI);
		}else {
			return null;
		}
	}

	@Import(module = "platformNetworking", name = "createWebSocketHandle")
	private static native WASMGCWebSocketClient.JSWebSocketClientHandle createWebSocketHandle(JSString socketURI);

}