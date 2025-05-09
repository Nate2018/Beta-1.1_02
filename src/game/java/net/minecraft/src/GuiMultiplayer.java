package net.minecraft.src;

import org.lwjgl.input.Keyboard;

import net.lax1dude.eaglercraft.profile.EaglerProfile;

public class GuiMultiplayer extends GuiScreen {
	private GuiScreen updateCounter;
	private int parentScreen = 0;
	private String serverAddress = "";
	private String username = "";
	
	private boolean serverTextBox;
	private boolean usernameTextBox;

	public GuiMultiplayer(GuiScreen var1) {
		this.updateCounter = var1;
	}

	public void updateScreen() {
		++this.parentScreen;
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.func_20162_a();
		Keyboard.enableRepeatEvents(true);
		if(this.mc.gameSettings.lastServer == null) {
			this.mc.gameSettings.lastServer = "";
		}
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.func_20163_a("multiplayer.connect")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.func_20163_a("gui.cancel")));
		this.serverAddress = this.mc.gameSettings.lastServer;
		if(this.serverAddress.contains("_")) {
			this.serverAddress = this.serverAddress.replaceAll("_", ":");
		}
		this.username = EaglerProfile.getName();
		if(this.username == null) {
			this.username = "";
		}
		((GuiButton)this.controlList.get(0)).enabled = (this.serverAddress.length() > 0 && this.username.length() > 0);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 1) {
				this.mc.displayGuiScreen(this.updateCounter);
			} else if(var1.id == 0) {
				this.mc.gameSettings.lastServer = this.serverAddress.replaceAll(":", "_");
				this.mc.gameSettings.saveOptions();
				EaglerProfile.setName(this.username);
				EaglerProfile.save();
				this.mc.displayGuiScreen(new GuiConnecting(this.mc, serverAddress));
			}

		}
	}
	
	protected void mouseClicked(int var1, int var2, int var3) {
		if(var3 == 0) {
			if(var1 >= this.width / 2 - 100 && var1 < (this.width / 2 - 100) + 200 && var2 >= this.height / 4 - 10 + 10 + 18 && var2 < (this.height / 4 - 10 + 10 + 18) + 20) {
				Keyboard.enableRepeatEvents(true);
				usernameTextBox = true;
				serverTextBox = false;
			} else if(var1 >= this.width / 2 - 100 && var1 < (this.width / 2 - 100) + 200 && var2 >= this.height / 4 - 10 + 50 + 18 && var2 < (this.height / 4 - 10 + 50 + 18) + 20) {
				Keyboard.enableRepeatEvents(true);
				serverTextBox = true;
				usernameTextBox = false;
			} else {
				Keyboard.enableRepeatEvents(false);
				usernameTextBox = false;
				serverTextBox = false;
			}
		}
		super.mouseClicked(var1, var2, var3);
	}

	protected void keyTyped(char var1, int var2) {
		if(this.serverTextBox) {
			if(var1 == 22) {
				String var3 = GuiScreen.getClipboardString();
				if(var3 == null) {
					var3 = "";
				}

				int var4 = 32 - this.serverAddress.length();
				if(var4 > var3.length()) {
					var4 = var3.length();
				}

				if(var4 > 0) {
					this.serverAddress = this.serverAddress + var3.substring(0, var4);
				}
			}

			if(var2 == 14 && this.serverAddress.length() > 0) {
				this.serverAddress = this.serverAddress.substring(0, this.serverAddress.length() - 1);
			}

			if(FontAllowedCharacters.field_20157_a.indexOf(var1) >= 0 && this.serverAddress.length() < 32) {
				this.serverAddress = this.serverAddress + var1;
			}
		} else if(this.usernameTextBox) {
			if(var1 == 22) {
				String var3 = GuiScreen.getClipboardString();
				if(var3 == null) {
					var3 = "";
				}
				
				int var4 = 16 - this.username.length();
				if(var4 > var3.length()) {
					var4 = var3.length();
				}
				
				if(var4 > 0) {
					this.username = this.username + var3.substring(0, var4);
				}
			}
			
			if(var2 == 14 && this.username.length() > 0) {
				this.username = this.username.substring(0, this.username.length() - 1);
			}
			
			if(FontAllowedCharacters.field_20157_a.indexOf(var1) >= 0 && this.username.length() < 16) {
				this.username = this.username + var1;
			}
		}
		
		System.out.println(var1);
		System.out.println(var2);
		if(var2 == 28) {
			this.actionPerformed((GuiButton)this.controlList.get(0));
		}

		((GuiButton)this.controlList.get(0)).enabled = this.serverAddress.length() > 0 && this.username.length() > 0;
	}

	public void drawScreen(int var1, int var2, float var3) {
		StringTranslate var4 = StringTranslate.func_20162_a();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, var4.func_20163_a("multiplayer.title"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
		this.drawString(this.fontRenderer, var4.func_20163_a("multiplayer.info1"), this.width / 2 - 140, this.height / 4 - 60 + 60 + 0, 10526880);
		this.drawString(this.fontRenderer, var4.func_20163_a("multiplayer.info2"), this.width / 2 - 140, this.height / 4 - 60 + 60 + 9, 10526880);
		this.drawString(this.fontRenderer, "Server IP:", this.width / 2 - 100, this.height / 4 - 50 + 60 + 36, 10526880);
		this.drawString(this.fontRenderer, "Username:", this.width / 2 - 100, this.height / 4 - 90 + 60 + 36, 10526880);
		int var15 = this.width / 2 - 100;
		int var5 = this.height / 4 - 10 + 50 + 18;
		short var6 = 200;
		byte var7 = 20;
		this.drawRect(var15 - 1, var5 - 1, var15 + var6 + 1, var5 + var7 + 1, -6250336);
		this.drawRect(var15, var5, var15 + var6, var5 + var7, -16777216);
		if(this.serverTextBox) {
			this.drawString(this.fontRenderer, this.serverAddress + (this.parentScreen / 6 % 2 == 0 ? "_" : ""), var15 + 4, var5 + (var7 - 8) / 2, 14737632);
		} else {
			this.drawString(this.fontRenderer, this.serverAddress, var15 + 4, var5 + (var7 - 8) / 2, 14737632);
		}
		
		int var8 = this.width / 2 - 100;
		int var9 = this.height / 4 - 10 + 10 + 18;
		short var10 = 200;
		byte var11 = 20;
		this.drawRect(var8 - 1, var9 - 1, var8 + var10 + 1, var9 + var11 + 1, -6250336);
		this.drawRect(var8, var9, var8 + var10, var9 + var11, -16777216);
		if(this.usernameTextBox) {
			this.drawString(this.fontRenderer, this.username + (this.parentScreen / 6 % 2 == 0 ? "_" : ""), var8 + 4, var9 + (var11 - 8) / 2, 14737632);
		} else {
			this.drawString(this.fontRenderer, this.username, var8 + 4, var9 + (var11 - 8) / 2, 14737632);
		}
		super.drawScreen(var1, var2, var3);
	}
}
