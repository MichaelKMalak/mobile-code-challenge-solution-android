package io.mobilityCompany.mobile_code_challenge.base

interface BaseMapper<in D, out V> {

  fun mapDataModelToViewModel(dataModel: D): V
}
