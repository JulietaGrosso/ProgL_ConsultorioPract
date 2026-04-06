package org.progl.interfaces;

import java.util.List;

public interface Dao <O,K> {
// O objeto K key clave primaria

  public List<O> getAll();
  
  

}
