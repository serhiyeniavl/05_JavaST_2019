package com.epam.objects.constants;

/**
 * Enum class contains named const values which stored in input file.
 */
public enum FileData {
    /**
     * First x, y, z coordinates.
     */
    FIRST_X(0), FIRST_Y(1), FIRST_Z(2),

    /**
     * Second x, y, z coordinates.
     */
    SECOND_X(3), SECOND_Y(4), SECOND_Z(5),

    /**
     * Height of the pyramid and little height top truncated pyramid.
     */
    HEIGHT(6), LITTLE_HEIGHT(7),

    /**
     * Number of angels pyramid basis.
     */
    NUM_OF_ANGELS(8),

    /**
     * Data quantity required from the user input.
     */
    DATA_QUANTITY(9);

    /**
     * Parameter position.
     */
    private final int position;

    /**
     * Sets parameter position.
     *
     * @param num position.
     */
    FileData(final int num) {
        this.position = num;
    }

    /**
     * @return parameter position.
     */
    public int getPosition() {
        return position;
    }
}
