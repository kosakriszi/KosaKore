package com.kosakorner.kosakore.api.util.algorithm;

import com.kosakorner.kosakore.api.type.Type;
import com.kosakorner.kosakore.api.world.IBlock;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.Location;

public class OcTree {

    private volatile int volume;
    private volatile int depth;
    //private volatile boolean  shouldDelete;
    //private volatile Material toCount;

    private              int[] origin    = {0, 0, 0};
    private              int[] transpose = {1, -1, 1};
    private              int[] v         = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int   ULT       = 0;
    private static final int   URT       = 3;
    private static final int   LLT       = 6;
    private static final int   LRT       = 9;

    private static final int ULB = 12;
    private static final int URB = 15;
    private static final int LLB = 18;
    private static final int LRB = 21;

    private IWorld world;
    private static final int OPPOSITE = 21;
    private static final int X        = 0;
    private static final int Y        = 1;
    private static final int Z        = 2;

    private OcTree(Location upper, Location lower, int[] origin, int[] transpose, int depth) {
        this(upper, lower);
        this.origin = origin;
        this.transpose = transpose;
        this.depth = depth;
    }

    public OcTree(Location upper, Location lower) {
        depth = 0;

        // 1. upper left top
        v[ULT + X] = upper.getBlockX();   // = v[X]
        v[ULT + Y] = upper.getBlockY();   // = v[Y]
        v[ULT + Z] = upper.getBlockZ();   // = v[Z]

        // 8. lower right bottom
        v[X + OPPOSITE] = lower.getBlockX();// v[LRB + X]
        v[Y + OPPOSITE] = lower.getBlockY();// v[LRB + Y]
        v[Z + OPPOSITE] = lower.getBlockZ();// v[LRB + Z]

        // 2. upper right top
        v[URT + X] = v[OPPOSITE + X];
        v[URT + Y] = v[Y];
        v[URT + Z] = v[Z];

        // 3. lower left top
        v[LLT + X] = v[X];
        v[LLT + Y] = v[OPPOSITE + Y];
        v[LLT + Z] = v[Z];

        // 4. lower right top
        v[LRT + X] = v[OPPOSITE + X];
        v[LRT + Y] = v[OPPOSITE + Y];
        v[LRT + Z] = v[Z];

        // 5. upper left bottom
        v[ULB + X] = v[X];
        v[ULB + Y] = v[Y];
        v[ULB + Z] = v[OPPOSITE + Z];

        // 6. upper right bottom
        v[URB + X] = v[OPPOSITE + X];
        v[URB + Y] = v[Y];
        v[URB + Z] = v[OPPOSITE + Z];

        // 7. lower left bottom
        v[LLB + X] = v[X];
        v[LLB + Y] = v[OPPOSITE + Y];
        v[LLB + Z] = v[OPPOSITE + Z];

        this.world = upper.getWorld();
    }

    private void translate() {
        origin[X] = v[ULT + X];
        origin[Y] = v[ULT + Y];
        origin[Z] = v[ULT + Z];


        for (int i = 0; i < 24; i += 3) {
            v[i + X] -= origin[X];
            v[i + Y] -= origin[Y];
            v[i + Z] -= origin[Z];
        }

        transpose[X] = v[X + OPPOSITE] < 0 ? -1 : 1;
        transpose[Y] = v[Y + OPPOSITE] < 0 ? -1 : 1;
        transpose[Z] = v[Z + OPPOSITE] < 0 ? -1 : 1;

        for (int i = 0; i < 24; i += 3) {
            v[i + X] *= transpose[X];
            v[i + Y] *= transpose[Y];
            v[i + Z] *= transpose[Z];
        }
    }

    private int[] bisect(int offset, int xyz) {
        int opposite = 0;
        int halfway;

        // need to find the opposite from the offset
        switch (xyz) {
            case X:
                // offsets should always be leftmost top most first
                // order should be ult urt llt lrt and same with **b's

                switch (offset) {
                    case ULT:
                        opposite = URT;
                        break;
                    case LLT:
                        opposite = LRT;
                        break;
                    case ULB:
                        opposite = URB;
                        break;
                    case LLB:
                        opposite = LRB;
                        break;
                    default:
                        return null;
                }
                break;
            case Y:
                switch (offset) {
                    case ULT:
                        opposite = LLT;
                        break;
                    case URT:
                        opposite = LRT;
                        break;
                    case ULB:
                        opposite = LLB;
                        break;
                    case URB:
                        opposite = LRB;
                        break;
                    default:
                        return null;
                }
                break;
            case Z:
                switch (offset) {
                    case ULT:
                        opposite = ULB;
                        break;
                    case URT:
                        opposite = URB;
                        break;
                    case LLT:
                        opposite = LLB;
                        break;
                    case LRT:
                        opposite = LRB;
                        break;
                    default:
                        return null;
                }
                break;
        }

        halfway = (v[opposite + xyz] - v[offset + xyz]) / 2;

        int[] r = new int[]{
                v[offset + xyz],
                v[offset + xyz] + halfway,
                v[offset + xyz] + halfway + 1,
                v[opposite + xyz]
        };

        switch (r[3] - r[0]) {
            case 0: // 7 to 7 -> 7, 7
                r[0] = r[1] = r[2] = r[3];
                break;
            case 1: // 7 to 8 -> 7, 8
                r[0] = r[1];
                r[3] = r[2];
                break;
            case 2: // 7 to 9 -> 7-8, 9
                r[0] = r[1];
                r[2] = r[3] - 1;
                break;
            default:

        }

        return r;
    }

