import {ListGroup} from "react-bootstrap";
import CartItem from "./CartItem";
import Total from "./Total";

function Cart({products, removeFromCart, changeCount}) {
    return <div className={products.length ? 'cart-block p-3' : 'd-none'}>
        <h3>Cart</h3>
        <ListGroup className={'my-4'}>
            {products.map(product => <CartItem
                key={product.id}
                product={product}
                changeCount={changeCount}
                removeFromCart={removeFromCart}
            />)}
            <Total products={products}/>
        </ListGroup>
    </div>;
}

export default Cart;