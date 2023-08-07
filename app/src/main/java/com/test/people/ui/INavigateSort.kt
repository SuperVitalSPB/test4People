package com.test.people.ui

interface INavigateSort {
    fun navigateSort(sourceFragment: SourceFragment )
}

enum class SourceFragment(var id: Int) {
    sfNone(-1), sfPopular(0), sfFavorites(1);

    companion object {
        fun valueOfId(id: Int?): SourceFragment {
            return when (id) {
                0 -> sfPopular
                1 -> sfFavorites
                else -> sfNone
            }
        }
    }
}