import java.util.ArrayList;
import java.util.HashMap;

public class Plain {
  public abstract static class Value {
    public abstract Value eval(Table scope);
  }
  public static final class Number extends Value {
    private final Double value;
    public Number(Double value) { this.value = value; }
    public Value eval(Table scope) { return this; }
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
    public Value eval(Table scope) { return this; }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof Text) &&
          ((Text) other).value.equals(value);
    }
  }
  public static final class List extends Value {
    private final ArrayList<Value> value;
    public List(ArrayList<Value> value) { this.value = value; }
    public Value eval(Table scope) { return this; }
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
    public Value eval(Table scope) { return this; }
    public int hashCode() { return value.hashCode(); }
    public boolean equals(Object other) {
      return
          (other instanceof Table) &&
          ((Table) other).value.equals(value);
    }
  }
}
