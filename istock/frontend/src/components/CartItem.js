import {Badge, Button, ListGroup} from "react-bootstrap";

function CartItem({product, changeCount, removeFromCart}) {
    return <ListGroup.Item>
        {product.title} (${product.price.toFixed(2)})
        <div className={'d-flex align-items-center justify-content-between'}>
            <div>
                <Button variant={'primary'} size={'sm'}
                        disabled={product.count === 1}
                        onClick={() => changeCount(product.id, product.count - 1)}>-</Button>
                <span className={'mx-2'}>{product.count}</span>
                <Button variant={'primary'} size={'sm'}
                        onClick={() => changeCount(product.id, product.count + 1)}>+</Button>
            </div>
            <div>
                <b>${(product.count * product.price).toFixed(2)}</b>
            </div>
            <Badge variant="danger"
                   className={'ml-3 cursor-pointer bg-danger text-white'}
                   onClick={() => removeFromCart(product.id)}>Remove</Badge>
        </div>
    </ListGroup.Item>
}

export default CartItem;