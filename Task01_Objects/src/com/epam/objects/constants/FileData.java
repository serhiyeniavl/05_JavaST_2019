package com.epam.objects.constants;

public enum FileData {
    FIRST_X(0), FIRST_Y(1), FIRST_Z(2), SECOND_X(3),
    SECOND_Y(4), SECOND_Z(5), HEIGHT(6), LITTLE_HEIGHT(7),
    NUM_OF_ANGELS(8), DATA_QUANTITY(9);

    private final int position;

    FileData(final int num) {
        this.position = num;
    }

    public int getPosition() {
        return position;
    }
}