    public int count(Type toCount) {
        //this.toCount = toCount;
        //this.shouldDelete = false;
        translate();
        return decimate();
    }

    public void clear() {
        //this.shouldDelete = true;
        translate();
        decimate();
        try {
            Thread.sleep(250);
        }
        catch (Exception e) {
            // garbage exception is garbage
        }
    }

    private boolean find() {
        for (int x = v[X]; x < 1 + v[X + OPPOSITE]; x++) {
            for (int y = v[Y]; y < 1 + v[Y + OPPOSITE]; y++) {
                for (int z = v[Z]; z < 1 + v[Z + OPPOSITE]; z++) {
                    Type type = world.getBlockAt(
                            transpose[X] * x + origin[X],
                            transpose[Y] * y + origin[Y],
                            transpose[Z] * z + origin[Z]
                    ).getType();
                    if (type != Type.AIR) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int decimate() {
        int corner = 0;
        int j = 0;
        int rx[];
        int ry[];
        int rz[];
        OcTree chumbawumba;

        if (!find()) {
            return 0;
        }

        if (depth == 2) {
            for (int x = v[X]; x < 1 + v[X + OPPOSITE]; x++) {
                for (int y = v[Y]; y < 1 + v[Y + OPPOSITE]; y++) {
                    for (int z = v[Z]; z < 1 + v[Z + OPPOSITE]; z++) {
                        IBlock block = world.getBlockAt(
                                transpose[X] * x + origin[X],
                                transpose[Y] * y + origin[Y],
                                transpose[Z] * z + origin[Z]
                        );
                        if (block.getType() != Type.AIR) {
                            if (block.getType().isContainer()) {
                                block.getInventory().clear();
                            }
                            block.setType(Type.AIR);
                            //if (shouldDelete) {

                            //}
                            //else {
                            //if (block.getType() == toCount || toCount == null) {
                            //volume++;
                            //}
                            //}
                        }
                    }
                }
            }
            return volume;
        }

        // ULT corner produces 8 by itself....
        for (; corner < 24; corner += 3, j++) {
            rx = bisect(corner, X); // 3 points used to create 4 points btw
            ry = bisect(corner, Y); // 0 5 6 10
            rz = bisect(corner, Z);
            if (rx == null || ry == null || rz == null) {
                continue;
            }

            //  o #    1 2
            //  # #    3 4
            //    # #    5 6
            //    # #    7 8

            // top

            // left hand side - always
            // upper - always

            // CUBE 1
            chumbawumba = new OcTree(
                    new Location(world, rx[0], ry[0], rz[0]),
                    new Location(world, rx[1], ry[1], rz[1]),
                    origin, transpose, depth + 1
            );
            volume += chumbawumba.decimate();

            // lower - ry[3] > ry[0]
            if (ry[3] > ry[0]) {

                // CUBE 3
                chumbawumba = new OcTree(
                        new Location(world, rx[0], ry[2], rz[0]),
                        new Location(world, rx[1], ry[3], rz[1]),
                        origin, transpose, depth + 1
                );
                volume += chumbawumba.decimate();

            }

            // right hand side - rx[3] > rx[0]
            if (rx[3] > rx[0]) {
                // upper - always

                // CUBE 2
                chumbawumba = new OcTree(
                        new Location(world, rx[2], ry[0], rz[0]),
                        new Location(world, rx[3], ry[1], rz[1]),
                        origin, transpose, depth + 1
                );
                volume += chumbawumba.decimate();

                // lower - ry[3] > ry[0]
                if (ry[3] > ry[0]) {

                    // CUBE 4
                    chumbawumba = new OcTree(
                            new Location(world, rx[2], ry[2], rz[0]),
                            new Location(world, rx[3], ry[3], rz[1]),
                            origin, transpose, depth + 1
                    );
                    volume += chumbawumba.decimate();

                }
            }

            // bottom
            if (rz[3] > rz[0]) {

                // left hand side - always
                // upper - always

                // CUBE 5
                chumbawumba = new OcTree(
                        new Location(world, rx[0], ry[0], rz[2]),
                        new Location(world, rx[1], ry[1], rz[3]),
                        origin, transpose, depth + 1
                );
                volume += chumbawumba.decimate();

                // lower - ry[3] > ry[0]
                if (ry[3] > ry[0]) {

                    // CUBE 7
                    chumbawumba = new OcTree(
                            new Location(world, rx[0], ry[2], rz[2]),
                            new Location(world, rx[1], ry[3], rz[3]),
                            origin, transpose, depth + 1
                    );
                    volume += chumbawumba.decimate();

                }

                // right hand side - rx[3] > rx[0]
                if (rx[3] > rx[0]) {
                    // upper - always

                    // CUBE 6
                    chumbawumba = new OcTree(
                            new Location(world, rx[2], ry[0], rz[2]),
                            new Location(world, rx[3], ry[1], rz[3]),
                            origin, transpose, depth + 1
                    );
                    volume += chumbawumba.decimate();

                    // lower - ry[3] > ry[0]
                    if (ry[3] > ry[0]) {

                        // CUBE 8
                        chumbawumba = new OcTree(
                                new Location(world, rx[2], ry[2], rz[2]),
                                new Location(world, rx[3], ry[3], rz[3]),
                                origin, transpose, depth + 1
                        );
                        volume += chumbawumba.decimate();

                    }
                }
            }
        }
        return volume;
    }

}
