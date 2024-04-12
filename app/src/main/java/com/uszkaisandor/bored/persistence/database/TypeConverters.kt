package com.uszkaisandor.bored.persistence.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.uszkaisandor.bored.domain.LeisureActivityType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ProvidedTypeConverter
class TypeConverters @Inject constructor(
    private val json: Json
) {

    @TypeConverter
    fun leisureActivityTypeToString(type: LeisureActivityType): String {
        return json.encodeToString(type)
    }

    @TypeConverter
    fun stringToLeisureActivityType(data: String): LeisureActivityType {
        return json.decodeFromString(data)
    }

}