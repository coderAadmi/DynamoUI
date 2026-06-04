package com.tc.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [FormEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DynamoDb : RoomDatabase() {
    abstract fun getFormDao() : FormDao
}