package com.iks.cms.core.gul;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IGulContainer extends IGulElement {
  List<IGulElement> getElements();
  void addElement(IGulElement element);
}
