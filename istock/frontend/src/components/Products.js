import {useEffect, useState} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import Cart from "./Cart";
import SelectCategories from "./SelectCategories";

function Products() {
    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');

    useEffect(() => {

        // const savedCart = localStorage.getItem('cartItems');

        fetch('http://localhost:8080').then(data => data.json()).then(data => {
            setProducts(data.map(product => ({...product, addedToCart: false, count: 1})))
            getAllCategories(data);///чи можна так?????????
        })

    }, []);


    // .then(data => {
    // if (savedCart) {
    //     let savedItems = JSON.parse(savedCart);
    //     for (let product of data) {
    //         const savedProduct = savedItems.filter(savedItem => product.id === savedItem.id);
    //         product.addedToCart = savedProduct.length;
    //         product.count = savedProduct.length ? savedProduct[0].count : 1;
    //     }
    //     setProducts(data);
    // } else {
    //     setProducts(data.map(product => ({...product, addedToCart: false, count: 1})));
    // }
    // });


    function getAllCategories(products) {
        const categories = products.reduce(
            (acc, product) => (acc.includes(product.category) ? acc : [...acc, product.category]), [])
        setCategories(categories);
    }

    function addToCart(id) {
        const newProducts = products.map(product => ({
            ...product,
            addedToCart: id === product.id ? true : product.addedToCart
        }))
        setProducts(newProducts);
        // saveProducts(newProducts);
    }

    function removeFromCart(id) {
        const newProducts = products.map(product => ({
            ...product,
            count: 1,
            addedToCart: id === product.id ? false : product.addedToCart
        }))
        setProducts(newProducts);
        // saveProducts(newProducts);
    }

    // function saveProducts(updatedProducts) {
    //     const productsToSave = updatedProducts.filter(product => product.addedToCart).map(product => ({
    //         id: product.id,
    //         count: product.count
    //     }));
    //     localStorage.setItem('cartItems', JSON.stringify(productsToSave));
    // }

    function changeCount(id, newCount) {
        const newProducts = products.map(product => ({
            ...product,
            count: id === product.id ? newCount : product.count
        }))
        setProducts(newProducts);
        // saveProducts(newProducts);
    }

    return <Row>
        <Col xs={12}>
            <Cart
                changeCount={changeCount}
                products={products.filter(product => product.addedToCart)}
                removeFromCart={removeFromCart}/>
        </Col>
        <SelectCategories categories={categories} setSelectedCategory={setSelectedCategory}/>

        {products.filter(product => product.category === selectedCategory || !selectedCategory)
            .map(product => <Product key={product.id} product={product}
                                     addToCart={addToCart} removeFromCart={removeFromCart}
            />)}
    </Row>
}

export default Products;