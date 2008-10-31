package net.sf.anathema.test.behaviour;

public interface IMockBehaviour<T> {

  public void configure(T object) throws Exception;
}