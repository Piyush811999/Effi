package com.thethirdbit.time.effi.Goals;

/**
 * Created by piyush on 5/2/18.
 */

public class ListItems {

    private String task;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public ListItems (String task){
        this.task = task;
    }

    private String name;
    private String description;

    public ListItems(String name , String description ){
        this.description = description;
        this.name = name ;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
