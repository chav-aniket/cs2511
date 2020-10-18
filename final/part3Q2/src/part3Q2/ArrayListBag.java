package part3Q2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A bag implemented using an ArrayList.
 *
 * @author Robert Clifton-Everest
 *
 * @invariant for all c in counts, c.getCount() > 0
 *
 * @param <E>
 */
public class ArrayListBag<E> implements Bag<E> {

    private ArrayList<Count<E>> counts;

    /**
     * Create a new empty bag.
     */
    public ArrayListBag() {
        this.counts = new ArrayList<Count<E>>();
    }

    private Count<E> getCount(Object o) {
        for (Count<E> c : counts)
            if (c.getElement().equals(o))
                return c;
        return null;
    }

    @Override
    public void add(E e) {
        add(e,1);
    }

    @Override
    public void add(E e, int n) {
        Count<E> c = getCount(e);
        if (c != null) {
            c.incrementCount(n);
        } else if (n > 0) {
            counts.add(new Count<E>(e, n));
        }
    }

    @Override
    public void remove(E e) {
        remove(e, 1);
    }

    @Override
    public void remove(E e, int n) {
        // TODO Implement this
        for (Count<E> c: counts) {
            if (e.equals(c.getElement())) c.decrementCount(n);
            if (c.getCount() <= 0) counts.remove(c);
        }
    }

    @Override
    public int count(Object o) {
        Count<E> c = getCount(o);
        if (c != null)
            return c.getCount();
        return 0;
    }

    @Override
    public int size() {
        // TODO Implement this
        int size = 0;
        for (Count<E> element : counts) {
            size += element.getCount();
        }
        return size;
    }

    @Override
    public Bag<E> sum(Bag<? extends E> bag) {
        // TODO Implement this
        Bag<E> retBag = new ArrayListBag<E>();
        for (Count<E> c: counts) {
            retBag.add(c.getElement(), c.getCount());
        }
        Iterator<?> iter = bag.iterator();
        while (iter.hasNext()) {
            Object o = iter.next();
            Count<E> c = (Count<E>) o;
            retBag.add(c.getElement(), c.getCount());
        }
        return retBag;
    }

    @Override
    public Iterator<Count<E>> iterator() {
        return counts.iterator();
    }

    /**
     * For this method, it should be possible to compare all other possible bags
     * for equality with this bag. For example, if an ArrayListBag<Fruit> and a
     * LinkedListBag<Fruit> both contain the same number of each element they
     * are equal. Similarly, if a Bag<Apple> contains the same elements as a
     * Bag<Fruit> they are also equal.
     */
    @Override
    public boolean equals(Object o) {
        // TODO Implement this
        ArrayListBag<?> bag = (ArrayListBag<?>) o;
        Iterator<?> iter = bag.iterator();
        for (Count c: counts) {
            Count<E> c1 = null;
            if (iter.hasNext()) {
                Object o2 = iter.next();
                c1 = (Count<E>) o2;
            }
            if (!c.getElement().equals(c1.getElement()) && !(c.getCount() == c1.getCount())) return false;
        }
        return true;
    }
}
