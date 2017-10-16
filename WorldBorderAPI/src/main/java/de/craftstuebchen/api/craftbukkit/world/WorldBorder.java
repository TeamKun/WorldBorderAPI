package de.craftstuebchen.api.craftbukkit.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

public class WorldBorder implements IWorldBorder{

	private net.minecraft.server.v1_8_R3.WorldBorder	border;

	public WorldBorder(World bukkitWorld) {
			this.border =((CraftWorld) bukkitWorld).getHandle().getWorldBorder();
	}
	
	public WorldBorder(Player player){
		try {
			this.border = net.minecraft.server.v1_8_R3.WorldBorder.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public net.minecraft.server.v1_8_R3.WorldBorder getHandle() {
		return border;
	}

	public double getCenterX() {
		return border.getCenterX();
	}

	public double getCenterZ() {
		return border.getCenterZ();
	}

	public double getMinX() {
		return border.b();
	}

	public double getMaxX() {
		return border.e();
	}

	public double getMinY() {
		return border.c();
	}

	public double getMaxY() {
		return border.e();
	}

	public void setCenter(double x, double z) {
		border.setCenter(x,z);
	}

	public double getWith() {
		return border.getSize();
	}

	public double getLength() {
		return border.j();
	}

	public void setRadius(double radius) {
		border.setSize(radius);
	}

	public int getDamageBufferInBlocks() {
		return border.l();
	}

	public void setDamageBufferInBlocks(int blocks) {
		border.a(blocks);
	}

	public double getDamagePerSecondPerBlock() {
		return border.getDamageAmount();
	}

	public void setDamagerPerSecondPerBlock(double damage) {
		border.setDamageAmount(damage);
	}

	public int getWarningTimerInSeconds() {
		return border.getWarningTime();
	}

	public void setWarningTimeInSeconds(int seconds) {
		border.setWarningTime(seconds);
	}

	public int getWarningDistanceInBlocks() {
		return border.getWarningDistance();
	}

	public void setWarningDistanceInBlocks(int blocks) {
		border.setWarningDistance(blocks);
	}

	public boolean isInBounds(Location location){
		return border.isInBounds(location.getBlockX(), location.getBlockZ());
	}
	
	public void lerp(double oldSize,double newSize, long time){
		border.transitionSizeBetween(oldSize, newSize, time);
	}
}