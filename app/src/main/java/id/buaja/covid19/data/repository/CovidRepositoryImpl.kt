package id.buaja.covid19.data.repository

import id.buaja.covid19.data.repository.mapping.mapToEntityDomain
import id.buaja.covid19.data.repository.mapping.mapToEntityLocationDomain
import id.buaja.covid19.data.source.RemoteDataSource
import id.buaja.covid19.domain.repository.CovidRepository
import id.buaja.covid19.domain.usecase.model.LocationProvinceWithTotalCases
import id.buaja.covid19.domain.usecase.model.ProvinceCovid
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CovidRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : CovidRepository {
    override fun getProvinceCovid(): Flow<ProvinceCovid> {
        return flow {
            emit(
                remoteDataSource.getProvinceCovid()
                    .mapToEntityDomain()
            )
        }.flowOn(dispatcher)
    }

    override fun getLocationProvinceWithTotalCases(): Flow<LocationProvinceWithTotalCases> {
        return flow {
            emit(
                remoteDataSource.getProvinceCovid()
                    .mapToEntityLocationDomain()
            )
        }.flowOn(dispatcher)
    }
}