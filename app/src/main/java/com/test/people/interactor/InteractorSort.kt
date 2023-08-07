package com.test.people.interactor

import com.test.people.api.ApiResult
import com.test.people.model.Valute
import com.test.people.ui.SourceFragment
import javax.inject.Inject

class InteractorSort @Inject constructor(private val interactorEntity: InteractorEntity,
                                         private val interactorDatabase: InteractorDatabase) {

    suspend fun getData (sourceFragment: SourceFragment): ApiResult<List<Valute>> {
        return when (sourceFragment) {
            SourceFragment.sfPopular -> {
                ApiResult.Success(interactorEntity.getLatest().data?.rates)
            }
            SourceFragment.sfFavorites -> {
                val list = interactorDatabase.getFavorites()
                ApiResult.Success(list.data?.map {
                    Valute.from(it)
                })
            }
            else -> ApiResult.Success(emptyList<Valute>())
        }
    }
}