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

package net.lax1dude.eaglercraft.internal.wasm_gc_teavm;

import java.io.InputStream;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;
import org.teavm.jso.core.JSString;
import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.Uint8Array;

import net.lax1dude.eaglercraft.internal.IWebSocketFrame;
import net.lax1dude.eaglercraft.internal.buffer.WASMGCDirectArrayConverter;
import net.lax1dude.eaglercraft.EaglerInputStream;

public class WASMGCWebSocketFrame implements IWebSocketFrame {

	public interface JSWebSocketFrame extends JSObject {

		@JSProperty
		int getType();

		@JSProperty("data")
		ArrayBuffer getDataBinary();

		@JSProperty("data")
		JSString getDataJSString();

		@JSProperty
		double getTimestamp();

	}

	private final JSWebSocketFrame handle;
	private final boolean isStr;
	private int cachedLength = -1;
	private String cachedString = null;
	private byte[] cachedByteArray = null;

	public WASMGCWebSocketFrame(JSWebSocketFrame handle) {
		this.handle = handle;
		this.isStr = handle.getType() == 0;
	}

	@Override
	public boolean isString() {
		return isStr;
	}

	@Override
	public String getString() {
		if(cachedString == null) {
			cachedString = getString0();
		}
		return cachedString;
	}

	private String getString0() {
		if(!isStr) return null;
		return BetterJSStringConverter.stringFromJS(handle.getDataJSString());
	}

	@Override
	public byte[] getByteArray() {
		if(cachedByteArray == null) {
			cachedByteArray = getByteArray0();
		}
		return cachedByteArray;
	}

	private byte[] getByteArray0() {
		if(isStr) return null;
		return WASMGCDirectArrayConverter.externU8ArrayToByteArray(new Uint8Array(handle.getDataBinary()));
	}

	@Override
	public InputStream getInputStream() {
		return new EaglerInputStream(getByteArray());
	}

	@Override
	public int getLength() {
		if(cachedLength == -1) {
			cachedLength = getLength0();
		}
		return cachedLength;
	}

	private int getLength0() {
		if(isStr) {
			return handle.getDataJSString().getLength();
		}else {
			return handle.getDataBinary().getByteLength();
		}
	}

	@Override
	public long getTimestamp() {
		return (long)handle.getTimestamp();
	}

}