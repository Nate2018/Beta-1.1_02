package net.minecraft.src;

import net.peyton.eagler.minecraft.EntityConstructor;

public class MobSpawnerHell extends MobSpawnerBase {
	public MobSpawnerHell() {
		this.biomeMonsters = new EntityConstructor[]{EntityGhast::new, EntityPigZombie::new};
		this.biomeCreatures = new EntityConstructor[0];
	}
}
