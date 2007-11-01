package net.sf.anathema.lib.collection;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ITransformer;

public class CollectionUtilities {

  public static <I, O> List<O> transform(I[] inputArray, ITransformer<I, O> transformer) {
    List<O> output = new ArrayList<O>();
    for (I input : inputArray) {
      output.add(transformer.transform(input));
    }
    return output;
  }
}