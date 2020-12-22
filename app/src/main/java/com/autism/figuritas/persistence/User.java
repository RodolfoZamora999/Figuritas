package com.autism.figuritas.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class of User for database
 */
@Entity
public class User
{
    @PrimaryKey
    @ColumnInfo
    int id_user;

    @ColumnInfo
    String nombre;
}
