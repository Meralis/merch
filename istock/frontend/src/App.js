import {Nav, Navbar} from "react-bootstrap";
import {Link, Outlet} from "react-router-dom";
import ProductContext from "./context/ProductContext";
import {useState} from "react";
import useFetchProducts from "./context/UseFetchProducts";
import SearchProduct from "./components/product/SearchProduct";

function App() {
    const [products, setProducts] = useState([]);
    useFetchProducts(setProducts);
    return (
        <>
            <ProductContext.Provider value={[products, setProducts]}>
                <Navbar bg="primary" variant="dark" className={'row'}>
                    <Nav className="me-auto">
                        <Link className={'nav-link'} to={'/'}>Istock</Link>
                        <Link className={'nav-link'} to={'/products'}>Products</Link>
                        <Link className={'nav-link'} to={'/contacts'}>Contacts</Link>
                        <SearchProduct className={'search'}/>
                    </Nav>
                </Navbar>
                <Outlet/>
            </ProductContext.Provider>
        </>
    );
}

export default App;