import {ListGroup} from "react-bootstrap";
import BasketItem from "./BasketItem";
import Total from "./Total";
import RedirectToOrder from "./RedirectToOrder";

function Basket({products, removeFromBasket, changeCount}) {
    return <div className={products.length ? 'basket-block p-3' : 'd-none'}>
        <h3>Basket</h3>
        <ListGroup className={'my-4'}>
            {products.map(product => <BasketItem
                key={product.productId}
                product={product}
                changeCount={changeCount}
                removeFromCart={removeFromBasket}
            />)}
            <Total products={products}/>
            <div className="mb-2">
                <RedirectToOrder/>
            </div>
        </ListGroup>
    </div>;
}

export default Basket;