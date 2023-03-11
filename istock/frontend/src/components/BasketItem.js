import {Badge, Button, ListGroup} from "react-bootstrap";

function BasketItem({product, changeCount, removeFromBasket}) {
    return <ListGroup.Item>
        {product.title} (${product.price})
        <div className={'d-flex align-items-center justify-content-between'}>
            <div>
                <Button variant={'primary'} size={'sm'}
                        disabled={product.count === 1}
                        onClick={() => changeCount(product.productId, product.count - 1)}>-</Button>
                <span className={'mx-2'}>{product.count}</span>
                <Button variant={'primary'} size={'sm'}
                        onClick={() => changeCount(product.productId, product.count + 1)}>+</Button>
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