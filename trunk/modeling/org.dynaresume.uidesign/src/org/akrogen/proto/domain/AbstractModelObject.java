package org.akrogen.proto.domain;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 
 * Classe permettant l'accrochage de listeners a un javabean.<BR>
 *
 */
public abstract class AbstractModelObject {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * 
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * 
     * @param propertyName
     * @param listener
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * 
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * 
     * @param propertyName
     * @param listener
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * 
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);

    }

}
