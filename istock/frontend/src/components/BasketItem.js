import {Badge, Button, ListGroup} from "react-bootstrap";
import changeCount from "../common/ChangeCount";
import {useContext} from "react";
import ProductContext from "../context/ProductContext";
import removeFromBasket from "../common/RemoveFromBasket";

function BasketItem({product}) {
    const [products, setProducts] = useContext(ProductContext);
    return <ListGroup.Item>
        {product.title} (${product.price})
        <div className={'d-flex align-items-center justify-content-between'}>
            <div>
                <Button variant={'primary'} size={'sm'}
                        disabled={product.count === 1}
                        onClick={() => changeCount(product.productId, product.count - 1, products, setProducts)}>-</Button>
                <span className={'mx-2'}>{product.count}</span>
                <Button variant={'primary'} size={'sm'}
                        onClick={() => changeCount(product.productId, product.count + 1, products, setProducts)}>+</Button>
            </div>
            <div>
                <b>${(product.count * product.price)}</b>
            </div>
            <Badge variant="danger"
                   className={'ml-3 cursor-pointer bg-danger text-white'}
                   onClick={() => removeFromBasket(product.productId, products, setProducts)}>Remove</Badge>
        </div>
    </ListGroup.Item>
}

export default BasketItem;