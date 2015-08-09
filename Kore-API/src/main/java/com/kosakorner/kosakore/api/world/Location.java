package com.kosakorner.kosakore.api.world;

import com.kosakorner.kosakore.api.KoreAPI;
import com.kosakorner.kosakore.api.entity.IEntity;
import com.kosakorner.kosakore.api.type.Type;

import java.util.regex.Pattern;

public class Location {

    private IWorld world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    public Location(final IWorld world, final double x, final double y, final double z) {
        this(world, x, y, z, 0, 0);
    }

    public Location(final IWorld world, final double x, final double y, final double z, final float yaw, final float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public void setWorld(IWorld world) {
        this.world = world;
    }

    public IWorld getWorld() {
        return world;
    }

    public IBlock getBlock() {
        return world.getBlockAt(this);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public int getBlockX() {
        return locToBlock(x);
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public int getBlockY() {
        return locToBlock(y);
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public int getBlockZ() {
        return locToBlock(z);
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getYaw() {
        return yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getPitch() {
        return pitch;
    }

    public Location setDirection(Location location) {
        /*
         * Sin = Opp / Hyp
         * Cos = Adj / Hyp
         * Tan = Opp / Adj
         *
         * x = -Opp
         * z = Adj
         */
        final double _2PI = 2 * Math.PI;
        final double x = location.getX();
        final double z = location.getZ();

        if (x == 0 && z == 0) {
            pitch = location.getY() > 0 ? -90 : 90;
            return this;
        }

        double theta = Math.atan2(-x, z);
        yaw = (float) Math.toDegrees((theta + _2PI) % _2PI);

        double x2 = square(x);
        double z2 = square(z);
        double xz = Math.sqrt(x2 + z2);
        pitch = (float) Math.toDegrees(Math.atan(-location.getY() / xz));

        return this;
    }

    public Location add(Location vec) {
        if (vec == null || vec.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }

        x += vec.x;
        y += vec.y;
        z += vec.z;
        return this;
    }

    public Location add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Location subtract(Location vec) {
        if (vec == null || vec.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }

        x -= vec.x;
        y -= vec.y;
        z -= vec.z;
        return this;
    }

    public Location subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public double length() {
        return Math.sqrt(square(x) + square(y) + square(z));
    }

    public double distance(Location o) {
        return Math.sqrt(distanceSquared(o));
    }

    public double distanceSquared(Location o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null location");
        } else if (o.getWorld() == null || getWorld() == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null world");
        } else if (o.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot measure distance between " + getWorld().getName() + " and " + o.getWorld().getName());
        }

        return square(x - o.x) + square(y - o.y) + square(z - o.z);
    }

    public Location multiply(double m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    public Location zero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;

        if (this.world != other.world && (this.world == null || !this.world.equals(other.world))) {
            return false;
        }
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch)) {
            return false;
        }
        if (Float.floatToIntBits(this.yaw) != Float.floatToIntBits(other.yaw)) {
            return false;
        }
        return true;
    }

    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    public static int locToBlock(double loc) {
        return floor(loc);
    }

    private static int floor(double num) {
        final int floor = (int) num;
        return floor == num ? floor : floor - (int) (Double.doubleToRawLongBits(num) >>> 63);
    }

    private static double square(double toSquare) {
        return toSquare * toSquare;
    }

    public String toString() {
        return "(" + world.getName() + "," + x + "," + y + "," + z + ")";
    }

    public static String toString(Location location) {
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getPitch()  + "," + location.getYaw();
    }

    public static Location fromString(String string) {
        if (string == null || string.equals("null") || string.equals("")) {
            return null;
        }
        String[] parts = string.split(Pattern.quote(","));
        Location parsed = new Location(KoreAPI.getKore().worldFactory().getWorld(parts[0]), Double.valueOf(parts[1]), Double.valueOf(parts[2]), Double.valueOf(parts[3]));
        if (parts.length > 4) {
            parsed.setPitch(Float.valueOf(parts[4]));
            parsed.setYaw(Float.valueOf(parts[5]));
        }
        return parsed;
    }

    public boolean isSafe() {
        IBlock above = getWorld().getBlockAt(getBlockX(), getBlockY() + 1, getBlockZ());
        IBlock below = getWorld().getBlockAt(getBlockX(), getBlockY() - 1, getBlockZ());

        if (below.getType().equals(Type.AIR)) {
            return false;
        }
        if (below.getType().equals(Type.LAVA)) {
            return false;
        }
        if (below.getType().equals(Type.STATIONARY_LAVA)) {
            return false;
        }
        if (below.getType().equals(Type.CACTUS)) {
            return false;
        }
        if ((
                    getBlock().getType().equals(Type.AIR) ||
                    getBlock().getType().equals(Type.TORCH) ||
                    getBlock().getType().equals(Type.REDSTONE_TORCH_OFF) ||
                    getBlock().getType().equals(Type.REDSTONE_TORCH_ON) ||
                    getBlock().getType().equals(Type.REDSTONE_WIRE) ||
                    getBlock().getType().equals(Type.CROPS) ||
                    getBlock().getType().equals(Type.CARROT) ||
                    getBlock().getType().equals(Type.POTATO) ||
                    getBlock().getType().equals(Type.LONG_GRASS) ||
                    getBlock().getType().equals(Type.RED_ROSE) ||
                    getBlock().getType().equals(Type.YELLOW_FLOWER) ||
                    getBlock().getType().equals(Type.MELON_STEM) ||
                    getBlock().getType().equals(Type.PUMPKIN_STEM) ||
                    getBlock().getType().equals(Type.DEAD_BUSH) ||
                    getBlock().getType().equals(Type.SIGN_POST) ||
                    getBlock().getType().equals(Type.SIGN) ||
                    getBlock().getType().equals(Type.DOUBLE_PLANT) ||
                    getBlock().getType().equals(Type.STONE_PLATE) ||
                    getBlock().getType().equals(Type.WOOD_PLATE) ||
                    getBlock().getType().equals(Type.STEP)
            ) && (above.getType().equals(Type.AIR))) {
            return true;
        }
        return false;
    }

    public void removeMobs(int radius) {
        int px = getBlockX();
        int py = getBlockY();
        int pz = getBlockZ();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                IChunk c = getWorld().getChunkAt(new Location(getWorld(), px + x * 16, py, pz + z * 16));
                for (IEntity e : c.getEntities()) {
                    switch (e.getType()) {
                        case SPIDER:
                        case CREEPER:
                        case ENDERMAN:
                        case SKELETON:
                        case ZOMBIE:
                        case WITCH:
                            e.remove();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

}
