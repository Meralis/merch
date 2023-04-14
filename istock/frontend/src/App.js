import {Outlet} from "react-router-dom";
import ProductContext from "./context/ProductContext";
import {useState} from "react";
import useFetchProducts from "./context/UseFetchProducts";
import Header from "./components/header/Header";
import Footer from "./components/footer/Footer";

function App() {
    const [products, setProducts] = useState([]);
    useFetchProducts(setProducts);
    return (
        <>
            <ProductContext.Provider value={[products, setProducts]}>
                <Header/>
                <Outlet/>
                <Footer/>
            </ProductContext.Provider>
        </>
    );
}

export default App;