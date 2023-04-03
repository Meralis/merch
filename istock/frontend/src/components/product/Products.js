import {useContext} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import Basket from "../basket/Basket";
import ProductContext from "../../context/ProductContext";

function Products() {
    const [products] = useContext(ProductContext);
    // const [client, setClient] = useState({});
    // const [categories, setCategories] = useState([]);
    // const [selectedCategory, setSelectedCategory] = useState('');

    // function getAllCategories(products) {
    //     const categories = products.reduce(
    //         (acc, product) => (acc.includes(product.category) ? acc : [...acc, product.category]), [])
    //     setCategories(categories);
    // }

    return <>
        <Row>
            <Col xs={12}>
                {/*<ClientContext.Provider value={{client}}>*/}
                <Basket
                    products={products.filter(product => product.addedToBasket)}
                />
            </Col>
            {/*<SelectCategories categories={categories} setSelectedCategory={setSelectedCategory}/>*/}
            {/*{products.filter(product => product.category === selectedCategory || !selectedCategory)*/}
            {products.map(product => <Product key={product.productId} product={product}
            />)}
        </Row>
    </>
}

export default Products;