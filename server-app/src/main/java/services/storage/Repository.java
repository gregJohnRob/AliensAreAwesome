package services.storage;

import java.util.List;

/* Defines a set of common methods a class storing something must implement */
public interface Repository<T>
{
  List<T> getContents();
  
  boolean add(T aItem);

  boolean remove(T aItem);
  boolean remove(String aId);
  
  boolean contains(T aItem);
  boolean contains(String aId);
}
