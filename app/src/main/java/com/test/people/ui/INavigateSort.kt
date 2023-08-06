package com.test.people.ui

interface INavigateSort {
    fun navigateSort(sourceFragment: SourceFragment )
}

enum class SourceFragment(id: Int) {
    sfNone(-1), sfPopular(0), sfFavorites(1);

    companion object {
        fun valueOf(value: Int?): SourceFragment {
            return when (value) {
                0 -> sfFavorites
                1 -> sfPopular
                else -> sfNone
            }
        }
    }
}