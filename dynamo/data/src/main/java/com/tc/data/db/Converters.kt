package com.tc.data.db

import FormElementEntity
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromString(str : String?) : List<FormElementEntity> {
        if(str == null){
            return emptyList()
        }
        return Json.decodeFromString(str)
    }

    @TypeConverter
    fun toString(forms :  List<FormElementEntity> ) : String {
        return Json.encodeToString(forms)
    }

}