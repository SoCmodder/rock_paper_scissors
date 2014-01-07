package com.socmodder.android.rps.Database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 1/6/14
 * Time: 3:49 PM
 */
public class Game implements Serializable {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private Date gameDate;

    @DatabaseField(index = true)
    private String result;

    @DatabaseField
    private String winningThrow;

    Game(){
        // needed by ormlite
    }

    public Game(String result){
        this.result = result;
    }

    @Override
    public String toString(){
        return result;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Date getGameDate(){
        return gameDate;
    }

    public void setGameDate(Date date){
        this.gameDate = date;
    }

    public String getResult(){
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public String getWinningThrow(){
        return winningThrow;
    }

    public void setWinningThrow(String winThrow){
        this.winningThrow = winThrow;
    }
}
