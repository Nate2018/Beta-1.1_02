package net.minecraft.src;

import java.util.Comparator;

public class EntitySorter implements Comparator {
	private double field_30008_a;
	private double field_30007_b;
	private double field_30009_c;

	public EntitySorter(Entity var1) {
		this.field_30008_a = -var1.posX;
		this.field_30007_b = -var1.posY;
		this.field_30009_c = -var1.posZ;
	}

	public int sortByDistanceToEntity(WorldRenderer var1, WorldRenderer var2) {
		double var3 = (double)var1.field_1746_q + this.field_30008_a;
		double var5 = (double)var1.field_1743_r + this.field_30007_b;
		double var7 = (double)var1.field_1741_s + this.field_30009_c;
		double var9 = (double)var2.field_1746_q + this.field_30008_a;
		double var11 = (double)var2.field_1743_r + this.field_30007_b;
		double var13 = (double)var2.field_1741_s + this.field_30009_c;
		return (int)((var3 * var3 + var5 * var5 + var7 * var7 - (var9 * var9 + var11 * var11 + var13 * var13)) * 1024.0D);
	}

	public int compare(Object var1, Object var2) {
		return this.sortByDistanceToEntity((WorldRenderer)var1, (WorldRenderer)var2);
	}
}
