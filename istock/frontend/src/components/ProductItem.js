import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Button, Card} from "react-bootstrap";

function ProductItem(addToCart, removeFromCart) {
    const {productId} = useParams();
    const [product, setProduct] = useState({});
    useEffect(() => {
        if ({productId}) {
            fetch('http://localhost:8080/product/' + productId).then(data => data.json()).then(data => {
                setProduct(data);
            })
        }
    },[])
    return <>
        <Card className={'productCard'} >
            <Card.Img  src={product.imageUrl} style={{height: '25rem', width: '20rem'}}/>
            <Card.Body>
                <Card.Title>{product.title}</Card.Title>
                <Card.Text>{product.description}</Card.Text>
                {/*{product.addedToCart ?*/}
                {/*    <Button variant="danger" onClick={() => removeFromCart(product.productId)}>Видалити</Button> :*/}
                {/*    <Button variant="success" onClick={() => addToCart(product.productId)}>До кошика</Button>}*/}
            </Card.Body>
        </Card>
    </>
}

export default ProductItem;