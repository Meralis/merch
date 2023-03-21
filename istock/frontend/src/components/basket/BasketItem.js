import {Badge, Button, ListGroup} from "react-bootstrap";
import {useContext} from "react";
import ProductContext from "../../context/ProductContext";
import changeCount from "../../common/commonComponents/changeCount";
import {saveProducts} from "../../utils/saveProducts";

function BasketItem({product, removeFromBasket}) {
    const [products, setProducts] = useContext(ProductContext);

    function getNewProducts(newCount) {
        const newProducts = changeCount(products, product.productId, newCount);
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    return <ListGroup.Item>
        {product.title} (${product.price})
        <div className={'d-flex align-items-center justify-content-between'}>
            <div>
                <Button variant={'primary'} size={'sm'}
                        onClick={() => getNewProducts(product.count - 1)}
                        disabled={product.count === 1}>-</Button>
                <span className={'mx-2'}>{product.count}</span>
                <Button variant={'primary'} size={'sm'}
                        onClick={() => getNewProducts(product.count + 1)}>+</Button>
            </div>
            <div>
                <b>${(product.count * product.price)}</b>
            </div>
            <Badge variant="danger"
                   className={'ml-3 cursor-pointer bg-danger text-white'}
                   onClick={() => removeFromBasket(product.productId)}>Remove</Badge>
        </div>
    </ListGroup.Item>
}

export default BasketItem;