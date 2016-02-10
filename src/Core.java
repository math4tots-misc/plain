package com.mtots.plain;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Core {
  public static final class Err extends RuntimeException {
    public Err(String message) { super(message); }
  }
  public static Value eval(Scope scope, Value value) {
    if (!(value instanceof List))
      return value;
    List v = (List) value;
    Value head = eval(scope, v.get(0));
    if (head instanceof Function) {
      List args = new List();
      for (int i = 1; i < v.size(); i++)
        args.add(eval(scope, v.get(i)));
      return ((Function) head).call(args);
    }
    throw new Err(head.toString() + " is not callable");
  }
  public abstract static class Value {}
  public static final class Number extends Value {
    private final Double value;
    public Number(Double value) { this.value = value; }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof Number) &&
          ((Number) other).value.equals(value);
    }
  }
  public static final class Text extends Value {
    private final String value;
    public Text(String value) { this.value = value; }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof Text) &&
          ((Text) other).value.equals(value);
    }
    public String toString() { return value; }
  }
  public static final class List extends Value {
    private final ArrayList<Value> value;
    public List(ArrayList<Value> value) { this.value = value; }
    public List(Value... values) {
      this(new ArrayList<Value>());
      for (int i = 0; i < values.length; i++)
        value.add(values[i]);
    }
    public void add(Value item) { value.add(item); }
    public Value pop() { return value.remove(value.size()-1); }
    public Value get(int i) { return value.get(i); }
    public int size() { return value.size(); }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof List) &&
          ((List) other).value.equals(value);
    }
  }
  public static final class Table extends Value {
    private final HashMap<Value, Value> value;
    public Table(HashMap<Value, Value> value) { this.value = value; }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof Table) &&
          ((Table) other).value.equals(value);
    }
  }
  public static final class Blob extends Value {
    private final Blob meta;
    private final HashMap<String, Value> value;
    public Blob(Blob meta, HashMap<String, Value> value) {
      this.meta = meta;
      this.value = value;
    }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof Blob) &&
          ((Table) other).value.equals(value);
    }
  }
  public static final class Scope extends Value {
    private final Scope parent;
    private final HashMap<String, Value> value;
    public Scope(Scope parent, HashMap<String, Value> value) {
      this.parent = parent;
      this.value = value;
    }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof Scope) &&
          ((Table) other).value.equals(value);
    }
  }
  public abstract static class Function extends Value {
    public abstract Value call(List args);
  }
}
