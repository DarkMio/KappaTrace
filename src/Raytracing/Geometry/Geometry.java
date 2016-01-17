package Raytracing.Geometry;

/**
 * class for geometric functions
 */

import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Ray;

@SuppressWarnings("ConstantConditions")
public abstract class Geometry {

    /**
     * Material representing material
     */
    public final Material material;

    /**
     * Construct an AxisAlignedBox
     *
     * @param material Material for color - must not be null
     */
    public Geometry(final Material material) {
        if (material == null) throw new IllegalArgumentException("must not be null");
        this.material = material;
    }

    /**
     * abstract constructor for a ray hit
     */
    public abstract Hit hit(final Ray r);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        return !(material != null ? !material.equals(geometry.material) : geometry.material != null);

    }

    @Override
    public int hashCode() {
        return material != null ? material.hashCode() : 0;
    }
}
