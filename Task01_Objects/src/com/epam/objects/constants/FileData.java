package com.epam.objects.constants;

public enum FileData {
    FIRST_X(0), FIRST_Y(1), SECOND_X(2), SECOND_Y(3),
    APOTHEM(4), HEIGHT(5), LITTLE_HEIGHT(6), NUM_OF_ANGELS(7),
    DATA_QUANTITY(8);

    private final int position;

    FileData(final int num) {
        this.position = num;
    }

    public int getPosition() {
        return position;
    }
}
