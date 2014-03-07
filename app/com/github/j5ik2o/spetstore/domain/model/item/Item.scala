package com.github.j5ik2o.spetstore.domain.model.item

import com.github.j5ik2o.spetstore.domain.infrastructure.support.{EntityIOContext, Entity}
import scala.util.Try
import com.github.j5ik2o.spetstore.domain.model.basic.SexType

/**
 * ペットを表すエンティティ。
 *
 * @param id 識別子
 * @param itemTypeId [[com.github.j5ik2o.spetstore.domain.model.item.ItemTypeId]]
 * @param name 名前
 * @param description 説明
 * @param price 価格
 */
case class Item
(id: ItemId = ItemId(),
 itemTypeId: ItemTypeId,
 name: String,
 sexType: SexType.Value,
 description: Option[String] = None,
 price: BigDecimal,
 supplierId: SupplierId)
  extends Entity[ItemId] {

  /**
   * [[com.github.j5ik2o.spetstore.domain.model.item.ItemType]]を取得する。
   *
   * @param ptr [[com.github.j5ik2o.spetstore.domain.model.item.ItemTypeRepository]]
   * @param ctx [[com.github.j5ik2o.spetstore.domain.infrastructure.support.EntityIOContext]]
   * @return `Try`にラップされた[[com.github.j5ik2o.spetstore.domain.model.item.ItemType]]
   */
  def petType(implicit ptr: ItemTypeRepository, ctx: EntityIOContext): Try[ItemType] =
    ptr.resolveEntity(itemTypeId)

}


