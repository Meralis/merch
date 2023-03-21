import {useParams} from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import {Button, Card} from "react-bootstrap";
import ProductContext from "../context/ProductContext";

function ProductItem() {
    const {productId} = useParams();
    const [product, setProduct] = useState({});

    useEffect(() => {
        const savedBasket = localStorage.getItem('basketItems');
        if ({productId}) {
            fetch('http://localhost:8080/product/' + productId).then(data => data.json()).then(data => {
                // if (savedBasket) {
                //     let savedItems = JSON.parse(savedBasket);
                //     const savedProduct = savedItems.filter(savedItem => data.productId === savedItem.productId);
                //     data.addedToBasket = savedProduct.length > 0;
                //     data.count = savedProduct.length ? savedProduct[0].count : 1;
                    setProduct(data);
                // } else {
                //     setProduct(product => ({...product, addedToBasket: false, count: 1}));
                // }
            })
        }
    }, [])
    return <>
        <Card className={'productCard'}>
            <Card.Img src={product.imageUrl} style={{height: '25rem', width: '20rem'}}/>
            <Card.Body>
                <Card.Title>{product.title}</Card.Title>
                <Card.Text>{product.description}</Card.Text>
                {/*{product.addedToBasket ?*/}
                {/*    <Button variant="danger"*/}
                {/*            onClick={() => removeFromBasket(product.productId)}>Видалити</Button> :*/}
                {/*    <Button variant="success" onClick={() => addToBasket(product.productId)}>До*/}
                {/*        кошика</Button>}*/}
            </Card.Body>
        </Card>
    </>
}

export default ProductItem;