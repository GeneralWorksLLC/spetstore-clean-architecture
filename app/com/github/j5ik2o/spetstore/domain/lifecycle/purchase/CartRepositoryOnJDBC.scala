package com.github.j5ik2o.spetstore.domain.lifecycle.purchase

import com.github.j5ik2o.spetstore.domain.infrastructure.support.RepositoryOnJDBC
import com.github.j5ik2o.spetstore.domain.model.customer.CustomerId
import com.github.j5ik2o.spetstore.domain.model.purchase.{CartItem, Cart, CartId}
import java.util.UUID
import com.github.j5ik2o.spetstore.domain.infrastructure.json.CartFormats._
import org.json4s.DefaultReaders._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import scalikejdbc._, SQLInterpolation._


private[purchase]
class CartRepositoryOnJDBC
  extends RepositoryOnJDBC[CartId, Cart] with CartRepository {

  class Dao extends AbstractDao[Cart] {
    override def defaultAlias = createAlias("c")

    override def tableName: String = "cart"

    def extract(rs: WrappedResultSet, n: SQLInterpolation.ResultName[Cart]): Cart =
      Cart(
        id = CartId(UUID.fromString(rs.get(n.id))),
        customerId = CustomerId(UUID.fromString(rs.get(n.customerId))),
        cartItems = parse(rs.string(n.field("cartItems"))).as[List[CartItem]]
      )

    def toNamedValues(entity: Cart): Seq[(Symbol, Any)] = Seq(
      'id -> entity.id.value,
      'customerId -> entity.customerId.value,
      'cartItems -> compact(JArray(entity.cartItems.toList.map(_.asJValue)))
    )
  }

  override protected def createDao = new Dao

}
