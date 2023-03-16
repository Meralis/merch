import {saveProducts} from "./saveProducts";

function changeCount(productId, newCount, products, setProducts) {
    const newProducts = products.map(product => ({
        ...product,
        count: productId === product.productId ? newCount : product.count
    }))
     setProducts(newProducts);
    saveProducts(newProducts);
}

export default changeCount;