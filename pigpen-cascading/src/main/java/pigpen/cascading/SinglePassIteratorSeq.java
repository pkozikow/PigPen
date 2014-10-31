package pigpen.cascading;


import java.io.IOException;
import java.io.NotSerializableException;
import java.util.Iterator;

import clojure.lang.ASeq;
import clojure.lang.IPersistentMap;
import clojure.lang.ISeq;

// This class is not thread safe! (Unlike true IteratorSeq)
public class SinglePassIteratorSeq extends ASeq {

  private static final Object NOT_GOTTEN = new Object();

  private final Iterator iter;
  private final State state;

  private static class State {
    private Object val;
    private boolean restTaken;
  }

  public static SinglePassIteratorSeq create(Iterator iter) {
    if (iter.hasNext()) {
      return new SinglePassIteratorSeq(iter);
    }
    return null;
  }

  private SinglePassIteratorSeq(Iterator iter) {
    this.iter = iter;
    this.state = new State();
    this.state.val = NOT_GOTTEN;
    this.state.restTaken = false;
  }

  private SinglePassIteratorSeq(IPersistentMap meta, Iterator iter, State state) {
    super(meta);
    this.iter = iter;
    this.state = state;
  }

  @Override
  public Object first() {
    if (state.val == NOT_GOTTEN) {
      state.val = iter.next();
    }
    return state.val;
  }

  @Override
  public ISeq next() {
    if (state.restTaken) {
      throw new IllegalStateException("Already took rest");
    }
    if (state.val == NOT_GOTTEN) {
      first();
    }
    ISeq rest = create(iter);
    this.state.restTaken = true;
    return rest;
  }

  @Override
  public SinglePassIteratorSeq withMeta(IPersistentMap meta) {
    return new SinglePassIteratorSeq(meta, iter, state);
  }

  private void writeObject(java.io.ObjectOutputStream out) throws IOException {
    throw new NotSerializableException(getClass().getName());
  }
}
