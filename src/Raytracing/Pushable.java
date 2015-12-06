package Raytracing;

public interface Pushable {

    /**
     * Returns the size of the object fields
     * Could be calculated by getFields().length-1 - maybe?
     * @return amount of fields multiplied with
     */
    int getFieldSize();

    /**
     * Returns a serialized array of fields
     * Everything can be cl_float4 - makes sense to push that data directly and convert all data-information like that
     * @return structured field data, able to push into the CL buffer.
     */
    float[] getFields();

    /**
     * Returns dependencies that implemented the Pushable Interface as well
     * @return null|Pushable[] Can be null
     */
    Pushable[] getDependencies();


}