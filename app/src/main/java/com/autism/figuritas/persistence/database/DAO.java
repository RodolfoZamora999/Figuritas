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
    public long insertUser(User user);

    @Query("SELECT * FROM tb_user WHERE tb_user.id_user = :id_user")
    public User getUser(long id_user);

    @Query("SELECT * FROM tb_user WHERE tb_user.email = :email")
    public User getUserByEmail(String email);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);

    //Config
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertConfig(Configuration config);

    @Query("SELECT * FROM tb_config_user WHERE tb_config_user.id_Config = :id_config")
    public Configuration getConfig(long id_config);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateConfig(Configuration config);

    @Delete
    public void deleteConfig(Configuration config);


    //History
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertHistory(History history);

    @Query("SELECT * FROM tb_history WHERE tb_history.id_history = :id_history")
    public History getHistory(long id_history);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateHistory(History history);

    @Delete
    public void deleteHistory(History history);
    
    //Level
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertLevel(Level level);

    @Query("SELECT * FROM tb_level WHERE tb_level.id_level = :id_level")
    public Level getLevel(long id_level);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateLevel(Level level);

    @Delete
    public void deleteLevel(Level level);


    //Notification
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertNotification(Notification notification);

    @Query("SELECT * FROM tb_notification WHERE tb_notification.id_notification = :id_notification")
    public Notification getNotification(long id_notification);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateNotification(Notification notification);

    @Delete
    public void deleteNotification(Notification notification);


    //Level Complete
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertLevelComplete(LevelComplete levelComplete);

    @Query("SELECT * FROM tb_level_complete WHERE tb_level_complete.id_levelcomplete = :id_level_complete")
    public LevelComplete getLevelComplete(long id_level_complete);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateLevelComplete(LevelComplete levelComplete);

    @Delete
    public void deleteLevelComplete(LevelComplete levelComplete);


    //Bonus
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public long insertBonus(Bonus bonus);

    @Query("SELECT * FROM tb_bonus WHERE tb_bonus.id_bonus = :id_bonus")
    public Bonus getBonus(long id_bonus);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateBonus(Bonus bonus);

    @Delete
    public void deleteBonus(Bonus bonus);
}
