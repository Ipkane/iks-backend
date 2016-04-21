package com.iks.cms.web.api.grid

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.iks.cms.web.api.common.AbstractApiResponse

/**
 * @author Igor Kaynov
 */
@JsonSerialize
class ResponseNav : AbstractApiResponse {
  var nav: List<String>? = null

  constructor(nav: List<String>) {
    this.nav = nav;
  }
}
