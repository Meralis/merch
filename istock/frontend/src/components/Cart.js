import {Button, ListGroup} from "react-bootstrap";
import CartItem from "./CartItem";
import Total from "./Total";

function Cart({products, removeFromCart, changeCount}) {
    return <div className={products.length ? 'cart-block p-3' : 'd-none'}>
        <h3>Cart</h3>
        <ListGroup className={'my-4'}>
            {products.map(product => <CartItem
                key={product.productId}
                product={product}
                changeCount={changeCount}
                removeFromCart={removeFromCart}
            />)}
            <Total products={products}/>
            <div className="mb-2">
                <Button variant="success" size="lg">
                    Оформити замовлення
                </Button>{' '}
            </div>
        </ListGroup>
    </div>;
}

export default Cart;