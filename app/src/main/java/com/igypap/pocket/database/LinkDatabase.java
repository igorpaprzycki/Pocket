package com.igypap.pocket.database;

import com.igypap.pocket.model.Link;

import java.util.List;

/**
 * Created by igypap on 08.01.17.
 */

public interface LinkDatabase {
    List<Link> getLinks();

    void create(Link link);
}
