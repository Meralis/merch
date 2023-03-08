import {useEffect, useState} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import Cart from "./Cart";
import ClientContext from "../context/ClientContext";

function Products() {
    const [products, setProducts] = useState([]);
    // const [client, setClient] = useState({});
    // const [categories, setCategories] = useState([]);
    // const [selectedCategory, setSelectedCategory] = useState('');

    useEffect(() => {

        const savedCart = localStorage.getItem('cartItems');

        fetch('http://localhost:8080/product').then(data => data.json()).then(data => {
            if (savedCart) {
                let savedItems = JSON.parse(savedCart);
                for (let product of data) {
                    const savedProduct = savedItems.filter(savedItem => product.productId === savedItem.productId);
                    product.addedToCart = savedProduct.length;
                    product.count = savedProduct.length ? savedProduct[0].count : 1;
                }
                setProducts(data);
            } else {
                setProducts(data.map(product => ({...product, addedToCart: false, count: 1})));
            }

            // getAllCategories(data);///чи можна так?????????
        })
    }, []);


    // function getAllCategories(products) {
    //     const categories = products.reduce(
    //         (acc, product) => (acc.includes(product.category) ? acc : [...acc, product.category]), [])
    //     setCategories(categories);
    // }

    function addToCart(productId) {
        const newProducts = products.map(product => ({
            ...product,
            addedToCart: productId === product.productId ? true : product.addedToCart
        }))
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    function removeFromCart(productId) {
        const newProducts = products.map(product => ({
            ...product,
            addedToCart: productId === product.productId ? false : product.addedToCart,
            count: productId === product.productId ? 1 : product.count
        }))
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    function saveProducts(updatedProducts) {
        const productsToSave = updatedProducts.filter(product => product.addedToCart).map(product => ({
            productId: product.productId,
            count: product.count
        }));
        localStorage.setItem('cartItems', JSON.stringify(productsToSave));
    }

    function changeCount(productId, newCount) {
        const newProducts = products.map(product => ({
            ...product,
            count: productId === product.productId ? newCount : product.count
        }))
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    return <>
        <Row>
            <Col xs={12}>
                {/*<ClientContext.Provider value={{client}}>*/}
                    <Cart
                        changeCount={changeCount}
                        products={products.filter(product => product.addedToCart)}
                        removeFromCart={removeFromCart}
                    />
                {/*</ClientContext.Provider>*/}
            </Col>
            {/*<SelectCategories categories={categories} setSelectedCategory={setSelectedCategory}/>*/}

            {/*{products.filter(product => product.category === selectedCategory || !selectedCategory)*/}

            {products.map(product => <Product key={product.productId} product={product}
                                              addToCart={addToCart} removeFromCart={removeFromCart}
            />)}
        </Row>
    </>
}

export default Products;