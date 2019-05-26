package com.github.yannicklamprecht.worldborder.v1_13_R2;

import com.github.yannicklamprecht.worldborder.api.AbstractWorldBorder;
import com.github.yannicklamprecht.worldborder.api.Position;
import com.github.yannicklamprecht.worldborder.api.WorldBorderAction;
import net.minecraft.server.v1_13_R2.ChunkCoordIntPair;
import net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder.EnumWorldBorderAction;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class WorldBorder extends AbstractWorldBorder {

    private net.minecraft.server.v1_13_R2.WorldBorder handle;

    public WorldBorder(Player player) {
        this(new net.minecraft.server.v1_13_R2.WorldBorder());
        this.handle.world = ((CraftWorld)player.getWorld()).getHandle();
    }

    public WorldBorder(World world) {
        this(((CraftWorld) world).getHandle().getWorldBorder());
    }

    private WorldBorder(net.minecraft.server.v1_13_R2.WorldBorder worldBorder) {
        super(
        () -> new Position(worldBorder.getCenterX(), worldBorder.getCenterZ()),
        (position -> worldBorder.setCenter(position.getX(), position.getZ())),
        () -> new Position(worldBorder.b(), worldBorder.c()),
        () -> new Position(worldBorder.d(), worldBorder.e()),
        worldBorder::getSize,
        worldBorder::setSize,
        worldBorder::l,
        worldBorder::a,
        worldBorder::getDamageAmount,
        worldBorder::setDamageAmount,
        worldBorder::getWarningTime,
        worldBorder::setWarningTime,
        worldBorder::getWarningDistance,
        worldBorder::setWarningDistance,
        (Location location) -> worldBorder.isInBounds(new ChunkCoordIntPair(location.getBlockX(), location.getBlockZ())),
        worldBorder::transitionSizeBetween
        );
        this.handle = worldBorder;
    }

    @Override
    public void send(Player player, WorldBorderAction worldBorderAction) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldBorder(handle, EnumWorldBorderAction.valueOf(worldBorderAction.name())));
    }
}
