import {useEffect, useState} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";

function Products() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/').then(data => data.json()).then(data => {
            setProducts(data);
        })
    }, []);

    // useEffect(()=> {
    //     console.log(products);
    // },[products])

    return <Row>
        {/*<Col>*/}
            {products.map(product => <Product key={product.id} product={product}/>)}
        {/*</Col>*/}
    </Row>
}

export default Products;