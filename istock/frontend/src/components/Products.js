import {useEffect, useState} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import Cart from "./Cart";
import CategoriesSelect from "./CategoriesSelect";

function Products() {
    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/').then(data => data.json()).then(data => {
            setProducts(data.map(product => ({...product, addedToCart: false, count: 1})))
            getAllCategories(data);
        })

    }, []);

    function getAllCategories(products) {
        const categories = products.reduce(
            (acc, product) => (acc.includes(product.category) ? acc : [...acc, product.category]), [])
        setCategories(categories);
        console.log(categories);
    }

    function addToCart(id) {
        const newProducts = products.map(product => ({
            ...product,
            addedToCart: id === product.id ? true : product.addedToCart
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
                products={products.filter(product => product.addedToCart)}
                removeFromCart={removeFromCart}/>
        </Col>
        <CategoriesSelect categories={categories} setSelectedCategory={setSelectedCategory}/>

        {products.filter(product => product.category === selectedCategory || !selectedCategory)
            .map(product => <Product key={product.id} product={product}
                                     addToCart={addToCart} removeFromCart={removeFromCart}
            />)}
    </Row>
}

export default Products;