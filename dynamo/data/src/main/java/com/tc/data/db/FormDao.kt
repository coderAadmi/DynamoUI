package com.tc.data.db

import FormEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg formEntity : FormEntity )
    // insert(FormEntity1, FormEntity2, ......FormEntityN)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( formEntities : List<FormEntity> )
    // insert(formList)

    @Query("Select * from forms")
    fun getAll() : Flow<List<FormEntity?>>

    @Query("Select * from forms where formId = :id")
    fun getFormById(id : String) : Flow<FormEntity?>
}