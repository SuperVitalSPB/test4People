package com.test.people.ui.sort

enum class TypeField(iType: Int) {
    tfNone(-1), tfByName(0), tfByValue(1);

    companion object {
        fun valueOf(value: Int?): TypeField {
            return when (value) {
                0 -> tfByName
                1 -> tfByValue
                else -> tfNone
            }
        }
    }
}