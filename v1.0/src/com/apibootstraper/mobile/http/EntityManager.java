/**
 * 
 */
package com.apibootstraper.mobile.http;

import com.apibootstraper.core.Entity;

/**
 * @author Nicolas
 *
 */
public class EntityManager {

    /**
     * 
     * @param entity
     */
    static public void persist(Entity entity) {

        if (entity.getUUID() == null) {
            // Call HTTP POST for entity
        }
        else {
            // Call HTTP PUT for entity
        }
        
        // Update the locale entity after receive response
        // It's important to set the UUID
    }
    
    static public void persist(Entity entity, HTTPResponse<?> response) {
        persist(entity);
    }
    
    static public void remove(Entity entity) {

        // Call HTTP DELETE for entity
    }
}
