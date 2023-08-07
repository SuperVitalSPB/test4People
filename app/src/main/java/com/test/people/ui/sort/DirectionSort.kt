package com.test.people.ui.sort

enum class DirectionSort(iType: Int) {
    tfNone(-1), tfSortUp(0), tfSortDown(1);

    companion object {
        fun valueOf(value: Int?): DirectionSort {
            return when (value) {
                0 -> tfSortUp
                1 -> tfSortDown
                else -> tfNone
            }
        }
    }
}