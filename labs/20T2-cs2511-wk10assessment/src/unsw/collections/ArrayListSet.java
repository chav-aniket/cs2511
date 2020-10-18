/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        // TODO Implement me
        if (e != null && !elements.contains(e)) elements.add(e);
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        // TODO Implement me
        if (other == null) return false;
        if (elements.size() > other.size()) return false;
        for (E e: elements) {
            if (!other.contains(e)) return false;
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Implement me
        return elements.iterator();
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        // TODO Implement me
        Set<E> union = new ArrayListSet<E>();
        if (other == null) return this;
        for (E e: elements)
            union.add(e);
        for (E e: other)
            union.add(e);
        return union;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
        // TODO Implement me
        Set<E> intersect = new ArrayListSet<E>();
        if (other == null) return intersect;
        if (elements.size() == 0 || other.size() == 0)
            return intersect;
        for (E e: other) {
            if (elements.contains(e)) intersect.add(e);
        }
        return intersect;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
        // TODO Implement me
        if (other == null) return false;
        Set<?> otherSet = (Set<?>) other;
        if (!(otherSet.size() == elements.size())) return false;
        for (Object i: otherSet) {
            if (!elements.contains(i)) return false;
        }
        return true;
    }

}
