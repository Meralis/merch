import {Badge, Button, ListGroup} from "react-bootstrap";
import {useContext} from "react";
import ProductContext from "../../context/ProductContext";
import changeCount from "../../utils/changeCount";
import {saveProducts} from "../../utils/saveProducts";
import removeFromBasket from "../../utils/removeFromBasket";

function BasketItem({product}) {
    const [products, setProducts] = useContext(ProductContext);

    function getNewProducts(newCount) {
        const newProducts = changeCount(products, product.productId, newCount);
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    function handleRemovingProducts(productId) {
        const newProducts = removeFromBasket(products, productId);
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    return <ListGroup.Item>
        {product.title} ({product.price} грн)
        <div className={'d-flex align-items-center justify-content-between'}>
            <div>
                <Button variant={'success'} size={'sm'}
                        onClick={() => getNewProducts(product.count - 1)}
                        disabled={product.count === 1}>-</Button>
                <span className={'mx-2'}>{product.count}</span>
                <Button variant={'success'} size={'sm'}
                        onClick={() => getNewProducts(product.count + 1)}>+</Button>
            </div>
            <div>
                <b>{(product.count * product.price)} грн</b>
            </div>
            <Badge variant="warning"
                   className={'ml-3 cursor-pointer bg-warning text-white'}
                   onClick={() => handleRemovingProducts(product.productId)}>Видалити</Badge>
        </div>
    </ListGroup.Item>
}

export default BasketItem;