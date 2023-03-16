import {saveProducts} from "./saveProducts";

function addToBasket(productId, products, setProducts) {
    const newProducts = products.map(product => ({
        ...product,
        addedToBasket: productId === product.productId ? true : product.addedToBasket
    }))
    setProducts(newProducts);
    saveProducts(newProducts);
}

export default addToBasket;