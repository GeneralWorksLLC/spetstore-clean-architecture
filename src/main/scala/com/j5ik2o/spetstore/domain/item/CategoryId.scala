package com.j5ik2o.spetstore.domain.item

import com.j5ik2o.spetstore.infrastructure.support.Identifier
import java.util.UUID

/**
 * [[com.j5ik2o.spetstore.domain.item.Category]]のための識別子。
 *
 * @param value 識別子の値
 */
case class CategoryId(value: UUID = UUID.randomUUID())
  extends Identifier[UUID]