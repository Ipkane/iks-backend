package com.iks.cms.core.sql

/**
 * @author Igor Kaynov
 */
fun String?.quote(): String=if (this == null) "null" else "\"$this\""
