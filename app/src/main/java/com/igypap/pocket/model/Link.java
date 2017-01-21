package com.igypap.pocket.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by igypap on 08.01.17.
 */

@DatabaseTable(tableName = "link")
public class Link implements Comparable<Link> {
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_LINK = 2;

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false)
    private int type;
    @DatabaseField(canBeNull = false)
    private String reference;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


    @Override
    public int compareTo(Link o) {
        return name.compareTo(o.getName());
    }
}
