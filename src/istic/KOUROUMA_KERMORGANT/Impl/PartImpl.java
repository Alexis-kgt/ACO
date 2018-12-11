package istic.KOUROUMA_KERMORGANT.Impl;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import istic.KOUROUMA_KERMORGANT.API.Part;
import istic.KOUROUMA_KERMORGANT.API.PartType;

public class PartImpl implements Part {

    private PartType type;

    private class Property {
        public final Supplier<String> getter;
        public final Consumer<String> setter;
        public final Set<String> possibleValues;

        Property(Supplier<String> getter, Consumer<String> setter, Set<String> possibleValues) {
            this.getter = getter;
            this.setter = setter;
            this.possibleValues = possibleValues;

        }
    }

    private Map<String, Property> properties = new HashMap<>();

    /**
     * To be used but subclasses only.
     *
     * @param name
     * @param getter
     * @param setter
     * @param possibleValues
     */
    protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter,
                               Set<String> possibleValues) {
        properties.put(name, new Property(getter, setter, possibleValues));
    }


    /*
     * Property management (from the definitions in PropertyManager)
     */

    /**
     * Returns an immutable set of the property names supported by the property
     * manager.
     *
     * @return
     */
    @Override
    public Set<String> getPropertyNames() {
        return Collections.unmodifiableSet(properties.keySet());
    }

    /**
     * Returns the optional value of a property. If the object does not support that
     * property then an empty optional is returned.
     *
     * @param propertyName the property to read
     * @return
     */
    @Override
    public Optional<String> getProperty(String propertyName) {
        Objects.requireNonNull(propertyName);

        if ((properties.containsKey(propertyName)) && (properties.get(propertyName).getter != null)) {
            return Optional.of(properties.get(propertyName).getter.get());
        }
        return Optional.empty();
    }

    /**
     * Sets the value of a given property. If there is not such property, or if it
     * not writable, or if the value is invalid then an IllegalArgumentException is
     * thrown.
     *
     * @param propertyName  (must be non-null)
     * @param propertyValue (must be non-null)
     * @throws IllegalArgumentException (see above)
     */
    @Override
    public void setProperty(String propertyName, String propertyValue) {
        Objects.requireNonNull(propertyName);
        Objects.requireNonNull(propertyValue);

        if ((properties.containsKey(propertyName)) && (properties.get(propertyName).setter != null)) {
            properties.get(propertyName).setter.accept(propertyValue);
        } else {
            throw new IllegalArgumentException("bad property name or value: " + propertyName);
        }
    }

    /**
     * Returns the immutable set of discrete string values for a given property. For
     * properties that have a non explicit set of possible values (eg double
     * converted to strings), or for a non existing property name, returns an empty
     * set.
     *
     * @param propertyName a non-null string reference
     * @return an immutable set (see above)
     */
    @Override
    public Set<String> getAvailablePropertyValues(String propertyName) {
        Objects.requireNonNull(propertyName);

        if (properties.containsKey(propertyName)) {
            return Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
        }
        return Collections.emptySet();
    }

    @Override
    public void printDescription(PrintStream s) {
        // TODO Auto-generated method stub

    }


}
