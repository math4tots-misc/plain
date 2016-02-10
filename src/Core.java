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
  public static final class Scope extends Value {}
}
