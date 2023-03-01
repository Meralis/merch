import {Badge} from "react-bootstrap";

function Cart({products, removeFromCart}) {
    return <div className={products.length ? 'cart-block p-3' : 'd-none'}>
        <h3>Кошик</h3>
        <ul className={"my-4"}>
            {products.map(product => <li>
                {product.title} (${product.price.toFixed(2)})
                <Badge variant="danger"
                       className={'bg-danger cursor-pointer text-white'}
                       onClick={() => removeFromCart(product.id)}>Видалити</Badge>
            </li>)}
        </ul>
    </div>;
}

export default Cart;
