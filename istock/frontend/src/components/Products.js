import {useEffect, useState} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import Cart from "./Cart";

function Products() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/').then(data => data.json()).then(data => {
            setProducts(data.map(product => ({...product, addedToCart: false, count: 1})))
        })
    }, []);

    // useEffect(()=> {
    //     console.log(products);
    // },[products])

    function addToCart(id) {
        const newProducts = products.map(product => ({
            ...product,
            addedToCart: product.id === id ? true : product.addedToCart
        }))
        setProducts(newProducts);
    }

    function removeFromCart(id) {
        const newProducts = products.map(product => ({
            ...product,
            addedToCart: product.id === id ? false : product.addedToCart
        }))
        setProducts(newProducts);
    }

    return <Row>
        <Col xs={12}>
            <Cart
                products={products.filter(product => product === product.addedToCart)}
                removeFromCart={removeFromCart}/>
        </Col>
        {products.map(product => <Product
            key={product.id} product={product} addToCart={addToCart}
            removeFromCart={removeFromCart}/>)}
    </Row>
}

export default Products;