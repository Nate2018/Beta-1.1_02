package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;

public class GLAllocation {
	private static List<Integer> textureNames = new ArrayList<Integer>();

	public static int generateDisplayLists(int count) {
		return GL11.glGenLists(count);
	}
	
	public static void deleteDisplayLists(int list) {
		GL11.glDeleteLists(list);
	}

	public static void generateTextureNames(IntBuffer var0) {
		GL11.glGenTextures(var0);

		for(int var1 = var0.position(); var1 < var0.limit(); ++var1) {
			textureNames.add(Integer.valueOf(var0.get(var1)));
		}

	}

	public static void deleteTexturesAndDisplayLists() {
		IntBuffer var2 = createDirectIntBuffer(textureNames.size());
		var2.flip();
		GL11.glDeleteTextures(var2);

		for(int var1 = 0; var1 < textureNames.size(); ++var1) {
			var2.put(((Integer)textureNames.get(var1)).intValue());
		}

		var2.flip();
		GL11.glDeleteTextures(var2);
		textureNames.clear();
	}

	public static ByteBuffer createDirectByteBuffer(int var0) {
		return EagRuntime.allocateByteBuffer(var0);
	}

	public static IntBuffer createDirectIntBuffer(int var0) {
		return createDirectByteBuffer(var0 << 2).asIntBuffer();
	}

	public static FloatBuffer createDirectFloatBuffer(int var0) {
		return createDirectByteBuffer(var0 << 2).asFloatBuffer();
	}
}
