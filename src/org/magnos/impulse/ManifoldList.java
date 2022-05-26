package org.magnos.impulse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * list of {@link Manifold} instances. specialised for use in the collision
 * detector: you can only {@link #add()} to it, and {@link #clear()} it. the
 * only exception is that you can remove the very last item in the list with
 * {@link #removeLast()}
 * </p>
 * <p>
 * the point of it is to avoid allocating instances, so the remain in the
 * underlying list, even when you clear it
 * </p>
 * 
 * 
 * @author stik
 *
 */
public class ManifoldList implements Iterable<Manifold> {
	private List<Manifold>	list	= new ArrayList<>();
	private int				size;

	public void clear() {
		size = 0;
	}

	public Manifold add() {
		if (size >= list.size()) {
			Manifold c = new Manifold();
			list.add(c);
			++size;
			return c;
		}
		return list.get(size++);
	}

	@Override
	public Iterator<Manifold> iterator() {
		return new Iterator<Manifold>() {
			private int idx = -1;

			@Override
			public boolean hasNext() {
				return idx < size - 1;
			}

			@Override
			public Manifold next() {
				return list.get(++idx);
			}
		};

	}

	public void removeLast() {
		if (size == 0)
			throw new IllegalStateException("No elements in list");
		--size;
	}

	public int size() {
		return size;
	}

}
