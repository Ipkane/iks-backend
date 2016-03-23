package com.iks.cms.core.appObj;

import com.iks.cms.core.gul.*;
import com.iks.cms.core.gul.form.*;

import java.util.*;

/**
 * @author Igor Kaynov
 */
public interface IEditView extends IGulContainer {
  List< IGulInputField > getFields();
}
