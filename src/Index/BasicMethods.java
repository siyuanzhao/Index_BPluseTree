package Index;

import java.io.Serializable;
import java.util.ArrayList;


public class BasicMethods<E extends Comparable<E>> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3522296078907930861L;

	public void sortedInsert(E key, Object record, Node<E> N) {
        ArrayList<E> data = N.getKeyValue();
        ArrayList<Object> pointers = N.getPointers();
        if (data.isEmpty()) {
            data.add(key);
            pointers.add(record);
            return;
        } else {
            if (key.compareTo(data.get(0)) < 0) {
                data.add(0, key);
                pointers.add(0, record);
            } else {
                boolean flag = true;
                for (int i = 0; i < data.size(); i++) {
                    if (key.compareTo(data.get(i)) < 0) {
                        flag = false;
                        data.add(i, key);
                        pointers.add(i, record);
                        break;
                    }
                }
                if (flag) {
                    data.add(key);
                    pointers.add(record);
                }
            }
        }
    }
   
    
    public void sortedInsertInternal(E key, Object record, Node<E> N) {
        ArrayList<E> data = N.getKeyValue();
        ArrayList<Object> pointers = N.getPointers();
        if (key.compareTo(data.get(0)) < 0) {
            data.add(0, key);
            pointers.add(1, record);
        } 
        else {
            boolean flag = true;
            for (int i = 0; i < data.size(); i++) {
                if (key.compareTo(data.get(i)) < 0) {
                    data.add(i, key);
                    pointers.add(i + 1, record);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                data.add(key);
                pointers.add(record);
            }
        }
    }

    public void deleteNode(Node<E> n, E k, Object dataValue) {
        for (int i = 0; i < n.getKeyValue().size(); i++) {
            if (k.compareTo(n.getKeyValue().get(i)) == 0 && dataValue.equals(n.getPointers().get(i))) {
                n.getKeyValue().remove(i);
                n.getPointers().remove(i);
            }
        }
    }

    public void internalDelete(E key, Node<E> n, Node<E> temp) {
        for (int i = 0; i < n.getKeyValue().size(); i++) {
            if (n.getKeyValue().get(i).compareTo(key) == 0) {
                n.getKeyValue().remove(i);
                n.getPointers().remove(i + 1);
            }
        }
    }

    public int sameParent(Node<E> n, Node<E> parent, int size) {
        ArrayList<E> keys = parent.getKeyValue();
        boolean _next = false;
        boolean _prev = false;
        Node<E> next = n.getNext();
        Node<E> prev = n.getPrev();
        if (sameParent2(parent, n)) {
            for (int i = 0; i < parent.getPointers().size(); i++) {
                if (next == parent.getPointers().get(i)) {
                    _next = true;
                    break;
                }
            }
        }
        if (!sameParent2(parent, n)) {
            for (int i = 0; i < parent.getPointers().size(); i++) {
                if (prev == parent.getPointers().get(i)) {
                    _prev = true;
                    break;
                }
            }
        }
        if (_next && next.getKeyValue().size() - 1 >= Math.ceil(size / 2.0)) {
            return 1;
        } else if (_prev && prev.getKeyValue().size() - 1 >= Math.ceil(size / 2.0)) {
            return 2;
        } else {
            return 0;
        }
    }

    public int nexOrprev(Node<E> n, Node<E> parent, int size) {
        boolean _next = false;
        boolean _prev = false;
        Node<E> next = n.getNext();
        Node<E> prev = n.getPrev();
        if (next != null) {
            for (int i = 0; i < parent.getPointers().size(); i++) {
                if (next == parent.getPointers().get(i)) {
                    _next = true;
                    break;
                }
            }
        }
        if (prev != null) {
            for (int i = 0; i < parent.getPointers().size(); i++) {
                if (prev == parent.getPointers().get(i)) {
                    _prev = true;
                    break;
                }
            }
        }
        if (next != null && _next && next.getKeyValue().size() - 1 >= 1) {
            return 1;
        } else if (prev != null && _prev && prev.getKeyValue().size() - 1 >= 1) {
            return 2;
        } else {
            return 0;
        }
    }

    public boolean sameParent2(Node<E> parent, Node<E> n) {
        boolean _next = false;
        boolean _prev = false;
        Node<E> next = n.getNext();
        Node<E> prev = n.getPrev();
        if (next != null) {
            for (int i = 0; i < parent.getPointers().size(); i++) {
                if (next == parent.getPointers().get(i)) {
                    _next = true;
                    break;
                }
            }
        }
        if (prev != null) {
            for (int i = 0; i < parent.getPointers().size(); i++) {
                if (prev == parent.getPointers().get(i)) {
                    _prev = true;
                    break;
                }
            }
        }
        if (_next) {
            return true;
        } else {
            return false;
        }


    }
}
