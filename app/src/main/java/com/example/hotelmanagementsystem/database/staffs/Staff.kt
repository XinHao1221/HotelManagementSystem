package com.example.hotelmanagementsystem.database.staffs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity(tableName = "Staff")
data class Staff(

    @PrimaryKey
    var staffID: String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "identity")
    var identity: String?,
    @ColumnInfo(name = "email_address")
    var emailAddress: String?,
    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String?,
    @ColumnInfo(name = "birthday")
    var birthday: String?,
    @ColumnInfo(name = "wage")
    var wage: Double?,
    @ColumnInfo(name = "admin")
    var admin: Boolean?,
    @ColumnInfo(name = "activateAcc")
    var activateAcc: Boolean?,
    @ColumnInfo(name = "password")
    var password: String?

)
// Migration from 1 to 2, Room 2.1.0
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE Song ADD COLUMN tag TEXT NOT NULL DEFAULT ''")
    }
}
