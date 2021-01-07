package com.autism.figuritas.persistence.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Implementation class of Room DAO
 */
@Dao
public interface DAO
{
    //User
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertUser(Usuario usuario);

    @Query("SELECT * FROM tb_user WHERE tb_user.id_user = :id_user")
    public Usuario getUser(int id_user);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateUser(Usuario usuario);

    @Delete
    public void deleteUser(Usuario usuario);


    //Config
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertConfig(Configuracion config);

    @Query("SELECT * FROM tb_config_user WHERE tb_config_user.id_Config = :id_config")
    public Configuracion getConfig(int id_config);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateConfig(Configuracion config);

    @Delete
    public void deleteConfig(Configuracion config);


    //History
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long inserHistory(Historial historial);

    @Query("SELECT * FROM tb_history WHERE tb_history.id_history = :id_history")
    public Historial getHistory(int id_history);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateHistory(Historial historial);

    @Delete
    public void deleteHistory(Historial historial);


    //Level
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertLevel(Nivel nivel);

    @Query("SELECT * FROM tb_level WHERE tb_level.id_level = :id_level")
    public Nivel getLevel(int id_level);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateLevel(Nivel nivel);

    @Delete
    public void deleteLevel(Nivel nivel);


    //Notification
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertNotification(Notificacion notificacion);

    @Query("SELECT * FROM tb_notification WHERE tb_notification.id_notification = :id_notification")
    public Notificacion getNotification(int id_notification);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateNotification(Notificacion notificacion);

    @Delete
    public void deleteNotification(Notificacion notificacion);


    //Level Complete
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertLevelComplete(NivelCompletado nivelCompletado);

    @Query("SELECT * FROM tb_level_complete WHERE tb_level_complete.id_levelcomplete = :id_level_complete")
    public NivelCompletado getLevelComplete(int id_level_complete);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateLevelComplete(NivelCompletado nivelCompletado);

    @Delete
    public void deleteLevelComplete(NivelCompletado nivelCompletado);


    //Bonus
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertBonus(Bonificacion bonificacion);

    @Query("SELECT * FROM tb_bonus WHERE tb_bonus.id_bonus = :id_bonus")
    public Bonificacion getBonus(int id_bonus);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateBonus(Bonificacion bonificacion);

    @Delete
    public void deleteBonus(Bonificacion bonificacion);
}
